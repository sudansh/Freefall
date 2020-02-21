package com.sudansh.falldetection

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

fun Context.startFallService() {
    val startIntent = Intent(this, SensorService::class.java)
    startIntent.action = SensorService.START_SERVICE
    startService(startIntent)
}

fun Context.stopFallService() {
    val stopIntent = Intent(this, SensorService::class.java)
    stopIntent.action = SensorService.STOP_SERVICE
    startService(stopIntent)
}

fun SensorService.startForeground() {
    val startPendingIntent = android.app.PendingIntent.getActivity(
        this,
        0,
        packageManager.getLaunchIntentForPackage(packageName),
        0
    )

    val stopServiceIntent = Intent(this, SensorService::class.java)
    stopServiceIntent.action = SensorService.STOP_SERVICE
    val stopServicePendingIntent = android.app.PendingIntent.getService(
        this,
        0,
        stopServiceIntent,
        0
    )
    val stopAction = NotificationCompat.Action.Builder(
        R.drawable.ic_stop,
        "Stop",
        stopServicePendingIntent
    ).build()

    val notification = NotificationCompat.Builder(this, SensorService.CHANNEL_ID)
        .setContentTitle("XBird Service")
        .setContentText("Fall Detection running...")
        .addAction(stopAction)
        .setSmallIcon(R.drawable.ic_running)
        .setContentIntent(startPendingIntent)
        .build()

    startForeground(1, notification)
}

fun SensorService.createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            SensorService.CHANNEL_ID,
            SensorService.CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        getSystemService(NotificationManager::class.java)?.createNotificationChannel(channel)
    }
}