package com.example.nextapptest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ThemesDao {

    @Query("SELECT * FROM themes ORDER BY id DESC")
    suspend fun getAllThemes(): List<ThemeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: ThemeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllThemes(list : List<ThemeEntity>)

    @Query("SELECT EXISTS(SELECT 1 FROM themes WHERE baseWasInited = 1 LIMIT 1)")
    suspend fun hasThemesAlreadyInserted(): Boolean
}