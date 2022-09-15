package com.deneyehayir.deneysiz.domain.model

data class SupportDomainModel(
    val description: String,
    val supportActions: List<SupportActionDomainModel>
)

data class SupportActionDomainModel(
    val id: Int,
    val title: String,
    val description: String,
    val socialMediaPages: List<SocialMediaPageDomainModel>
)
