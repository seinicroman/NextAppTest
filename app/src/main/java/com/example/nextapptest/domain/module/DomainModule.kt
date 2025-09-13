package com.example.nextapptest.domain.module

import com.example.nextapptest.domain.repository.ThemesService
import com.example.nextapptest.domain.repository.ThemesServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun bindThemesRepository(impl : ThemesServiceImpl) : ThemesService
}