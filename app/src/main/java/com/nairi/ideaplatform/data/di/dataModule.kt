package com.nairi.ideaplatform.data.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nairi.ideaplatform.data.item.AppDatabase
import com.nairi.ideaplatform.data.repo.ItemsRepositoryImpl
import com.nairi.ideaplatform.domain.repo.ItemsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

private const val DATABASE_NAME = "items_bd"

val dataModule = module {
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AppDatabase::class.java,
            name = DATABASE_NAME
        )
            .addCallback(callback = get())
            .build()
    }
    single {
        get<AppDatabase>().itemDao()
    }

    single {
        ItemsRepositoryImpl(itemDao = get())
    }.bind(ItemsRepository::class)

    single<RoomDatabase.Callback> {
        object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                db.execSQL(
                    """
                INSERT INTO ItemEntity (id, name, date, desc, count) VALUES
                (1, 'iPhone 13', 1633046400000, 'Телефон, Новый, Распродажа', 15),
                (2, 'Samsung Galaxy S21', 1633132800000, 'Телефон, Хит', 30),
                (3, 'PlayStation 5', 1633219200000, 'Игровая приставка, Акция, Распродажа', 7),
                (4, 'LG OLED TV', 1633305600000, 'Телевизор, Эксклюзив, Ограниченный', 22),
                (5, 'Apple Watch Series 7', 1633392000000, 'Часы, Новый, Рекомендуем', 0),
                (6, 'Xiaomi Mi 11', 1633478400000, 'Телефон, Скидка, Распродажа', 5),
                (7, 'MacBook Air M1', 1633564800000, 'Ноутбук, Тренд', 12),
                (8, 'Amazon Kindle Paperwhite', 1633651200000, 'Электронная книга, Последний шанс, Ограниченный', 18),
                (9, 'Fitbit Charge 5', 1633737600000, '', 27),
                (10, 'GoPro Hero 10', 1633824000000, 'Камера, Эксклюзив', 25);
            """.trimIndent()
                )
            }
        }
    }
}