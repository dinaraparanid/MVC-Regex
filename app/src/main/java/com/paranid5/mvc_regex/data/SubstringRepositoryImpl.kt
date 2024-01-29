package com.paranid5.mvc_regex.data

import com.paranid5.mvc_regex.use_cases.TakeInputUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubstringRepositoryImpl @Inject constructor() : SubstringRepository {
    private var matches = listOf<SubstringModel>()
    private var take = 0

    private inline val shownMatches: List<SubstringModel>
        get() = matches.let {
            when (take) {
                TakeInputUseCase.FULL_TAKE -> it
                else -> it.take(take)
            }
        }

    override fun matchSubstrings(
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

    override val matchedSubstringsAndTotal
        get() = shownMatches to matches.size
}