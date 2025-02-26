package com.nairi.ideaplatform.domain.usecase

import com.nairi.ideaplatform.domain.model.Item
import com.nairi.ideaplatform.domain.repo.ItemsRepository
import kotlinx.coroutines.flow.Flow

class GetAllItemsUseCase(
    private val itemsRepository: ItemsRepository
) {

    suspend operator fun invoke(): Flow<List<Item>> {
        return itemsRepository.getAllItems()
    }
}