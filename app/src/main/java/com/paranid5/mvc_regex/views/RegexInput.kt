package com.paranid5.mvc_regex.views

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.paranid5.mvc_regex.MainActivity
import com.paranid5.mvc_regex.MainActivityViewModel
import com.paranid5.mvc_regex.R

fun MainActivity.RegexInput(viewModel: MainActivityViewModel): EditText =
    findViewById<TextInputEditText>(R.id.regex_input).apply {
        addTextChangedListener {
            val isOk = viewModel.validateAndStoreRegexInput(
                regex = it?.toString() ?: ".*",
                activity = this@RegexInput
            )

            if (!isOk) error = getString(R.string.regex_input_error)
        }
    }