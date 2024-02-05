package com.paranid5.mvc_regex

import com.paranid5.mvc_regex.domain.SubstringModel
import com.paranid5.mvc_regex.use_cases.MatchSubstringsUseCase
import com.paranid5.mvc_regex.use_cases.RegexInputUseCase
import com.paranid5.mvc_regex.use_cases.TakeInputUseCase
import com.paranid5.mvc_regex.use_cases.TextInputUseCase
import javax.inject.Inject

class InputInteractor @Inject constructor(
    private val textInputUseCase: TextInputUseCase,
    private val regexInputUseCase: RegexInputUseCase,
    private val takeInputUseCase: TakeInputUseCase,
    private val matchSubstringsUseCase: MatchSubstringsUseCase,
) {
    fun validateAndSetTextInput(text: String): Boolean =
        textInputUseCase.validateAndSetTextInput(text)

    private inline val textHasError: Boolean
        get() = textInputUseCase.hasErrorInInput

    fun validateAndSetRegexInput(regex: String): Boolean =
        regexInputUseCase.validateAndSetRegexInput(regex)

    private inline val regexHasError: Boolean
        get() = regexInputUseCase.hasErrorInInput

    fun validateAndSetTakeInput(take: String): Boolean =
        takeInputUseCase.validateAndSetTakeInput(take)

    fun matchSubstrings(): Pair<List<SubstringModel>, Int> =
        matchSubstringsUseCase.matchSubstrings()

    private inline val takeHasError: Boolean
        get() = takeInputUseCase.hasErrorInInput

    val hasErrorInInput: Boolean
        get() = textHasError || regexHasError || takeHasError
}