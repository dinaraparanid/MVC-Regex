package com.paranid5.mvc_regex.use_cases

import com.paranid5.mvc_regex.data.FULL_TAKE
import javax.inject.Inject

class TakeInputUseCase @Inject constructor() {
    private var takeInput = ""

    var hasErrorInInput = false
        private set

    fun validateAndSetTakeInput(take: String): Boolean =
        (take.isBlank() || take.isCorrectNumber).also {
            hasErrorInInput = !it
            takeInput = take
        }

    fun parseTakeInput(): Int =
        when {
            takeInput.isBlank() -> null ?: FULL_TAKE
            else -> takeInput.toIntOrNull()?.takeIf { it > 0 } ?: FULL_TAKE
        }
}

private inline val String.isCorrectNumber
    get() = toIntOrNull()?.takeIf { it > 0 } != null