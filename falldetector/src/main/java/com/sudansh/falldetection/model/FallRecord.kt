package com.sudansh.falldetection.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class FallRecord(
    @PrimaryKey val timestamp: Long,
    val duration: Long
) {
    fun date() = Date(timestamp)
}
