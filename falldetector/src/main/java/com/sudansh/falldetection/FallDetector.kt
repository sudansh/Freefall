package com.sudansh.falldetection

import android.util.Log
import com.sudansh.falldetection.model.FallRecord
import java.util.*
import javax.inject.Inject
import kotlin.math.sqrt

class FallDetector @Inject constructor() {

    private var fallStarted: Long = 0
    private var fallEnded: Long = 0
    private var isFalling: Boolean = false

    fun calculate(x: Float, y: Float, z: Float): FallRecord? {
        val angularSpeed = sqrt(x * x + y * y + z * z)

        if (isFalling) {
            if (angularSpeed > LAND_THRESHOLD) {
                isFalling = !isFalling
                fallEnded()
                return getFallRecord()
            }
        } else {
            if (angularSpeed < FALL_THRESHOLD) {
                isFalling = !isFalling
                fallStarted()
            }
        }
        return null
    }

    private fun fallStarted() {
        Log.d("FallDetector", "start")
        fallStarted = System.currentTimeMillis()
    }

    private fun fallEnded() {
        Log.d("FallDetector", "end")
        fallEnded = System.currentTimeMillis()
    }

    private fun getFallRecord() =
        FallRecord(Date().time, fallEnded - fallStarted)

    companion object {

        const val FALL_THRESHOLD = 1
        const val LAND_THRESHOLD = 10

    }

}
