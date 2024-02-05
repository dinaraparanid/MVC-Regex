package com.paranid5.mvc_regex.use_cases

import com.paranid5.mvc_regex.data.MatchDataSource
import javax.inject.Inject

class RegexInputUseCase @Inject constructor(
    private val matchDataSource: MatchDataSource
) {
    private fun storeRegex(regex: Regex): Unit =
        matchDataSource.updateModel { it.copy(regex = regex) }

    var hasErrorInInput = false
        private set

    fun validateAndSetRegexInput(regex: String): Boolean =
        runCatching { Regex(regex) }
            .onSuccess(::storeRegex)
            .map { true }
            .getOrDefault(false)
            .also { hasErrorInInput = !it }
}