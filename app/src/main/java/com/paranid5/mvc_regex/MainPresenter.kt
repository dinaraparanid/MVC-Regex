package com.paranid5.mvc_regex

import com.paranid5.mvc_regex.data.SubstringRepository
import com.paranid5.mvc_regex.di.ErrorUseCaseFactory
import com.paranid5.mvc_regex.use_cases.RegexInputUseCase
import com.paranid5.mvc_regex.use_cases.TakeInputUseCase
import com.paranid5.mvc_regex.use_cases.TextInputUseCase
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val textInputUseCase: TextInputUseCase,
    private val regexInputUseCase: RegexInputUseCase,
    private val takeInputUseCase: TakeInputUseCase,
    private val errorUseCaseFactory: ErrorUseCaseFactory,
    private val repository: SubstringRepository
) : MvpPresenter<MainView>() {
    private val errorUseCase by lazy {
        errorUseCaseFactory.create(
            textInputUseCase,
            regexInputUseCase,
            takeInputUseCase
        )
    }

    private inline val textInput
        get() = textInputUseCase.textInput

    private inline val regex
        get() = regexInputUseCase.parseRegex()

    private inline val take
        get() = takeInputUseCase.parseTakeInput()

    private inline val hasErrorInInput
        get() = errorUseCase.hasErrorInInput

    fun validateAndStoreTextInput(text: String): Boolean =
        textInputUseCase
            .validateAndSetTextInput(text)
            .also { updateErrorStateWithButton() }

    fun validateAndStoreRegexInput(regex: String): Boolean =
        regexInputUseCase
            .validateAndSetRegexInput(regex)
            .also { updateErrorStateWithButton() }

    fun validateAndStoreTakeInput(take: String): Boolean =
        takeInputUseCase
            .validateAndSetTakeInput(take)
            .also { updateErrorStateWithButton() }

    fun matchSubstringsAndRevalidate() {
        repository.matchSubstrings(take, regex, textInput)
        val (matchedList, totalMatches) = repository.matchedSubstringsAndTotal
        viewState.revalidateMatches(matchedList, totalMatches)
    }

    private fun updateErrorStateWithButton() {
        viewState.revalidateButtonEnabled(!hasErrorInInput)
    }
}
