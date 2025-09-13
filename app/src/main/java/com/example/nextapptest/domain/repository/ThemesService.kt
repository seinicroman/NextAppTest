package com.example.nextapptest.domain.repository

import com.example.nextapptest.core.Status
import com.example.nextapptest.domain.model.ThemeModel
import javax.inject.Singleton

@Singleton
interface ThemesService {

    suspend fun initializeThemesInDb(themes : List<ThemeModel>) : Status<Unit>

    suspend fun getAllThemes() : Status<List<ThemeModel>>

   suspend fun updateTheme(theme : ThemeModel) : Status<Unit>

    suspend fun hasThemesAlreadyInserted() : Boolean
}