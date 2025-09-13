package com.example.nextapptest.domain.usecase

import com.example.nextapptest.domain.repository.ThemesService
import javax.inject.Inject


interface WasThemesAlreadyAddedToDbUseCase {
    suspend operator fun invoke(): Boolean
}

class WasThemesAlreadyAddedToDbUseCaseImp @Inject constructor(
    private val themesService: ThemesService
) : WasThemesAlreadyAddedToDbUseCase {

    override suspend fun invoke(): Boolean {
        return themesService.hasThemesAlreadyInserted()
    }
}