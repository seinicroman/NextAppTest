package com.example.nextapptest.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ThemeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun themesDao(): ThemesDao
}