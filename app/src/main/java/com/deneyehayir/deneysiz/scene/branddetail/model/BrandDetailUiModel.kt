package com.deneyehayir.deneysiz.scene.branddetail.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.domain.model.BrandDetailDomainModel
import com.deneyehayir.deneysiz.domain.model.CertificateType
import com.deneyehayir.deneysiz.domain.model.ParentCompanyType
import com.deneyehayir.deneysiz.ui.theme.ScoreDarkGreen
import com.deneyehayir.deneysiz.ui.theme.ScoreLightGreen
import com.deneyehayir.deneysiz.ui.theme.ScoreOrange
import com.deneyehayir.deneysiz.ui.theme.ScoreYellow

data class BrandDetailUiModel(
    val id: Int,
    val brandName: String,
    val parentCompany: BrandDetailParentCompanyUiModel,
    val scoreData: ScoreUiModel,
    val certificateList: List<CertificateUiModel>,
    val brandInfoList: List<BrandInfoUiModel>,
    val description: String,
    val updateDate: String
)

data class BrandDetailParentCompanyUiModel(
    val isAvailable: Boolean,
    val name: String,
    val isSafe: Boolean
)

data class ScoreUiModel(
    val totalValue: Int,
    val description: Int,
    val popupItems: List<BrandScoreType>
) {
    val scoreDisplayText = "$totalValue/10"
    val backgroundColor = totalValue.getScoreBackgroundColor()
}

data class CertificateUiModel(
    val certificate: CertificateType,
    val isAvailable: Boolean,
) {
    val opacity = if (isAvailable) 1f else 0.3f
    val iconRes = when (certificate) {
        CertificateType.LeapingBunny -> R.drawable.ic_certificate_leaping_bunny
        CertificateType.BeautyWithoutBunnies -> R.drawable.ic_certificate_beauty_without_bunnies
        CertificateType.VeganSociety -> R.drawable.ic_certificate_vegan_society
        CertificateType.VLabel -> R.drawable.ic_certificate_v_label
        CertificateType.None -> R.drawable.ic_do_you_know
    }
}

data class BrandInfoUiModel(
    @StringRes val titleStringRes: Int,
    @DrawableRes val iconRes: Int
)

private fun Int.getScoreBackgroundColor(): Color {
    return when (this) {
        in 4..5 -> ScoreYellow
        in 6..7 -> ScoreLightGreen
        in 8..10 -> ScoreDarkGreen
        else -> ScoreOrange
    }
}

fun BrandDetailDomainModel.toUiModel() = BrandDetailUiModel(
    id = id,
    brandName = name,
    parentCompany = parentCompany.toUiModel(),
    scoreData = toScoreUiModel(),
    certificateList = certificates.map { certificate ->
        CertificateUiModel(
            certificate = certificate.certificate,
            isAvailable = certificate.valid
        )
    },
    brandInfoList = populateBrandInfoList(),
    description = description,
    updateDate = updateDate
)

fun ParentCompanyType.toUiModel(): BrandDetailParentCompanyUiModel {
    return when (this) {
        ParentCompanyType.Unavailable -> BrandDetailParentCompanyUiModel(
            isAvailable = false,
            name = "",
            isSafe = true
        )
        is ParentCompanyType.Available -> BrandDetailParentCompanyUiModel(
            isAvailable = true,
            name = name,
            isSafe = safe
        )
    }
}

fun BrandDetailDomainModel.toScoreUiModel() = ScoreUiModel(
    totalValue = score,
    description = R.string.brand_detail_pop_up_description,
    popupItems = populatePopupItems()
)

// TODO find another way
private fun BrandDetailDomainModel.populatePopupItems(): List<BrandScoreType> {
    val safeBrand = SafeBrand(score = if (isSafe) 4 else 0)
    val safeParentCompany = SafeParentCompany(
        parentCompanyType = parentCompany,
        score = when (parentCompany) {
            is ParentCompanyType.Unavailable -> 3
            is ParentCompanyType.Available -> if (isSafe) 3 else 0
        }
    )
    val veganBrand = VeganBrand(score = if (isVegan) 1 else 0)
    val offerInChina = OfferInChina(score = if (isOfferedInChina) 0 else 1)
    val veganProduct = VeganProduct(score = if (isVeganProduct) 1 else 0)
    return listOf(
        safeBrand,
        safeParentCompany,
        veganBrand,
        offerInChina,
        veganProduct
    )
}

// TODO find another way
private fun BrandDetailDomainModel.populateBrandInfoList(): List<BrandInfoUiModel> {
    val veganProducts = BrandInfoUiModel(
        titleStringRes = R.string.brand_detail_info_vegan_product,
        iconRes = isVeganProduct.getSafetyIcon()
    )
    val offerInChina = BrandInfoUiModel(
        titleStringRes = R.string.brand_detail_info_china_offer,
        iconRes = isOfferedInChina.not().getSafetyIcon()
    )
    val safeParentCompany = BrandInfoUiModel(
        titleStringRes = R.string.brand_detail_info_parent_company_safe,
        iconRes = when (parentCompany) {
            ParentCompanyType.Unavailable -> R.drawable.ic_positive
            is ParentCompanyType.Available -> parentCompany.safe.getSafetyIcon()
        }
    )

    return listOf(
        veganProducts,
        offerInChina,
        safeParentCompany
    )
}

@DrawableRes
private fun Boolean.getSafetyIcon(): Int =
    if (this) R.drawable.ic_positive else R.drawable.ic_negative
