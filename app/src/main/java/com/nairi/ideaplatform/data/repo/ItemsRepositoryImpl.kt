package com.nairi.ideaplatform.data.repo

import com.nairi.ideaplatform.data.item.ItemDao
import com.nairi.ideaplatform.data.item.toDomain
import com.nairi.ideaplatform.domain.model.Item
import com.nairi.ideaplatform.domain.repo.ItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ItemsRepositoryImpl(
    private val itemDao: ItemDao
): ItemsRepository {
    override suspend fun getAllItems(): Flow<List<Item>> {
        return itemDao.getAll().map { it.toDomain() }
    }

    override suspend fun findByName(name: String): Flow<List<Item>> {
        return itemDao.findByName(name).map { it.toDomain() }
    }

    override suspend fun deleteItem(id: Long) {
        itemDao.deleteById(id)
    }

    override suspend fun updateItemQuantity(id: Long, item: Int) {
        itemDao.updateQuantity(id, item)
    }

}