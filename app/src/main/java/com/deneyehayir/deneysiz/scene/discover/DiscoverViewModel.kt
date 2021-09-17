package com.deneyehayir.deneysiz.scene.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.deneyehayir.deneysiz.domain.model.CategoryItemUiModel
import com.deneyehayir.deneysiz.domain.usecase.FetchCategoryDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val categoryDataUseCase: FetchCategoryDataUseCase
) : ViewModel() {
    val categoryData: LiveData<List<CategoryItemUiModel>> = liveData {
        categoryDataUseCase(Unit)
            .onSuccess { emit(it.items) }
            .onFailure { Timber.d("fetchCategoryData onFailure: $it") }
    }
}
