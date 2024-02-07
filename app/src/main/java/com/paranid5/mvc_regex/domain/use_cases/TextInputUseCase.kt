package com.paranid5.mvc_regex.domain.use_cases

private val ENGLISH_OR_DIGITS = Regex("[a-zA-Zа-яА-Я0-9,.;\\-\\s]*")

class TextInputUseCase {
    var hasErrorInInput = false
        private set

    fun validateTextInput(text: String): Boolean {
        if (!(text matches ENGLISH_OR_DIGITS)) {
            hasErrorInInput = true
            return false
        }

        hasErrorInInput = false
        return true
    }
}