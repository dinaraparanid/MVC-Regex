package com.paranid5.mvc_regex.domain.use_cases

class RegexInputUseCase {
    var hasErrorInInput = false
        private set

    fun validateRegexInput(regex: String): Boolean =
        runCatching { Regex(regex) }
            .map { true }
            .getOrDefault(false)
            .also { hasErrorInInput = !it }
}