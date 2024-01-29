package com.paranid5.mvc_regex.views

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.paranid5.mvc_regex.MainActivity
import com.paranid5.mvc_regex.MainPresenter
import com.paranid5.mvc_regex.R

fun MainActivity.TakeInput(presenter: MainPresenter): EditText =
    findViewById<TextInputEditText>(R.id.take_input).apply {
        addTextChangedListener {
            val isOk = presenter.validateAndStoreTakeInput(it?.toString() ?: "")
            if (!isOk) error = getString(R.string.take_input_error)
        }
    }