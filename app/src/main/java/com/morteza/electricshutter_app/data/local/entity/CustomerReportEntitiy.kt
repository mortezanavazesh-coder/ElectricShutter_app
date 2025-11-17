package com.morteza.electricshutter_app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "customer_reports")
data class CustomerReportEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var customerName: String,
    var customerPhone: String?, // اختیاری
    var date: Long = System.currentTimeMillis(), // زمان ذخیره به صورت timestamp
    var height: Double, // متر
    var width: Double,  // متر
    var area: Double,   // متر مربع
    var bladeType: String,
    var motorType: String,
    var priceBlade: Long,
    var priceMotor: Long,
    var shaftType: String,
    var priceShaft: Long,
    var boxType: String?,
    var priceBox: Long?,
    var installationCost: Long,
    var weldingCost: Long,
    var transportCost: Long,
    var extraFeatures: String?, // نام امکانات بیشتر انتخاب شده به صورت کاما جدا
    var extraFeaturesPrice: Long,
    var totalPrice: Long
)
