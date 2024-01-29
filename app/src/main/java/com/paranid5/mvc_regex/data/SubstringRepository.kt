package com.paranid5.mvc_regex.data

interface SubstringRepository {
    fun matchSubstrings(
        takeSubstrings: Int,
        regex: Regex,
        textInput: String,
    )

    val matchedSubstringsAndTotal: Pair<List<SubstringModel>, Int>
}