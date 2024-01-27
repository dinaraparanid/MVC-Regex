package com.paranid5.mvc_regex.use_cases

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class ErrorUseCase @AssistedInject constructor(
    @Assisted val textInputUseCase: TextInputUseCase,
    @Assisted val regexInputUseCase: RegexInputUseCase,
    @Assisted val takeInputUseCase: TakeInputUseCase
) {
    private inline val textHasError
        get() = textInputUseCase.hasErrorInInput

    private inline val regexHasError
        get() = regexInputUseCase.hasErrorInInput

    private inline val takeHasError
        get() = takeInputUseCase.hasErrorInInput

    val hasErrorInInput
        get() = textHasError || regexHasError || takeHasError
}