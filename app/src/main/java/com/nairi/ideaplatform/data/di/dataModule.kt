package com.nairi.ideaplatform.data.di

import androidx.room.Room
import com.nairi.ideaplatform.data.item.AppDatabase
import com.nairi.ideaplatform.data.repo.ItemsRepositoryImpl
import com.nairi.ideaplatform.domain.repo.ItemsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

private const val DATABASE_NAME = "items_db"

val dataModule = module {
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AppDatabase::class.java,
            name = DATABASE_NAME
        )
            .createFromAsset("data.db")
            .build()
    }
    single {
        get<AppDatabase>().itemDao()
    }

    single {
        ItemsRepositoryImpl(itemDao = get())
    }.bind(ItemsRepository::class)
}