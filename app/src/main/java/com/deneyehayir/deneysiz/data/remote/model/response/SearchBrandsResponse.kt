package com.deneyehayir.deneysiz.data.remote.model.response

import com.deneyehayir.deneysiz.domain.model.SearchResultDomainModel
import com.deneyehayir.deneysiz.domain.model.SearchResultItemDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class SearchBrandsResponse(
    val status: Int?,
    val message: String?,
    val data: List<SearchBrandsItemResponse>?
)

@Serializable
data class SearchBrandsItemResponse(
    val id: Int?,
    val name: String?,
    val score: Int?,
    val parentCompany: ParentCompanyResponse?
)

fun SearchBrandsResponse.toSearchResultDomainModel() = SearchResultDomainModel(
    items = data?.map { response ->
        SearchResultItemDomainModel(
            id = response.id ?: -1,
            brandName = response.name.orEmpty(),
            parentCompanyName = response.parentCompany?.name.orEmpty(),
            score = response.score ?: -1
        )
    }.orEmpty(),
    shouldShowError = status != 200
)
