package com.paranid5.mvc_regex.ui

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

fun MainActivity.TakeInput(viewModel: MainActivityViewModel): EditText =
    findViewById<TextInputEditText>(R.id.take_input).apply {
        addTextChangedListener {
            val isOk = viewModel.validateAndSetTakeInput(
                take = it?.toString() ?: "",
                activity = this@TakeInput
            )

            if (!isOk) error = getString(R.string.take_input_error)
        }
    }

fun MainActivity.FindButton(viewModel: MainActivityViewModel): Button =
    findViewById<Button>(R.id.find_button).apply {
        setOnClickListener {
            viewModel matchSubstringsAndRevalidate this@FindButton
        }
    }

context(MainActivity)
fun MatchesView(matchesAdapter: MatchesAdapter): RecyclerView =
    findViewById<RecyclerView>(R.id.matches_view).apply {
        adapter = matchesAdapter

        layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        addItemDecoration(PaddingItemDecorator(horizontalPadding = 10))
    }

context(MainActivity)
fun MatchesFound(): TextView =
    findViewById(R.id.matches_found)