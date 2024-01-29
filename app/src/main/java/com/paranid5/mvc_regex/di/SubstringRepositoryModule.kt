package com.paranid5.mvc_regex.di

import com.paranid5.mvc_regex.data.SubstringRepository
import com.paranid5.mvc_regex.data.SubstringRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SubstringRepositoryModule {
    @Binds
    @Singleton
    fun bindSubstringRepository(repositoryImpl: SubstringRepositoryImpl): SubstringRepository
}