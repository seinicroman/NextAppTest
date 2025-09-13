package com.example.nextapptest.domain.usecase

import com.example.nextapptest.core.Status
import com.example.nextapptest.domain.model.ThemeModel
import com.example.nextapptest.domain.repository.ThemesService
import javax.inject.Inject


interface GetAllThemesFromDbUseCase {
    suspend operator fun invoke(): Status<List<ThemeModel>>
}

class GetAllThemesFromDbUseCaseImp @Inject constructor(
    private val themesService: ThemesService
) : GetAllThemesFromDbUseCase {

    override suspend fun invoke(): Status<List<ThemeModel>> {
        return themesService.getAllThemes()
    }
}