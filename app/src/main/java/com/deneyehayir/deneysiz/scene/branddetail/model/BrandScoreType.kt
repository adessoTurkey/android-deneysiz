package com.deneyehayir.deneysiz.scene.branddetail.model

import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.domain.model.ParentCompanyType
import com.deneyehayir.deneysiz.ui.theme.ScoreDarkGreen
import com.deneyehayir.deneysiz.ui.theme.ScoreRed

sealed class BrandScoreType {
    abstract val titleRes: Int
    abstract val score: Int
    abstract val maxScore: Int

    open val subtitleRes by lazy {
        if (score == maxScore) {
            R.string.brand_detail_pop_up_subtitle_yes
        } else {
            R.string.brand_detail_pop_up_subtitle_no
        }
    }
    val color by lazy { if (score == maxScore) ScoreDarkGreen else ScoreRed }
    val scoreDisplayText by lazy { "$score/$maxScore" }
}

data class SafeBrand(
    override val titleRes: Int = R.string.brand_detail_pop_up_title_safe_brand,
    override val score: Int,
    override val maxScore: Int = 4
) : BrandScoreType()

data class SafeParentCompany(
    override val titleRes: Int = R.string.brand_detail_pop_up_title_parent_company_safe,
    override val score: Int,
    override val maxScore: Int = 3,
    val parentCompanyType: ParentCompanyType
) : BrandScoreType() {
    override val subtitleRes = when {
        parentCompanyType is ParentCompanyType.Unavailable -> {
            R.string.brand_detail_pop_up_subtitle_none
        }
        score == maxScore -> R.string.brand_detail_pop_up_subtitle_yes
        else -> R.string.brand_detail_pop_up_subtitle_no
    }
}

data class VeganBrand(
    override val titleRes: Int = R.string.brand_detail_pop_up_title_brand_vegan,
    override val score: Int,
    override val maxScore: Int = 1
) : BrandScoreType()

data class OfferInChina(
    override val titleRes: Int = R.string.brand_detail_pop_up_title_china_offer,
    override val score: Int,
    override val maxScore: Int = 1
) : BrandScoreType() {
    override val subtitleRes = if (score != maxScore) {
        R.string.brand_detail_pop_up_subtitle_yes
    } else {
        R.string.brand_detail_pop_up_subtitle_no
    }
}

data class VeganProduct(
    override val titleRes: Int = R.string.brand_detail_pop_up_title_vegan_product,
    override val score: Int,
    override val maxScore: Int = 1
) : BrandScoreType()
