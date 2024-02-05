package com.paranid5.mvc_regex.data

import com.paranid5.mvc_regex.domain.SubstringModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubstringDataSourceImpl @Inject constructor() : SubstringDataSource {
    override var shownMatchesList: List<SubstringModel> = emptyList()
        private set

    override var totalMatches: Int = 0
        private set

    override fun updateModel(shownMatchesList: List<SubstringModel>, totalMatches: Int) {
        this.shownMatchesList = shownMatchesList
        this.totalMatches = totalMatches
    }
}