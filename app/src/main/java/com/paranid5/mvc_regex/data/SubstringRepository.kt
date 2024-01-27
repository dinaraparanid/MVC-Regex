package com.paranid5.mvc_regex.data

import javax.inject.Inject
import javax.inject.Singleton

const val FULL_TAKE = -1

@Singleton
class SubstringRepository @Inject constructor() {
    private var matches = listOf<SubstringModel>()
    private var take = 0

    private inline val shownMatches: List<SubstringModel>
        get() = matches.let { if (take == FULL_TAKE) it else it.take(take) }

    fun matchSubstrings(
        takeSubstrings: Int,
        regex: Regex,
        textInput: String,
    ) {
        take = takeSubstrings

        matches = regex
            .findAll(textInput)
            .flatMap(MatchResult::groupValues)
            .filter(String::isNotBlank)
            .mapIndexed { index, match -> SubstringModel(match, index) }
            .toList()
    }

    val matchedSubstringsAndTotal: Pair<List<SubstringModel>, Int>
        get() = shownMatches to matches.size
}