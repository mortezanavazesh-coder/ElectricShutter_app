package com.morteza.electricshutter_app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.morteza.electricshutter_app.data.db.dao.CustomerReportDao
import com.morteza.electricshutter_app.data.db.dao.CostDao
import com.morteza.electricshutter_app.data.db.dao.MoreFeatureDao
import com.morteza.electricshutter_app.data.local.entity.CustomerReportEntity
import com.morteza.electricshutter_app.data.local.entity.CostEntity
import com.morteza.electricshutter_app.data.local.entity.MoreFeature

@Database(
    entities = [CustomerReportEntity::class, MoreFeature::class, CostEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moreFeatureDao(): MoreFeatureDao
    abstract fun costDao(): CostDao
    abstract fun customerReportDao(): CustomerReportDao
}
