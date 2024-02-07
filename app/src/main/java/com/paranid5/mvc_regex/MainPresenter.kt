package com.paranid5.mvc_regex

import com.paranid5.mvc_regex.data.MatchRepository
import com.paranid5.mvc_regex.domain.InputInteractor
import com.paranid5.mvc_regex.domain.SubstringModel
import com.paranid5.mvc_regex.domain.use_cases.FULL_TAKE
import com.paranid5.mvc_regex.presentation.MainView
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val inputInteractor: InputInteractor,
    private val matchRepository: MatchRepository
) : MvpPresenter<MainView>() {
    private inline val hasErrorInInput
        get() = inputInteractor.hasErrorInInput

    fun validateAndStoreTextInput(text: String): Boolean =
        inputInteractor
            .validateTextInput(text)
            .also {
                if (it) matchRepository.storeText(text)
                revalidateErrorStateWithButton()
            }

    fun validateAndStoreRegexInput(regex: String): Boolean =
        inputInteractor
            .validateRegexInput(regex)
            .also {
                if (it) matchRepository.storeRegex(regex.toRegex())
                revalidateErrorStateWithButton()
            }

    fun validateAndStoreTakeInput(take: String): Boolean =
        inputInteractor
            .validateTakeInput(take)
            .also {
                if (it) matchRepository.storeTake(take.toIntOrNull() ?: FULL_TAKE)
                revalidateErrorStateWithButton()
            }

    fun matchSubstringsAndRevalidate() {
        val (shownMatchesList, totalMatches) = inputInteractor.matchSubstrings(
            takeSubstrings = matchRepository.takeSubstrings,
            regex = matchRepository.regex,
            textInput = matchRepository.textInput
        )

        storeMatches(shownMatchesList, totalMatches)
        revalidateMatches(shownMatchesList, totalMatches)
    }

    private fun revalidateErrorStateWithButton(): Unit =
        viewState.revalidateButtonEnabled(!hasErrorInInput)

    private fun storeMatches(
        shownMatchesList: List<SubstringModel>,
        totalMatches: Int
    ): Unit = matchRepository.storeMatches(
        shownMatchesList = shownMatchesList,
        totalMatches = totalMatches
    )

    fun initMatchesView(): Unit =
        viewState.revalidateMatches(
            matchedList = matchRepository.shownMatchesList,
            totalMatches = matchRepository.totalMatches
        )

    private fun revalidateMatches(
        shownMatchesList: List<SubstringModel>,
        totalMatches: Int
    ): Unit = viewState.revalidateMatches(
        matchedList = shownMatchesList,
        totalMatches = totalMatches
    )
}
