package com.sudansh.falldetection.database

import com.sudansh.falldetection.model.FallRecord
import javax.inject.Inject

class FallDatabase @Inject constructor(
    private val fallDao: FallDao
) {
    suspend fun insertRecord(record: FallRecord) {
        fallDao.insert(record)
    }

    suspend fun getAllRecords(): List<FallRecord> {
        return fallDao.findAll()
    }

}
