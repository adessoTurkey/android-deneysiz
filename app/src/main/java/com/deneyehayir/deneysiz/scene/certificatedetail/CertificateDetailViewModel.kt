package com.deneyehayir.deneysiz.scene.certificatedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deneyehayir.deneysiz.domain.usecase.FetchCertificateByNameUseCase
import com.deneyehayir.deneysiz.scene.certificatedetail.model.toUiModel
import com.deneyehayir.deneysiz.scene.navCertificateName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CertificateDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val certificateByNameUseCase: FetchCertificateByNameUseCase
) : ViewModel() {
    private val certificateName = savedStateHandle.get<String>(navCertificateName) ?: ""
    private val _viewState: CertificateDetailViewState = CertificateDetailViewState.Initial
    val viewState: StateFlow<CertificateDetailViewState> = flow {
        certificateByNameUseCase(
            params = FetchCertificateByNameUseCase.Params(
                certificateName = certificateName
            )
        )
            .onSuccess { certificate ->
                emit(_viewState.updateCertificateDetail(certificate.toUiModel()))
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
