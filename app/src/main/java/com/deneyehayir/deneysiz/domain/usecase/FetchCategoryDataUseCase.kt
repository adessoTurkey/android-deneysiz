package com.deneyehayir.deneysiz.domain.usecase

import com.deneyehayir.deneysiz.domain.model.CategoryDomainModel
import com.deneyehayir.deneysiz.domain.repository.CategoryRepository
import com.deneyehayir.deneysiz.internal.injection.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchCategoryDataUseCase @Inject constructor(
    private val repository: CategoryRepository,
    @DefaultDispatcher dispatcher: CoroutineDispatcher
) : UseCase<CategoryDomainModel, Unit>(dispatcher) {

    override suspend fun execute(params: Unit): CategoryDomainModel = repository.fetchCategories()
}
