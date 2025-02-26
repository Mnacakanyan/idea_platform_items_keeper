package com.nairi.ideaplatform.data.item

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM ItemEntity")
    fun getAll(): Flow<List<ItemEntity>>

    @Query("SELECT * FROM ItemEntity WHERE name LIKE '%' || :name || '%'")
    fun findByName(name: String): Flow<List<ItemEntity>>


    @Query("DELETE FROM ItemEntity WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("UPDATE ItemEntity SET count = :item WHERE id = :id")
    suspend fun updateQuantity(id: Long, item: Int)

}