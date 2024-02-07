package com.paranid5.mvc_regex.domain.use_cases

class TakeInputUseCase {
    var hasErrorInInput = false
        private set

    fun validateTakeInput(take: String): Boolean {
        if (take.isNotBlank() && !take.isCorrectNumber) {
            hasErrorInInput = true
            return false
        }

        hasErrorInInput = false
        return true
    }
}

private inline val String.isCorrectNumber
    get() = toIntOrNull()?.takeIf { it > 0 } != null