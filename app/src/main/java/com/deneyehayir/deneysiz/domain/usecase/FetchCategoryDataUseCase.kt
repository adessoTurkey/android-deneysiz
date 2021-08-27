package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.data.remote.model.CategoryResponse
import com.deneyehayir.deneysiz.data.repository.CategoryRepository
import com.deneyehayir.deneysiz.domain.mapper.category.CategoryUiModelMapper
import com.deneyehayir.deneysiz.domain.model.CategoryUiModel
import javax.inject.Inject

class FetchCategoryDataUseCase @Inject constructor(
    private val repository: CategoryRepository,
    private val mapper: CategoryUiModelMapper
) : UseCase<CategoryResponse, CategoryUiModel, Unit>() {

    override suspend fun buildUseCase(params: Unit): CategoryResponse =
        repository.fetchCategories()

    override fun map(dataSourceType: CategoryResponse): CategoryUiModel =
        mapper.mapToUiModel(dataSourceType)
}
