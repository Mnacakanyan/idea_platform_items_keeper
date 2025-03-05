package com.nairi.ideaplatform.data.item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val tags: List<String>,
    val amount: Int,
    val time: Long
)
