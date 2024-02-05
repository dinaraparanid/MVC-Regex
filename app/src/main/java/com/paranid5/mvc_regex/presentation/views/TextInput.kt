package com.paranid5.mvc_regex.presentation.views

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.paranid5.mvc_regex.presentation.MainActivity
import com.paranid5.mvc_regex.MainPresenter
import com.paranid5.mvc_regex.R

fun MainActivity.TextInput(presenter: MainPresenter): EditText =
    findViewById<TextInputEditText>(R.id.text_input).apply {
        addTextChangedListener {
            val isOk = presenter.validateAndStoreTextInput(it?.toString() ?: "")
            if (!isOk) error = getString(R.string.text_input_error)
        }
    }