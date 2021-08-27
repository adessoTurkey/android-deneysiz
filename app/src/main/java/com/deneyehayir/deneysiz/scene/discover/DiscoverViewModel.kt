package com.deneyehayir.deneysiz.scene.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deneyehayir.deneysiz.domain.model.CategoryItemUiModel
import com.deneyehayir.deneysiz.domain.model.CategoryUiModel
import com.deneyehayir.deneysiz.domain.usecase.FetchCategoryDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val categoryDataUseCase: FetchCategoryDataUseCase
) : ViewModel() {
    private val _categoryData = MutableLiveData<CategoryUiModel>()
    val categoryData: LiveData<List<CategoryItemUiModel>> = Transformations.map(_categoryData) {
        mutableListOf<CategoryItemUiModel>().apply {
            addAll(it.items)
        }
    }

    init {
        fetchCategoryData()
    }

    private fun fetchCategoryData() = viewModelScope.launch {
        categoryDataUseCase(Unit)
            .onSuccess { postCategoryData(it) }
            .onFailure { Timber.d("fetchCategoryData onFailure: $it") }
    }

    private fun postCategoryData(data: CategoryUiModel) {
        _categoryData.value = data
    }
}
