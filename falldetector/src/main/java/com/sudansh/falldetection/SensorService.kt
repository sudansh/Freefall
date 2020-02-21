package com.sudansh.falldetection

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.sudansh.falldetection.database.FallDatabase
import com.sudansh.falldetection.di.component
import com.sudansh.falldetection.model.FallRecord
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class SensorService : Service() {
    @Inject
    lateinit var fallDetector: FallDetector
    @Inject
    lateinit var fallDatabase: FallDatabase

    private val sensorListener by lazy {
        object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

            override fun onSensorChanged(event: SensorEvent?) {
                if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                    val x = event.values[0]
                    val y = event.values[1]
                    val z = event.values[2]
                    when (val record = fallDetector.calculate(x, y, z)) {
                        is FallRecord -> {
                            showFallNotification()
                            GlobalScope.launch {
                                fallDatabase.insertRecord(record)
                            }
                        }
                    }
                }
            }

        }
    }

    private var sensorManager: SensorManager? = null
    private var accelerometer: Sensor? = null

    override fun onBind(intent: Intent): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        component(this).inject(this)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (intent.action == START_SERVICE) {
            registerService()
        } else {
            unregisterService()
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        sensorManager?.unregisterListener(sensorListener)
        sensorManager = null
        accelerometer = null
        super.onDestroy()
    }

    private fun registerService() {
        showStickyNotification()
        startDetection()
    }

    private fun unregisterService() {
        stopForeground(true)
        stopSelf()
    }

    private fun showStickyNotification() {
        createNotificationChannel()
        startForeground()
    }

    private fun startDetection() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager?.registerListener(
            sensorListener,
            accelerometer,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    private fun showFallNotification() {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentText("Fall Detected!")
            .setSmallIcon(R.drawable.ic_warning)
            .build()

        with(NotificationManagerCompat.from(this)) {
            notify(Random.nextInt(), notification)
        }
    }

    companion object {

        const val CHANNEL_ID = "channel_fall_detector"
        const val CHANNEL_NAME = "Fall Detector"

        const val START_SERVICE = "ACTION_START"
        const val STOP_SERVICE = "ACTION_STOP"

    }

}
