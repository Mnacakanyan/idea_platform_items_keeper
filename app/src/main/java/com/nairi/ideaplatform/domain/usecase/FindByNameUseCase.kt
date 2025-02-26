package com.nairi.ideaplatform.domain.usecase

import com.nairi.ideaplatform.domain.model.Item
import com.nairi.ideaplatform.domain.repo.ItemsRepository
import kotlinx.coroutines.flow.Flow

class FindByNameUseCase(
    private val itemsRepository: ItemsRepository
) {
    suspend operator fun invoke(name: String): Flow<List<Item>> {
        return itemsRepository.findByName(name)
    }
}