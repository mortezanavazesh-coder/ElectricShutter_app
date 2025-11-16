package com.morteza.shuttercalculator.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "costs")
data class CostEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val installation: Long = 0L,
    val welding: Long = 0L,
    val transport: Long = 0L
)
