package com.deneyehayir.deneysiz.scene.whoweare.model

import androidx.annotation.DrawableRes
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.domain.model.SocialMediaPageDomainModel
import com.deneyehayir.deneysiz.domain.model.WhoWeAreDomainModel
import com.deneyehayir.deneysiz.domain.model.WhoWeAreSupportActionDomainModel
import com.deneyehayir.deneysiz.internal.extension.getDisplayUrl

data class WhoWeAreUiModel(
    val description: String,
    val url: String,
    val supportActions: List<WhoWeAreSupportActionUiModel>,
    val footerDescription: String,
    val socialMediaPages: List<SocialMediaPageUiModel>
) {
    val displayUrl = url.getDisplayUrl()
}

data class WhoWeAreSupportActionUiModel(
    val id: Int,
    val text: String,
    @DrawableRes val iconResId: Int
)

data class SocialMediaPageUiModel(
    val id: Int,
    val url: String,
    @DrawableRes val iconResId: Int
)

fun WhoWeAreDomainModel.toUiModel() = WhoWeAreUiModel(
    description = description,
    url = url,
    supportActions = supportActions.map { supportAction -> supportAction.toUiModel() },
    footerDescription = footerDescription,
    socialMediaPages = socialMediaPages.map { socialMediaPage -> socialMediaPage.toUiModel() }
)

fun WhoWeAreSupportActionDomainModel.toUiModel() = WhoWeAreSupportActionUiModel(
    id = id,
    text = text,
    iconResId = when (id) {
        0 -> R.drawable.ic_email
        1 -> R.drawable.ic_support
        2 -> R.drawable.ic_heart
        else -> R.drawable.ic_do_you_know
    }
)

fun SocialMediaPageDomainModel.toUiModel() = SocialMediaPageUiModel(
    id = id,
    url = url,
    iconResId = when (id) {
        0 -> R.drawable.ic_twitter
        1 -> R.drawable.ic_instagram
        2 -> R.drawable.ic_youtube
        3 -> R.drawable.ic_facebook
        else -> R.drawable.ic_do_you_know
    }
)
