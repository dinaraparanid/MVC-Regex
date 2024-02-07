package com.paranid5.mvc_regex.di

import com.paranid5.mvc_regex.data.MatchDataSource
import com.paranid5.mvc_regex.data.MatchDataSourceImpl
import com.paranid5.mvc_regex.data.MatchRepository
import com.paranid5.mvc_regex.data.MatchRepositoryImpl
import com.paranid5.mvc_regex.data.SubstringDataSource
import com.paranid5.mvc_regex.data.SubstringDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindMatchRepository(matchRepositoryImpl: MatchRepositoryImpl): MatchRepository

    @Binds
    @Singleton
    fun bindMatchDataSource(repositoryImpl: MatchDataSourceImpl): MatchDataSource

    @Binds
    @Singleton
    fun bindSubstringDataSource(substringDataSourceImpl: SubstringDataSourceImpl): SubstringDataSource
}