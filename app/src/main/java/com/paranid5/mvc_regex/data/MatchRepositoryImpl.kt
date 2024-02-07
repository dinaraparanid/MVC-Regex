package com.paranid5.mvc_regex.data

import com.paranid5.mvc_regex.domain.SubstringModel
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val matchDataSource: MatchDataSource,
    private val substringDataSource: SubstringDataSource,
) : MatchRepository {
    override val textInput: String
        get() = matchDataSource.model.textInput

    override val regex: Regex
        get() = matchDataSource.model.regex

    override val takeSubstrings: Int
        get() = matchDataSource.model.takeSubstrings

    override val shownMatchesList: List<SubstringModel>
        get() = substringDataSource.shownMatchesList

    override val totalMatches: Int
        get() = substringDataSource.totalMatches

    override fun storeText(textInput: String): Unit =
        matchDataSource.updateModel { it.copy(textInput = textInput) }

    override fun storeRegex(regex: Regex): Unit =
        matchDataSource.updateModel { it.copy(regex = regex) }

    override fun storeTake(take: Int): Unit =
        matchDataSource.updateModel { it.copy(takeSubstrings = take) }

    override fun storeMatches(shownMatchesList: List<SubstringModel>, totalMatches: Int): Unit =
        substringDataSource.updateModel(shownMatchesList, totalMatches)
}