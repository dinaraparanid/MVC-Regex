package com.paranid5.mvc_regex.data

import com.paranid5.mvc_regex.domain.SubstringModel

object SubstringsDataSource {
    var shownMatchesList: List<SubstringModel> = emptyList()
        private set

    var totalMatches: Int = 0
        private set

    fun updateModel(shownMatchesList: List<SubstringModel>, totalMatches: Int) {
        this.shownMatchesList = shownMatchesList
        this.totalMatches = totalMatches
    }
}