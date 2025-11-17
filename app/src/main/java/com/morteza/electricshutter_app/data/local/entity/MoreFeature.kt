package com.morteza.electricshutter_app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "more_features")
data class MoreFeature(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String,
    var price: Long
)
