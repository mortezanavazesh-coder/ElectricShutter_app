package ccom.morteza.shuttercalculator.data.repo

import com.electricshutter_app.data.db.AppDatabase
import com.electricshutter_app.data.models.CostEntity
import com.electricshutter_app.data.models.MoreFeature
import kotlinx.coroutines.flow.Flow

class BasePriceRepository(private val db: AppDatabase) {

    // --- تیغه‌ها ---
    fun getAllBlades(): Flow<List<MoreFeature>> = db.moreFeatureDao().getBlades()
    suspend fun insertBlade(blade: MoreFeature) = db.moreFeatureDao().insertBlade(blade)
    suspend fun deleteBlade(blade: MoreFeature) = db.moreFeatureDao().deleteBlade(blade)

    // --- موتور ---
    fun getAllMotors(): Flow<List<MoreFeature>> = db.moreFeatureDao().getMotors()
    suspend fun insertMotor(motor: MoreFeature) = db.moreFeatureDao().insertMotor(motor)
    suspend fun deleteMotor(motor: MoreFeature) = db.moreFeatureDao().deleteMotor(motor)

    // --- شفت ---
    fun getAllShafts(): Flow<List<MoreFeature>> = db.moreFeatureDao().getShafts()
    suspend fun insertShaft(shaft: MoreFeature) = db.moreFeatureDao().insertShaft(shaft)
    suspend fun deleteShaft(shaft: MoreFeature) = db.moreFeatureDao().deleteShaft(shaft)

    // --- قوطی ---
    fun getAllBoxes(): Flow<List<MoreFeature>> = db.moreFeatureDao().getBoxes()
    suspend fun insertBox(box: MoreFeature) = db.moreFeatureDao().insertBox(box)
    suspend fun deleteBox(box: MoreFeature) = db.moreFeatureDao().deleteBox(box)

    // --- امکانات بیشتر ---
    fun getAllFeatures(): Flow<List<MoreFeature>> = db.moreFeatureDao().getExtras()
    suspend fun insertFeature(feature: MoreFeature) = db.moreFeatureDao().insertExtra(feature)
    suspend fun deleteFeature(feature: MoreFeature) = db.moreFeatureDao().deleteExtra(feature)

    // --- هزینه‌های اجرایی ---
    fun getCosts(): Flow<CostEntity> = db.costDao().getCosts()
    suspend fun saveCosts(cost: CostEntity) = db.costDao().insertOrUpdate(cost)
}