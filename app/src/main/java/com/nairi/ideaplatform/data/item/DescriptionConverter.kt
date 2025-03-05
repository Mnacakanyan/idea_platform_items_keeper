package com.nairi.ideaplatform.data.item

import androidx.room.TypeConverter

class DescriptionConverter {

    @TypeConverter
    fun fromDescription(description: List<String>): String {
        return description.joinToString(",") { "\"$it\"" }
            .let { "[$it]" }
    }

    @TypeConverter
    fun toDescription(description: String): List<String> {
        val tags = description.removeSurrounding("[", "]")
        if (tags.isEmpty()) return emptyList()
        return tags
            .split(",")
            .map { it.trim().removeSurrounding("\"") }
    }

}