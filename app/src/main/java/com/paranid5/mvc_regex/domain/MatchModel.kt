package com.paranid5.mvc_regex.domain

data class MatchModel(
    val takeSubstrings: Int,
    val regex: Regex,
    val textInput: String,
)