package com.nairi.ideaplatform.data.item

import com.nairi.ideaplatform.domain.model.Item

fun List<ItemEntity>.toDomain(): List<Item> {
    return map { it.toDomain() }
}

fun ItemEntity.toDomain(): Item {
    return Item(
        id = id,
        name = name,
        desc = tags,
        count = amount,
        date = time,
    )
}