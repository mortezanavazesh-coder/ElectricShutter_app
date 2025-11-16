package com.morteza.shuttercalculator.db

import androidx.room.*
import com.electricshutter_app.data.models.MoreFeature
import kotlinx.coroutines.flow.Flow

@Dao
interface MoreFeatureDao {

    // تیغه‌ها
    @Query("SELECT * FROM more_features WHERE name LIKE 'تیغه%'")
    fun getBlades(): Flow<List<MoreFeature>>
    @Insert suspend fun insertBlade(blade: MoreFeature)
    @Delete suspend fun deleteBlade(blade: MoreFeature)

    // موتور
    @Query("SELECT * FROM more_features WHERE name LIKE 'موتور%'")
    fun getMotors(): Flow<List<MoreFeature>>
    @Insert suspend fun insertMotor(motor: MoreFeature)
    @Delete suspend fun deleteMotor(motor: MoreFeature)

    // شفت
    @Query("SELECT * FROM more_features WHERE name LIKE 'شفت%'")
    fun getShafts(): Flow<List<MoreFeature>>
    @Insert suspend fun insertShaft(shaft: MoreFeature)
    @Delete suspend fun deleteShaft(shaft: MoreFeature)

    // قوطی
    @Query("SELECT * FROM more_features WHERE name LIKE 'قوطی%'")
    fun getBoxes(): Flow<List<MoreFeature>>
    @Insert suspend fun insertBox(box: MoreFeature)
    @Delete suspend fun deleteBox(box: MoreFeature)

    // امکانات بیشتر
    @Query("SELECT * FROM more_features WHERE name NOT LIKE 'تیغه%' AND name NOT LIKE 'موتور%' AND name NOT LIKE 'شفت%' AND name NOT LIKE 'قوطی%'")
    fun getExtras(): Flow<List<MoreFeature>>
    @Insert suspend fun insertExtra(feature: MoreFeature)
    @Delete suspend fun deleteExtra(feature: MoreFeature)
}