package com.example.nextapptest.domain.usecase

import com.example.nextapptest.core.Status
import com.example.nextapptest.domain.model.ThemeModel
import com.example.nextapptest.domain.repository.ThemesService
import javax.inject.Inject


interface UpdateThemeInDbUseCase {
    suspend operator fun invoke(theme : ThemeModel): Status<Unit>
}

class UpdateThemeInDbUseCaseImp @Inject constructor(
    private val themesService: ThemesService
) : UpdateThemeInDbUseCase {

    override suspend fun invoke(theme : ThemeModel): Status<Unit>{
        return themesService.updateTheme(theme)
    }
}