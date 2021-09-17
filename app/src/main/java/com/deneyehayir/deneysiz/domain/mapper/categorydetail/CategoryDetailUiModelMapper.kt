package com.deneyehayir.deneysiz.domain.mapper.categorydetail

import com.deneyehayir.deneysiz.data.remote.model.CategoryDetailResponse
import com.deneyehayir.deneysiz.domain.mapper.UiModelMapper
import com.deneyehayir.deneysiz.domain.model.CategoryDetailUiModel
import javax.inject.Inject

class CategoryDetailUiModelMapper @Inject constructor(
    private val categoryDetailItemUiModelMapper: CategoryDetailItemUiModelMapper
) : UiModelMapper<CategoryDetailUiModel, CategoryDetailResponse> {

    override fun mapToUiModel(response: CategoryDetailResponse) = CategoryDetailUiModel(
        items = response.data.map {
            categoryDetailItemUiModelMapper.mapToUiModel(it)
        }
    )
}
