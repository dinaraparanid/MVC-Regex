package com.paranid5.mvc_regex.use_cases

import com.paranid5.mvc_regex.domain.SubstringModel
import com.paranid5.mvc_regex.data.MatchDataSource
import javax.inject.Inject

class MatchSubstringsUseCase @Inject constructor(
    private val matchDataSource: MatchDataSource
) {
    fun matchSubstrings(): Pair<List<SubstringModel>, Int> {
        val (takeSubstrings, regex, textInput) = matchDataSource.model

        val allMatches = regex
            .findAll(textInput)
            .flatMap(MatchResult::groupValues)
            .filter(String::isNotBlank)
            .mapIndexed { index, match -> SubstringModel(match, index) }
            .toList()

        return allMatches.take(takeSubstrings) to allMatches.size
    }
}