package com.nairi.ideaplatform.data.item

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM item")
    fun getAll(): Flow<List<ItemEntity>>

    @Query("SELECT * FROM item WHERE name LIKE '%' || :name || '%'")
    fun findByName(name: String): Flow<List<ItemEntity>>


    @Query("DELETE FROM item WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("UPDATE item SET amount = :item WHERE id = :id")
    suspend fun updateQuantity(id: Long, item: Int)

}