package com.electricshutter_app.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "more_features")
data class MoreFeature(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Long
)
