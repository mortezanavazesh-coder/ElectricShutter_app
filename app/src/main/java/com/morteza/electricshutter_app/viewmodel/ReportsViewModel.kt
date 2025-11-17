package com.morteza.shuttercalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electricshutter_app.data.models.CustomerReportEntity
import com.electricshutter_app.data.repo.CustomerReportRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ReportsViewModel(private val repo: CustomerReportRepository) : ViewModel() {

    private val _reports = MutableStateFlow<List<CustomerReportEntity>>(emptyList())
    val reports: StateFlow<List<CustomerReportEntity>> = _reports

    init {
        viewModelScope.launch {
            repo.getAllReports().collect {
                _reports.value = it
            }
        }
    }

    fun addReport(report: CustomerReportEntity) = viewModelScope.launch {
        repo.insertReport(report)
    }

    fun deleteReport(report: CustomerReportEntity) = viewModelScope.launch {
        repo.deleteReport(report)
    }

    fun searchReports(name: String) = viewModelScope.launch {
        repo.searchReportsByName(name).collect {
            _reports.value = it
        }
    }
}