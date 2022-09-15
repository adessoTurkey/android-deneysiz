package com.deneyehayir.deneysiz.domain.model

data class WhoWeAreDomainModel(
    val description: String,
    val url: String,
    val supportActions: List<WhoWeAreSupportActionDomainModel>,
    val footerDescription: String,
    val socialMediaPages: List<SocialMediaPageDomainModel>
)

data class WhoWeAreSupportActionDomainModel(
    val id: Int,
    val text: String
)

data class SocialMediaPageDomainModel(
    val id: Int,
    val url: String
)
