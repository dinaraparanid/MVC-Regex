package com.paranid5.mvc_regex

import com.paranid5.mvc_regex.domain.SubstringModel
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val inputInteractor: InputInteractor
) : MvpPresenter<MainView>() {
    private inline val hasErrorInInput
        get() = inputInteractor.hasErrorInInput

    fun validateAndStoreTextInput(text: String): Boolean =
        inputInteractor
            .validateAndStoreTextInput(text)
            .also { updateErrorStateWithButton() }

    fun validateAndStoreRegexInput(regex: String): Boolean =
        inputInteractor
            .validateAndStoreRegexInput(regex)
            .also { updateErrorStateWithButton() }

    fun validateAndStoreTakeInput(take: String): Boolean =
        inputInteractor
            .validateAndStoreTakeInput(take)
            .also { updateErrorStateWithButton() }

    fun matchSubstringsAndRevalidate() {
        inputInteractor.matchSubstrings()
        updateMatches()
    }

    private inline val shownMatchesList: List<SubstringModel>
        get() = inputInteractor.shownMatchesList

    private inline val totalMatches: Int
        get() = inputInteractor.totalMatches

    private fun updateErrorStateWithButton(): Unit =
        viewState.revalidateButtonEnabled(!hasErrorInInput)

    fun updateMatches(): Unit =
        viewState.revalidateMatches(shownMatchesList, totalMatches)
}
