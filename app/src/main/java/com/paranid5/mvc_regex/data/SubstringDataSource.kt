package com.paranid5.mvc_regex.data

import com.paranid5.mvc_regex.domain.SubstringModel

interface SubstringDataSource {
    val shownMatchesList: List<SubstringModel>
    val totalMatches: Int

    fun updateModel(shownMatchesList: List<SubstringModel>, totalMatches: Int)
}