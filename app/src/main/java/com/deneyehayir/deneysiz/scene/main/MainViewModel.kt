package com.deneyehayir.deneysiz.scene.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deneyehayir.deneysiz.domain.model.DummyUiModel
import com.deneyehayir.deneysiz.domain.usecase.FetchDummyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchDummyUseCase: FetchDummyUseCase
) : ViewModel() {
    private val _dummyData = MutableLiveData<DummyUiModel>()
    val dummyData: LiveData<DummyUiModel> = _dummyData

    init {
        fetchDummy()
    }

    private fun fetchDummy() = viewModelScope.launch {
        fetchDummyUseCase(
            FetchDummyUseCase.Params(
                queryParam = "param"
            )
        )
            .onSuccess { postDummyData(it) }
            .onFailure { Timber.d("fetchDummy onFailure: $it") }
    }

    private fun postDummyData(data: DummyUiModel) {
        _dummyData.value = data
    }
}
