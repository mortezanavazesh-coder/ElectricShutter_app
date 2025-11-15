package com.electricshutter_app.data.db

import androidx.room.*
import com.electricshutter_app.data.models.CustomerReportEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerReportDao {

    @Query("SELECT * FROM customer_reports ORDER BY date DESC")
    fun getAllReports(): Flow<List<CustomerReportEntity>>

    @Insert
    suspend fun insertReport(report: CustomerReportEntity)

    @Delete
    suspend fun deleteReport(report: CustomerReportEntity)

    @Query("SELECT * FROM customer_reports WHERE customerName LIKE :name ORDER BY date DESC")
    fun searchByName(name: String): Flow<List<CustomerReportEntity>>
}
