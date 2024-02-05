package com.paranid5.mvc_regex.data

import com.paranid5.mvc_regex.domain.MatchModel

interface MatchDataSource {
    val model: MatchModel

    fun updateModel(update: (current: MatchModel) -> MatchModel)
}