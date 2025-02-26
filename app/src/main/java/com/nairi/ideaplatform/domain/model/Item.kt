package com.nairi.ideaplatform.domain.model

data class Item(
    val id: Long,
    val name: String,
    val desc: List<String>,
    val count: Int,
    val date: Long,
)
