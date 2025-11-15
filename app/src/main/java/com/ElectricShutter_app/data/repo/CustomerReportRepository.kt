package com.electricshutter_app.data.repo

import com.electricshutter_app.data.db.AppDatabase
import com.electricshutter_app.data.models.CustomerReportEntity
import kotlinx.coroutines.flow.Flow

class CustomerReportRepository(private val db: AppDatabase) {

    fun getAllReports(): Flow<List<CustomerReportEntity>> = db.customerReportDao().getAllReports()
    suspend fun insertReport(report: CustomerReportEntity) = db.customerReportDao().insertReport(report)
    suspend fun deleteReport(report: CustomerReportEntity) = db.customerReportDao().deleteReport(report)

    fun searchReportsByName(name: String): Flow<List<CustomerReportEntity>> =
        db.customerReportDao().searchByName("%$name%")
}
