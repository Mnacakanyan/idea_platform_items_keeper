package com.nairi.ideaplatform.data.item

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ItemEntity::class], version = 1)
@TypeConverters(DescriptionConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun itemDao(): ItemDao
}