package com.deneyehayir.deneysiz.domain.mapper.category

import com.deneyehayir.deneysiz.data.remote.model.CategoryResponse
import com.deneyehayir.deneysiz.domain.mapper.UiModelMapper
import com.deneyehayir.deneysiz.domain.model.CategoryUiModel
import javax.inject.Inject

class CategoryUiModelMapper @Inject constructor(
    private val categoryItemModelMapper: CategoryItemUiModelMapper
) : UiModelMapper<CategoryUiModel, CategoryResponse> {

    override fun mapToUiModel(response: CategoryResponse) = CategoryUiModel(
        items = response.items.map {
            categoryItemModelMapper.mapToUiModel(it)
        }
    )
}
