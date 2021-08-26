package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.data.remote.model.CategoryDetailResponse
import com.deneyehayir.deneysiz.data.repository.CategoryRepository
import com.deneyehayir.deneysiz.domain.mapper.categorydetail.CategoryDetailUiModelMapper
import com.deneyehayir.deneysiz.domain.model.CategoryDetailUiModel
import javax.inject.Inject

class FetchCategoryDetailUseCase @Inject constructor(
    private val repository: CategoryRepository,
    private val mapper: CategoryDetailUiModelMapper
) : UseCase<CategoryDetailResponse, CategoryDetailUiModel, FetchCategoryDetailUseCase.Params>() {

    override suspend fun buildUseCase(params: Params): CategoryDetailResponse =
        repository.fetchCategoryDetail(
            params.categoryId
        )

    override fun map(dataSourceType: CategoryDetailResponse): CategoryDetailUiModel =
        mapper.mapToUiModel(dataSourceType)

    data class Params(
        val categoryId: String
    )
}
