package com.deneyehayir.deneysiz.domain.mapper.categorydetail

import androidx.annotation.ColorRes
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.data.remote.model.CategoryDetailItemResponse
import com.deneyehayir.deneysiz.domain.mapper.UiModelMapper
import com.deneyehayir.deneysiz.domain.model.CategoryDetailItemUiModel
import javax.inject.Inject

class CategoryDetailItemUiModelMapper @Inject constructor() :
    UiModelMapper<CategoryDetailItemUiModel, CategoryDetailItemResponse> {

    override fun mapToUiModel(response: CategoryDetailItemResponse) = with(response) {
        CategoryDetailItemUiModel(
            id = id,
            brandName = name,
            parentCompanyName = parentCompany ?: "",
            score = score,
            scoreBackgroundColorRes = getScoreBackgroundColor(score)
        )
    }

    @ColorRes
    private fun getScoreBackgroundColor(score: Int): Int {
        return when (score) {
            in 1..2 -> R.color.brand_score_red
            in 3..5 -> R.color.brand_score_orange
            6 -> R.color.brand_score_yellow
            in 7..8 -> R.color.brand_score_light_green
            else -> R.color.brand_score_dark_green
        }
    }
}
