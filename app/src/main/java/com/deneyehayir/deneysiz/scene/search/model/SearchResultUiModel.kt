package com.deneyehayir.deneysiz.scene.search.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.domain.model.SearchResultDomainModel
import com.deneyehayir.deneysiz.domain.model.SearchResultItemDomainModel
import com.deneyehayir.deneysiz.scene.categorydetail.model.getScoreBackgroundColor

@JvmInline
value class SearchResultUiModel(
    val items: List<SearchResultItemUiModel>
)

data class SearchResultItemUiModel(
    val id: Int,
    val brandName: String,
    val parentCompanyName: String,
    val score: Int,
    @StringRes val scoreStringRes: Int = R.string.search_result_score,
    val scoreBackgroundColor: Color
)

fun SearchResultDomainModel.toUiModel() = SearchResultUiModel(
    items = items.map { domain -> domain.toUiModel() }
)

fun SearchResultItemDomainModel.toUiModel() = SearchResultItemUiModel(
    id = id,
    brandName = brandName,
    parentCompanyName = parentCompanyName,
    score = score,
    scoreBackgroundColor = score.getScoreBackgroundColor()
)
