package com.example.nextapptest.db

import com.example.nextapptest.domain.model.ThemeModel
import com.example.nextapptest.domain.mapper.toThemeModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ThemeEntityDataSource @Inject constructor(
    private val themesDao: ThemesDao
) {

    suspend fun insertAllThemes(themes: List<ThemeEntity>) {
        themesDao.insertAllThemes(themes)
    }

    suspend fun getAllThemes(): List<ThemeModel> {
        return themesDao.getAllThemes().map {
            it.toThemeModel()
        }
    }

    suspend fun updateTheme(themeEntity: ThemeEntity) {
        themesDao.update(themeEntity)
    }

    suspend fun hasThemesAlreadyInserted() : Boolean{
        return themesDao.hasThemesAlreadyInserted()
    }
}