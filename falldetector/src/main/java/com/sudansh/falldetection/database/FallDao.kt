package com.sudansh.falldetection.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sudansh.falldetection.model.FallRecord

@Dao
interface FallDao {

    @Query("SELECT * FROM fallrecord")
    suspend fun findAll(): List<FallRecord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flightLeg: FallRecord)

    @Query("DELETE FROM fallrecord")
    suspend fun clear()
}