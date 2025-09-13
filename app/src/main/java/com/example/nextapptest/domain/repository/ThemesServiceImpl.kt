package com.example.nextapptest.domain.repository

import com.example.nextapptest.core.Status
import com.example.nextapptest.db.ThemeEntityDataSource
import com.example.nextapptest.domain.mapper.toThemeEntity
import com.example.nextapptest.domain.model.ThemeModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ThemesServiceImpl @Inject constructor(
    private val themeEntityDataSource: ThemeEntityDataSource
) : ThemesService {

    override suspend fun initializeThemesInDb(themes: List<ThemeModel>): Status<Unit> {
        return try {
            themeEntityDataSource.insertAllThemes(
                themes.map {
                    it.toThemeEntity()
                }
            )
            Status.Success(Unit)
        } catch (e: Exception) {
            Status.Error(e)
        }
    }

    override suspend fun getAllThemes(): Status<List<ThemeModel>> {
        return try {
            val result = themeEntityDataSource.getAllThemes()
            Status.Success(result)
        } catch (e: Exception) {
            Status.Error(e)
        }
    }

    override suspend fun updateTheme(theme: ThemeModel): Status<Unit> {
        return try {
            themeEntityDataSource.updateTheme(theme.toThemeEntity())
            Status.Success(Unit)
        } catch (e: Exception) {
            Status.Error(e)
        }
    }

    override suspend fun hasThemesAlreadyInserted(): Boolean {
        return themeEntityDataSource.hasThemesAlreadyInserted()
    }
}