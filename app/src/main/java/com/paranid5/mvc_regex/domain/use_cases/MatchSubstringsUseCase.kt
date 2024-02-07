package com.paranid5.mvc_regex.domain.use_cases

import com.paranid5.mvc_regex.domain.SubstringModel

const val FULL_TAKE = -1

class MatchSubstringsUseCase {
    fun matchSubstrings(
        takeSubstrings: Int,
        regex: Regex,
        textInput: String
    ): Pair<List<SubstringModel>, Int> {
        val allMatches = regex
            .findAll(textInput)
            .flatMap(MatchResult::groupValues)
            .filter(String::isNotBlank)
            .mapIndexed { index, match -> SubstringModel(match, index) }
            .toList()

        return allMatches.takeOrFull(takeSubstrings) to allMatches.size
    }
}

fun <T> Iterable<T>.takeOrFull(n: Int): List<T> =
    when (n) {
        FULL_TAKE -> toList()
        else -> take(n)
    }