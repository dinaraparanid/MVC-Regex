package com.paranid5.mvc_regex.views

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.paranid5.mvc_regex.MainActivity
import com.paranid5.mvc_regex.MainActivityViewModel
import com.paranid5.mvc_regex.R

fun MainActivity.TakeInput(viewModel: MainActivityViewModel): EditText =
    findViewById<TextInputEditText>(R.id.take_input).apply {
        addTextChangedListener {
            val isOk = viewModel.validateAndStoreTakeInput(
                take = it?.toString() ?: "",
                activity = this@TakeInput
            )

            if (!isOk) error = getString(R.string.take_input_error)
        }
    }