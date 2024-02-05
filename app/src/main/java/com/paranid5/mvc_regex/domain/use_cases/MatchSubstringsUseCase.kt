package com.paranid5.mvc_regex.domain.use_cases

import com.paranid5.mvc_regex.domain.SubstringModel
import com.paranid5.mvc_regex.data.MatchDataSource
import com.paranid5.mvc_regex.data.SubstringDataSource
import javax.inject.Inject

class MatchSubstringsUseCase @Inject constructor(
    private val matchDataSource: MatchDataSource,
    private val substringDataSource: SubstringDataSource
) {
    val shownMatchesList: List<SubstringModel>
        get() = substringDataSource.shownMatchesList

    val totalMatches: Int
        get() = substringDataSource.totalMatches

    private fun matchSubstrings(): Pair<List<SubstringModel>, Int> {
        val (takeSubstrings, regex, textInput) = matchDataSource.model

        val allMatches = regex
            .findAll(textInput)
            .flatMap(MatchResult::groupValues)
            .filter(String::isNotBlank)
            .mapIndexed { index, match -> SubstringModel(match, index) }
            .toList()

        return allMatches.take(takeSubstrings) to allMatches.size
    }

    fun matchAndStoreSubstrings() {
        val (shownMatchesList, totalMatches) = matchSubstrings()
        substringDataSource.updateModel(shownMatchesList, totalMatches)
    }
}