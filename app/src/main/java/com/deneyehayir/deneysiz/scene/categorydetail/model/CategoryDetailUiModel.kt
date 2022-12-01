package com.deneyehayir.deneysiz.scene.categorydetail.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.domain.model.CategoryDetailDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryDetailItemDomainModel
import com.deneyehayir.deneysiz.ui.theme.ScoreDarkGreen
import com.deneyehayir.deneysiz.ui.theme.ScoreLightGreen
import com.deneyehayir.deneysiz.ui.theme.ScoreOrange
import com.deneyehayir.deneysiz.ui.theme.ScoreYellow

@JvmInline
value class CategoryDetailUiModel(
    val items: List<CategoryDetailItemUiModel>
)

data class CategoryDetailItemUiModel(
    val id: Int,
    val brandName: String,
    val parentCompanyName: String,
    val score: Int,
    @StringRes val scoreStringRes: Int = R.string.category_detail_score,
    val scoreBackgroundColor: Color,
    val isFavorite: Boolean
) {
    fun toDomainModel() = CategoryDetailItemDomainModel(
        id = id,
        brandName = brandName,
        parentCompanyName = parentCompanyName,
        score = score,
        isFavorite = isFavorite
    )
}

fun CategoryDetailDomainModel.toUiModel() =
    CategoryDetailUiModel(items = items.map { domain -> domain.toUiModel() })

fun CategoryDetailItemDomainModel.toUiModel() = CategoryDetailItemUiModel(
    id = id,
    brandName = brandName,
    parentCompanyName = parentCompanyName,
    score = score,
    scoreBackgroundColor = score.getScoreBackgroundColor(),
    isFavorite = isFavorite
)

fun Int.getScoreBackgroundColor(): Color {
    return when (this) {
        in 4..5 -> ScoreYellow
        in 6..7 -> ScoreLightGreen
        in 8..10 -> ScoreDarkGreen
        else -> ScoreOrange
    }
}
