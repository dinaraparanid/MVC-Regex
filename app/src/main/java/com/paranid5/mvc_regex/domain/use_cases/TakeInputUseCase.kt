package com.paranid5.mvc_regex.domain.use_cases

import com.paranid5.mvc_regex.data.MatchDataSource
import javax.inject.Inject

class TakeInputUseCase @Inject constructor(
    private val matchDataSource: MatchDataSource
) {
    companion object {
        const val FULL_TAKE = -1
    }

    private fun storeTakeInput(take: Int): Unit =
        matchDataSource.updateModel { it.copy(takeSubstrings = take) }

    var hasErrorInInput = false
        private set

    fun validateAndSetTakeInput(take: String): Boolean {
        if (take.isNotBlank() && !take.isCorrectNumber) {
            hasErrorInInput = true
            return false
        }

        hasErrorInInput = false
        storeTakeInput(take.toIntOrNull() ?: FULL_TAKE)
        return true
    }
}

private inline val String.isCorrectNumber
    get() = toIntOrNull()?.takeIf { it > 0 } != null