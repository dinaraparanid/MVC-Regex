package com.paranid5.mvc_regex.ui

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.paranid5.mvc_regex.MainActivity
import com.paranid5.mvc_regex.MainPresenter
import com.paranid5.mvc_regex.R

fun MainActivity.TextInput(presenter: MainPresenter): EditText =
    findViewById<TextInputEditText>(R.id.text_input).apply {
        addTextChangedListener {
            val isOk = presenter.validateAndStoreTextInput(it?.toString() ?: "")
            if (!isOk) error = getString(R.string.text_input_error)
        }
    }

fun MainActivity.RegexInput(presenter: MainPresenter): EditText =
    findViewById<TextInputEditText>(R.id.regex_input).apply {
        addTextChangedListener {
            val isOk = presenter.validateAndStoreRegexInput(it?.toString() ?: ".*")
            if (!isOk) error = getString(R.string.regex_input_error)
        }
    }

fun MainActivity.TakeInput(presenter: MainPresenter): EditText =
    findViewById<TextInputEditText>(R.id.take_input).apply {
        addTextChangedListener {
            val isOk = presenter.validateAndStoreTakeInput(it?.toString() ?: "")
            if (!isOk) error = getString(R.string.take_input_error)
        }
    }

fun MainActivity.FindButton(presenter: MainPresenter): Button =
    findViewById<Button>(R.id.find_button).apply {
        setOnClickListener { presenter.matchSubstringsAndRevalidate() }
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