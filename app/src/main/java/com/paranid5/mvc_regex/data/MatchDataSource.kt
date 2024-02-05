package com.paranid5.mvc_regex.data

import com.paranid5.mvc_regex.domain.MatchModel

object MatchDataSource {
    private inline val initialModel
        get() = MatchModel(
            takeSubstrings = 0,
            regex = Regex(".*"),
            textInput = ""
        )

    var model: MatchModel = initialModel
        private set

    fun updateModel(update: (current: MatchModel) -> MatchModel) {
        model = update(model)
    }
}