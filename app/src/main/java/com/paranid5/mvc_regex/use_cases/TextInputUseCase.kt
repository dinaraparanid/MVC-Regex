package com.paranid5.mvc_regex.use_cases

import javax.inject.Inject

private val ENGLISH_OR_DIGITS = Regex("[a-zA-Zа-яА-Я0-9,.;\\-\\s]*")

class TextInputUseCase @Inject constructor() {
    var textInput = ""
        private set

    var hasErrorInInput = false
        private set

    fun validateAndSetTextInput(text: String): Boolean =
        (text matches ENGLISH_OR_DIGITS).also {
            hasErrorInInput = !it
            textInput = text
        }
}