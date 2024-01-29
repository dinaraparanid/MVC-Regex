package com.paranid5.mvc_regex.use_cases

import com.paranid5.mvc_regex.data.SubstringModel
import com.paranid5.mvc_regex.data.SubstringRepository
import javax.inject.Inject

class MatchSubstringsUseCase @Inject constructor(
    private val repository: SubstringRepository
) {
    fun matchSubstrings(
        takeSubstrings: Int,
        regex: Regex,
        textInput: String
    ): Pair<List<SubstringModel>, Int> {
        repository.matchSubstrings(takeSubstrings, regex, textInput)
        return repository.matchedSubstringsAndTotal
    }
}