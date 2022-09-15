package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.domain.model.CertificateItemDomainModel
import com.deneyehayir.deneysiz.domain.repository.Repository
import com.deneyehayir.deneysiz.internal.injection.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchCertificateByNameUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<CertificateItemDomainModel, FetchCertificateByNameUseCase.Params>(dispatcher) {

    override suspend fun execute(params: Params): CertificateItemDomainModel {
        return repository.fetchCertificates().certificates.firstOrNull { certificate ->
            certificate.certificate.type == params.certificateName
        } ?: CertificateItemDomainModel.Empty
    }

    data class Params(
        val certificateName: String
    )
}
