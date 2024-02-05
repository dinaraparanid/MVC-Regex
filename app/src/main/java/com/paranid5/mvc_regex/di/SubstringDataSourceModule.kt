package com.paranid5.mvc_regex.di

import com.paranid5.mvc_regex.data.SubstringDataSource
import com.paranid5.mvc_regex.data.SubstringDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SubstringDataSourceModule {
    @Binds
    @Singleton
    fun bindSubstringDataSource(substringDataSourceImpl: SubstringDataSourceImpl): SubstringDataSource
}