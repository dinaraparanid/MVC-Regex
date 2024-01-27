package com.paranid5.mvc_regex.di

import com.paranid5.mvc_regex.use_cases.ErrorUseCase
import com.paranid5.mvc_regex.use_cases.RegexInputUseCase
import com.paranid5.mvc_regex.use_cases.TakeInputUseCase
import com.paranid5.mvc_regex.use_cases.TextInputUseCase
import dagger.assisted.AssistedFactory

@AssistedFactory
interface ErrorUseCaseFactory {
    fun create(
        textInputUseCase: TextInputUseCase,
        regexInputUseCase: RegexInputUseCase,
        takeInputUseCase: TakeInputUseCase
    ): ErrorUseCase
}