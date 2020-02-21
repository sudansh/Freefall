package com.sudansh.falldetection

import android.content.Context
import com.sudansh.falldetection.di.component
import com.sudansh.falldetection.model.FallRecord

class FallDetectorLog {

    companion object {

        suspend fun getData(context: Context): List<FallRecord> =
            component(context).database().getAllRecords()
    }

}
