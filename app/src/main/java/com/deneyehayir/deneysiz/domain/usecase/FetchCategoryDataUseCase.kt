package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.domain.model.CategoryDomainModel
import com.deneyehayir.deneysiz.domain.repository.Repository
import com.deneyehayir.deneysiz.internal.injection.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchCategoryDataUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<CategoryDomainModel, Unit>(dispatcher) {

    override suspend fun execute(params: Unit): CategoryDomainModel = repository.fetchCategories()
}
