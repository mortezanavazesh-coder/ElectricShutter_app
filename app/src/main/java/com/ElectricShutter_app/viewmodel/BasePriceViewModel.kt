package com.electricshutter_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electricshutter_app.data.models.CostEntity
import com.electricshutter_app.data.models.MoreFeature
import com.electricshutter_app.data.repo.BasePriceRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BasePriceViewModel(private val repo: BasePriceRepository) : ViewModel() {

    val blades: StateFlow<List<MoreFeature>> = repo.getAllBlades().stateIn(
        viewModelScope, SharingStarted.Lazily, emptyList()
    )

    val motors: StateFlow<List<MoreFeature>> = repo.getAllMotors().stateIn(
        viewModelScope, SharingStarted.Lazily, emptyList()
    )

    val shafts: StateFlow<List<MoreFeature>> = repo.getAllShafts().stateIn(
        viewModelScope, SharingStarted.Lazily, emptyList()
    )

    val boxes: StateFlow<List<MoreFeature>> = repo.getAllBoxes().stateIn(
        viewModelScope, SharingStarted.Lazily, emptyList()
    )

    val extras: StateFlow<List<MoreFeature>> = repo.getAllFeatures().stateIn(
        viewModelScope, SharingStarted.Lazily, emptyList()
    )

    private val _costs = MutableStateFlow(CostEntity())
    val costs: StateFlow<CostEntity> = _costs

    init {
        viewModelScope.launch {
            repo.getCosts().collect {
                _costs.value = it
            }
        }
    }

    fun saveCosts(cost: CostEntity) {
        viewModelScope.launch {
            repo.saveCosts(cost)
        }
    }

    // عملیات افزودن/حذف/ویرایش
    fun addBlade(blade: MoreFeature) = viewModelScope.launch { repo.insertBlade(blade) }
    fun deleteBlade(blade: MoreFeature) = viewModelScope.launch { repo.deleteBlade(blade) }
    fun addMotor(motor: MoreFeature) = viewModelScope.launch { repo.insertMotor(motor) }
    fun deleteMotor(motor: MoreFeature) = viewModelScope.launch { repo.deleteMotor(motor) }
    fun addShaft(shaft: MoreFeature) = viewModelScope.launch { repo.insertShaft(shaft) }
    fun deleteShaft(shaft: MoreFeature) = viewModelScope.launch { repo.deleteShaft(shaft) }
    fun addBox(box: MoreFeature) = viewModelScope.launch { repo.insertBox(box) }
    fun deleteBox(box: MoreFeature) = viewModelScope.launch { repo.deleteBox(box) }
    fun addExtra(feature: MoreFeature) = viewModelScope.launch { repo.insertFeature(feature) }
    fun deleteExtra(feature: MoreFeature) = viewModelScope.launch { repo.deleteFeature(feature) }
}
