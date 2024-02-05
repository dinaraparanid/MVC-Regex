package com.paranid5.mvc_regex.di

import com.paranid5.mvc_regex.data.MatchDataSource
import com.paranid5.mvc_regex.data.MatchDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MatchDataSourceModule {
    @Binds
    @Singleton
    fun bindMatchDataSource(repositoryImpl: MatchDataSourceImpl): MatchDataSource
}