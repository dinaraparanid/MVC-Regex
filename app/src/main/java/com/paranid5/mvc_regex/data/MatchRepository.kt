package com.paranid5.mvc_regex.data

import com.paranid5.mvc_regex.domain.SubstringModel

interface MatchRepository {
    val textInput: String
    val regex: Regex
    val takeSubstrings: Int

    val shownMatchesList: List<SubstringModel>
    val totalMatches: Int

    fun storeText(textInput: String)
    fun storeRegex(regex: Regex)
    fun storeTake(take: Int)

    fun storeMatches(shownMatchesList: List<SubstringModel>, totalMatches: Int)
}