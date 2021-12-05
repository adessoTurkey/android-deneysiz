package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.domain.model.CategoryDetailDomainModel
import com.deneyehayir.deneysiz.domain.repository.CategoryRepository
import com.deneyehayir.deneysiz.internal.injection.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchCategoryDetailUseCase @Inject constructor(
    private val repository: CategoryRepository,
    @DefaultDispatcher dispatcher: CoroutineDispatcher
) : UseCase<CategoryDetailDomainModel, FetchCategoryDetailUseCase.Params>(dispatcher) {

    override suspend fun execute(params: Params): CategoryDetailDomainModel =
        repository.fetchCategoryDetail(
            categoryId = params.categoryId
        )

    data class Params(
        val categoryId: String
    )
}
