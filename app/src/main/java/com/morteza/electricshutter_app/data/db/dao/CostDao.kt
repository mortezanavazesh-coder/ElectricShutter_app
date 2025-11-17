package com.morteza.electricshutter_app.data.db.dao

import androidx.room.*
import com.morteza.electricshutter_app.data.local.entity.CostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CostDao {

    @Query("SELECT * FROM costs LIMIT 1")
    fun getCosts(): Flow<CostEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(cost: CostEntity)
}
