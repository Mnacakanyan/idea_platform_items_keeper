package com.nairi.ideaplatform.data.item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val desc: List<String>,
    val count: Int,
    val date: Long,
)
