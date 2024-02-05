package com.paranid5.mvc_regex.views

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.paranid5.mvc_regex.MainActivity
import com.paranid5.mvc_regex.MainActivityViewModel
import com.paranid5.mvc_regex.R

fun MainActivity.TextInput(viewModel: MainActivityViewModel): EditText =
    findViewById<TextInputEditText>(R.id.text_input).apply {
        addTextChangedListener {
            val isOk = viewModel.validateAndStoreTextInput(
                text = it?.toString() ?: "",
                activity = this@TextInput
            )

            if (!isOk) error = getString(R.string.text_input_error)
        }
    }