package com.paranid5.mvc_regex.use_cases

import javax.inject.Inject

class RegexInputUseCase @Inject constructor() {
    private var regexInput = ".*"

    var hasErrorInInput = false
        private set

    fun validateAndSetRegexInput(regex: String): Boolean =
        runCatching { Regex(regex) }
            .onSuccess { regexInput = regex }
            .map { true }
            .getOrDefault(false)
            .also { hasErrorInInput = !it }

    fun parseRegex() =
        regexInput.toRegex()
}