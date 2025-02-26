package com.nairi.ideaplatform.data.item

import androidx.room.TypeConverter

class DescriptionConverter {

    @TypeConverter
    fun fromDescription(description: List<String>): String {
        return description.joinToString(",")
    }

    @TypeConverter
    fun toDescription(description: String): List<String> {
        if (description.isEmpty()) return emptyList()
        return description.split(",")
    }

}