package com.nairi.ideaplatform.domain.usecase

import com.nairi.ideaplatform.domain.repo.ItemsRepository

class ChangeQuantityUseCase(
    private val itemsRepository: ItemsRepository
) {

    suspend operator fun invoke(id: Long, item: Int) {
        itemsRepository.updateItemQuantity(id, item)
    }

}