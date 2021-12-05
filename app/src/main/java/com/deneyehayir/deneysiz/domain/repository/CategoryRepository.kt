package com.deneyehayir.deneysiz.domain.repository

import com.deneyehayir.deneysiz.domain.model.CategoryDetailDomainModel
import com.deneyehayir.deneysiz.domain.model.CategoryDomainModel

interface CategoryRepository {

    suspend fun fetchCategories(): CategoryDomainModel

    suspend fun fetchCategoryDetail(categoryId: String): CategoryDetailDomainModel
}
