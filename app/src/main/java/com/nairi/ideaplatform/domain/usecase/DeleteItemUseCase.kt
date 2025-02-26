package com.nairi.ideaplatform.domain.usecase

import com.nairi.ideaplatform.domain.repo.ItemsRepository

class DeleteItemUseCase(
    private val itemRepository: ItemsRepository
) {

    suspend operator fun invoke(id: Long) {
        itemRepository.deleteItem(id)
    }
}