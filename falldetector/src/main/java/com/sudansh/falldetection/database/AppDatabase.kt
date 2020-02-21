package com.sudansh.falldetection.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sudansh.falldetection.model.FallRecord

@Database(
    entities = [FallRecord::class],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fallRecordDao(): FallDao
}