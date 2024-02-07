package com.paranid5.mvc_regex.di

import com.paranid5.mvc_regex.domain.InputInteractor
import com.paranid5.mvc_regex.domain.InputInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    @Singleton
    fun bindInputInteractor(inputInteractorImpl: InputInteractorImpl): InputInteractor
}