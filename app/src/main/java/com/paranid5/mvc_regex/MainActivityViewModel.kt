package com.paranid5.mvc_regex

import androidx.lifecycle.ViewModel

private val ENGLISH_OR_DIGITS = Regex("[a-zA-Zа-яА-Я0-9,.;\\-\\s]*")

private const val FULL_TAKE = -1

class MainActivityViewModel : ViewModel() {
    private var textInput = ""

    fun validateAndStoreTextInput(
        text: String,
        activity: MainActivity
    ): Boolean = validateTextInput(text).also {
        textInput = text
        hasErrorInInput = updatedErrorState(it)
        activity revalidateButtonEnabled it
    }

    private var regexInput = ".*"

    private inline val regex
        get() = regexInput.toRegex()

    fun validateAndStoreRegexInput(
        regex: String,
        activity: MainActivity
    ): Boolean = validateRegexInput(regex).also {
        regexInput = regex
        hasErrorInInput = updatedErrorState(it)
        activity revalidateButtonEnabled it
    }

    private var takeInput = ""

    private inline val take
        get() = parseTakeInput(takeInput)

    fun validateAndSetTakeInput(
        take: String,
        activity: MainActivity
    ): Boolean = validateTakeInput(take).also {
        takeInput = take
        hasErrorInInput = updatedErrorState(it)
        activity revalidateButtonEnabled it
    }

    private var hasErrorInInput = false

    private fun updatedErrorState(isErrorInInput: Boolean): Boolean =
        if (isErrorInInput) hasErrorInInput else false

    private var matches = listOf<SubstringModel>()

    infix fun matchSubstringsAndRevalidate(activity: MainActivity) {
        fun takeMatches(): List<SubstringModel> =
            matches.let { if (take == FULL_TAKE) it else it.take(take) }

        matches = regex
            .findAll(textInput)
            .flatMap { it.groupValues }
            .filter { it.isNotBlank() }
            .mapIndexed { index, match -> SubstringModel(match, index) }
            .toList()

        activity.revalidateMatches(takeMatches(), matches.size)
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