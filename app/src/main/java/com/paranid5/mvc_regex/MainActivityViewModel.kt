package com.paranid5.mvc_regex

import androidx.lifecycle.ViewModel
import com.paranid5.mvc_regex.data.SubstringRepository

private val ENGLISH_OR_DIGITS = Regex("[a-zA-Zа-яА-Я0-9,.;\\-\\s]*")

const val FULL_TAKE = -1

class MainActivityViewModel : ViewModel() {
    private val substringRepository by lazy {
        SubstringRepository()
    }

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

    infix fun matchSubstringsAndRevalidate(activity: MainActivity) {
        substringRepository.matchSubstrings(take, regex, textInput)
        activity revalidateMatches substringRepository
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