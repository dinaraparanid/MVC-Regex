package com.paranid5.mvc_regex.data

import com.paranid5.mvc_regex.domain.MatchModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MatchDataSourceImpl @Inject constructor() : MatchDataSource {
    companion object {
        private inline val initialModel
            get() = MatchModel(
                takeSubstrings = 0,
                regex = Regex(".*"),
                textInput = ""
            )
    }

    override var model: MatchModel = initialModel
        private set

    override fun updateModel(update: (current: MatchModel) -> MatchModel) {
        model = update(model)
    }
}