package com.paranid5.mvc_regex.domain

import com.paranid5.mvc_regex.domain.use_cases.MatchSubstringsUseCase
import com.paranid5.mvc_regex.domain.use_cases.RegexInputUseCase
import com.paranid5.mvc_regex.domain.use_cases.TakeInputUseCase
import com.paranid5.mvc_regex.domain.use_cases.TextInputUseCase
import javax.inject.Inject

class InputInteractorImpl @Inject constructor() : InputInteractor {
    private val textInputUseCase by lazy {
        TextInputUseCase()
    }

    private val regexInputUseCase by lazy {
        RegexInputUseCase()
    }

    private val takeInputUseCase by lazy {
        TakeInputUseCase()
    }

    private val matchSubstringsUseCase by lazy {
        MatchSubstringsUseCase()
    }

    override fun validateTextInput(text: String): Boolean =
        textInputUseCase.validateTextInput(text)

    private inline val textHasError: Boolean
        get() = textInputUseCase.hasErrorInInput

    override fun validateRegexInput(regex: String): Boolean =
        regexInputUseCase.validateRegexInput(regex)

    private inline val regexHasError: Boolean
        get() = regexInputUseCase.hasErrorInInput

    override fun validateTakeInput(take: String): Boolean =
        takeInputUseCase.validateTakeInput(take)

    override fun matchSubstrings(
        takeSubstrings: Int,
        regex: Regex,
        textInput: String
    ): Pair<List<SubstringModel>, Int> =
        matchSubstringsUseCase.matchSubstrings(
            takeSubstrings = takeSubstrings,
            regex = regex,
            textInput = textInput
        )

    private inline val takeHasError: Boolean
        get() = takeInputUseCase.hasErrorInInput

    override val hasErrorInInput: Boolean
        get() = textHasError || regexHasError || takeHasError
}