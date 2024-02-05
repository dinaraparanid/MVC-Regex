package com.paranid5.mvc_regex.domain

import com.paranid5.mvc_regex.domain.SubstringModel
import com.paranid5.mvc_regex.domain.use_cases.MatchSubstringsUseCase
import com.paranid5.mvc_regex.domain.use_cases.RegexInputUseCase
import com.paranid5.mvc_regex.domain.use_cases.TakeInputUseCase
import com.paranid5.mvc_regex.domain.use_cases.TextInputUseCase
import javax.inject.Inject

class InputInteractor @Inject constructor(
    private val textInputUseCase: TextInputUseCase,
    private val regexInputUseCase: RegexInputUseCase,
    private val takeInputUseCase: TakeInputUseCase,
    private val matchSubstringsUseCase: MatchSubstringsUseCase,
) {
    fun validateAndStoreTextInput(text: String): Boolean =
        textInputUseCase.validateAndSetTextInput(text)

    private inline val textHasError: Boolean
        get() = textInputUseCase.hasErrorInInput

    fun validateAndStoreRegexInput(regex: String): Boolean =
        regexInputUseCase.validateAndSetRegexInput(regex)

    private inline val regexHasError: Boolean
        get() = regexInputUseCase.hasErrorInInput

    fun validateAndStoreTakeInput(take: String): Boolean =
        takeInputUseCase.validateAndSetTakeInput(take)

    val shownMatchesList: List<SubstringModel>
        get() = matchSubstringsUseCase.shownMatchesList

    val totalMatches: Int
        get() = matchSubstringsUseCase.totalMatches

    fun matchSubstrings(): Unit =
        matchSubstringsUseCase.matchAndStoreSubstrings()

    private inline val takeHasError: Boolean
        get() = takeInputUseCase.hasErrorInInput

    val hasErrorInInput: Boolean
        get() = textHasError || regexHasError || takeHasError
}