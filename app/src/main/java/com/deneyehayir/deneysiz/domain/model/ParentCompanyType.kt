package com.deneyehayir.deneysiz.domain.model

sealed class ParentCompanyType {
    object Unavailable : ParentCompanyType()
    data class Available(
        val name: String,
        val safe: Boolean
    ) : ParentCompanyType()
}
