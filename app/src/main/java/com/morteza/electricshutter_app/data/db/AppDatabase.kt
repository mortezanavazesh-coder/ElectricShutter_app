
package com.morteza.shuttercalculator.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.electricshutter_app.data.models.*

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
