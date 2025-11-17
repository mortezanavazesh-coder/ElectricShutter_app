package com.morteza.electricshutter_app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "costs")
data class CostEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var installation: Long = 0L,
    var welding: Long = 0L,
    var transport: Long = 0L
)
