package com.morteza.electricshutter_app.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_reports")
data class CustomerReportEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val customerName: String,
    val customerPhone: String?, // اختیاری
    val date: Long = System.currentTimeMillis(), // زمان ذخیره به صورت timestamp
    val height: Double, // متر
    val width: Double,  // متر
    val area: Double,   // متر مربع
    val bladeType: String,
    val motorType: String,
    val priceBlade: Long,
    val priceMotor: Long,
    val shaftType: String,
    val priceShaft: Long,
    val boxType: String?,
    val priceBox: Long?,
    val installationCost: Long,
    val weldingCost: Long,
    val transportCost: Long,
    val extraFeatures: String?, // نام امکانات بیشتر انتخاب شده به صورت کاما جدا
    val extraFeaturesPrice: Long,
    val totalPrice: Long

)

