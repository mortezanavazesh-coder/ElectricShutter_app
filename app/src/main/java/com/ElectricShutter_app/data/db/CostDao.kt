package com.electricshutter_app.data.db

import androidx.room.*
import com.electricshutter_app.data.models.CostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CostDao {

    @Query("SELECT * FROM costs LIMIT 1")
    fun getCosts(): Flow<CostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(cost: CostEntity)
}
