package com.paranid5.mvc_regex.domain.use_cases

import com.paranid5.mvc_regex.data.MatchDataSource
import javax.inject.Inject

private val ENGLISH_OR_DIGITS = Regex("[a-zA-Zа-яА-Я0-9,.;\\-\\s]*")

class TextInputUseCase @Inject constructor(
    private val matchDataSource: MatchDataSource
) {
    private fun storeTextInput(text: String): Unit =
        matchDataSource.updateModel { it.copy(textInput = text) }

    var hasErrorInInput = false
        private set

    fun validateAndSetTextInput(text: String): Boolean {
        if (!(text matches ENGLISH_OR_DIGITS)) {
            hasErrorInInput = true
            return false
        }

        hasErrorInInput = false
        storeTextInput(text)
        return true
    }
}