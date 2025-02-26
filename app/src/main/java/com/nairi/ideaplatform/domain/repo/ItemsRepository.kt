package com.nairi.ideaplatform.domain.repo

import com.nairi.ideaplatform.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {

    suspend fun getAllItems(): Flow<List<Item>>

    suspend fun findByName(name: String): Flow<List<Item>>

    suspend fun deleteItem(id: Long)

    suspend fun updateItemQuantity(id: Long, item: Int)
}