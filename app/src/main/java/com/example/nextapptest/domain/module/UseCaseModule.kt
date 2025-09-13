package com.example.nextapptest.domain.module

import com.example.nextapptest.domain.usecase.GetAllThemesFromDbUseCase
import com.example.nextapptest.domain.usecase.GetAllThemesFromDbUseCaseImp
import com.example.nextapptest.domain.usecase.InitializeDataBaseThemesIfRequiredUseCase
import com.example.nextapptest.domain.usecase.InitializeDataBaseThemesIfRequiredUseCaseImp
import com.example.nextapptest.domain.usecase.UpdateThemeInDbUseCase
import com.example.nextapptest.domain.usecase.UpdateThemeInDbUseCaseImp
import com.example.nextapptest.domain.usecase.WasThemesAlreadyAddedToDbUseCase
import com.example.nextapptest.domain.usecase.WasThemesAlreadyAddedToDbUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun bindInitializeDataBaseThemesIfRequiredUseCase(
        initializeDataBaseThemesIfRequiredUseCaseImp: InitializeDataBaseThemesIfRequiredUseCaseImp
    ): InitializeDataBaseThemesIfRequiredUseCase

    @Singleton
    @Binds
    abstract fun bindGetAllThemesFromDbUseCase(
        getAllThemesFromDbUseCaseImp: GetAllThemesFromDbUseCaseImp
    ): GetAllThemesFromDbUseCase

    @Singleton
    @Binds
    abstract fun bindUpdateThemeInDbUseCase(
        updateThemeInDbUseCaseImp: UpdateThemeInDbUseCaseImp
    ): UpdateThemeInDbUseCase

    @Singleton
    @Binds
    abstract fun bindWasThemesAlreadyAddedToDbUseCase(
        wasThemesAlreadyAddedToDbUseCaseImp: WasThemesAlreadyAddedToDbUseCaseImp
    ): WasThemesAlreadyAddedToDbUseCase
}