package com.paranid5.mvc_regex

import androidx.lifecycle.ViewModel
import com.paranid5.mvc_regex.data.MatchDataSource
import com.paranid5.mvc_regex.data.SubstringsDataSource
import com.paranid5.mvc_regex.domain.SubstringModel

private val ENGLISH_OR_DIGITS = Regex("[a-zA-Zа-яА-Я0-9,.;\\-\\s]*")

const val FULL_TAKE = -1

class MainActivityViewModel : ViewModel() {

    // ----------------- Text Input -----------------

    private var hasErrorInTextInput = false

    fun validateAndStoreTextInput(
        text: String,
        activity: MainActivity
    ): Boolean {
        if (!validateTextInput(text)) {
            hasErrorInTextInput = true
            updateErrorStateWithButton(activity)
            return false
        }

        MatchDataSource.updateModel {
            it.copy(textInput = text)
        }

        hasErrorInTextInput = false
        updateErrorStateWithButton(activity)
        return true
    }

    // ----------------- Regex Input -----------------

    private var hasErrorInRegexInput = false

    fun validateAndStoreRegexInput(
        regex: String,
        activity: MainActivity
    ): Boolean {
        if (!validateRegexInput(regex)) {
            hasErrorInRegexInput = true
            updateErrorStateWithButton(activity)
            return false
        }

        MatchDataSource.updateModel {
            it.copy(regex = regex.toRegex())
        }

        hasErrorInRegexInput = false
        updateErrorStateWithButton(activity)
        return true
    }

    // ----------------- Take Input -----------------

    private var hasErrorInTakeInput = false

    fun validateAndStoreTakeInput(
        take: String,
        activity: MainActivity
    ): Boolean {
        if (!validateTakeInput(take)) {
            hasErrorInTakeInput = true
            updateErrorStateWithButton(activity)
            return false
        }

        MatchDataSource.updateModel {
            it.copy(takeSubstrings = parseTakeInput(take))
        }

        hasErrorInTakeInput = false
        updateErrorStateWithButton(activity)
        return true
    }

    // ----------------- Substring match -----------------

    infix fun matchSubstringsAndRevalidate(activity: MainActivity) {
        val (shownMatchesList, totalMatches) = matchSubstrings()
        SubstringsDataSource.updateModel(shownMatchesList, totalMatches)
        updateMatches(activity)
    }

    private fun matchSubstrings(): Pair<List<SubstringModel>, Int>  {
        val (takeSubstrings, regex, textInput) = MatchDataSource.model

        val allMatches = regex
            .findAll(textInput)
            .flatMap(MatchResult::groupValues)
            .filter(String::isNotBlank)
            .mapIndexed { index, match -> SubstringModel(match, index) }
            .toList()

        return allMatches.take(takeSubstrings) to allMatches.size
    }

    // ----------------- View Update -----------------

    private inline val shownMatchesList: List<SubstringModel>
        get() = SubstringsDataSource.shownMatchesList

    private inline val totalMatches: Int
        get() = SubstringsDataSource.totalMatches

    private inline val hasErrorInInput
        get() = hasErrorInTextInput || hasErrorInRegexInput || hasErrorInTakeInput

    private fun updateErrorStateWithButton(activity: MainActivity): Unit =
        activity revalidateButtonEnabled !hasErrorInInput

    fun updateMatches(activity: MainActivity) {
        if (totalMatches > 0)
            activity.revalidateMatches(shownMatchesList, totalMatches)
    }
}

private fun validateTextInput(text: String): Boolean =
    text matches ENGLISH_OR_DIGITS

private fun validateRegexInput(regex: String): Boolean =
    runCatching { Regex(regex) }
        .map { true }
        .getOrDefault(false)

private fun validateTakeInput(take: String): Boolean =
    when {
        take.isBlank() -> true
        else -> take.toIntOrNull()?.takeIf { it > 0 } != null
    }

private fun parseTakeInput(take: String): Int =
    when {
        take.isBlank() -> null ?: FULL_TAKE
        else -> take.toIntOrNull()?.takeIf { it > 0 } ?: FULL_TAKE
    }