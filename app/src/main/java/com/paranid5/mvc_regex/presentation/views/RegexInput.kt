package com.paranid5.mvc_regex.presentation.views

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.paranid5.mvc_regex.presentation.MainActivity
import com.paranid5.mvc_regex.MainPresenter
import com.paranid5.mvc_regex.R

fun MainActivity.RegexInput(presenter: MainPresenter): EditText =
    findViewById<TextInputEditText>(R.id.regex_input).apply {
        addTextChangedListener {
            val isOk = presenter.validateAndStoreRegexInput(it?.toString() ?: ".*")
            if (!isOk) error = getString(R.string.regex_input_error)
        }
    }