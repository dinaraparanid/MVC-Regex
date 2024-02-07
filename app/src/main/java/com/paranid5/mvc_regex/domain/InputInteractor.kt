package com.paranid5.mvc_regex.domain

interface InputInteractor {
    val hasErrorInInput: Boolean

    fun validateTextInput(text: String): Boolean
    fun validateRegexInput(regex: String): Boolean
    fun validateTakeInput(take: String): Boolean

    fun matchSubstrings(
        takeSubstrings: Int,
        regex: Regex,
        textInput: String
    ): Pair<List<SubstringModel>, Int>
}