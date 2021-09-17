package com.deneyehayir.deneysiz.domain.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.deneyehayir.deneysiz.R

@JvmInline
value class CategoryDetailUiModel(
    val items: List<CategoryDetailItemUiModel>
) : UiModel

data class CategoryDetailItemUiModel(
    val id: Int,
    val brandName: String,
    val parentCompanyName: String,
    val score: Int,
    @StringRes val scoreStringRes: Int = R.string.category_detail_score,
    @ColorRes val scoreBackgroundColorRes: Int
) : UiModel
