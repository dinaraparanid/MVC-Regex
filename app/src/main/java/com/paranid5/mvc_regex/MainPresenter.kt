package com.paranid5.mvc_regex

import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val inputInteractor: InputInteractor
) : MvpPresenter<MainView>() {
    private inline val hasErrorInInput
        get() = inputInteractor.hasErrorInInput

    fun validateAndStoreTextInput(text: String): Boolean =
        inputInteractor
            .validateAndSetTextInput(text)
            .also { updateErrorStateWithButton() }

    fun validateAndStoreRegexInput(regex: String): Boolean =
        inputInteractor
            .validateAndSetRegexInput(regex)
            .also { updateErrorStateWithButton() }

    fun validateAndStoreTakeInput(take: String): Boolean =
        inputInteractor
            .validateAndSetTakeInput(take)
            .also { updateErrorStateWithButton() }

    fun matchSubstringsAndRevalidate() {
        val (matchedList, totalMatches) = inputInteractor.matchSubstrings()
        viewState.revalidateMatches(matchedList, totalMatches)
    }

    private fun updateErrorStateWithButton() =
        viewState.revalidateButtonEnabled(!hasErrorInInput)
}
