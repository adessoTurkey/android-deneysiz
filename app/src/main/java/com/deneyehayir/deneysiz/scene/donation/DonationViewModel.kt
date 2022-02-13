package com.deneyehayir.deneysiz.scene.donation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deneyehayir.deneysiz.domain.usecase.FetchDonationDataUseCase
import com.deneyehayir.deneysiz.scene.donation.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DonationViewModel @Inject constructor(
    private val donationDataUseCase: FetchDonationDataUseCase
) : ViewModel() {
    private val _viewState: DonationViewState = DonationViewState.Initial
    val viewState: StateFlow<DonationViewState> = flow {
        donationDataUseCase(Unit)
            .onSuccess { donationData ->
                emit(_viewState.updateDonationUiModel(donationData.toUiModel()))
            }
            .onFailure {
                emit(_viewState.setError())
            }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _viewState
    )
}
