package com.paranid5.mvc_regex.presentation.views

import android.widget.Button
import com.paranid5.mvc_regex.presentation.MainActivity
import com.paranid5.mvc_regex.MainPresenter
import com.paranid5.mvc_regex.R

fun MainActivity.FindButton(presenter: MainPresenter): Button =
    findViewById<Button>(R.id.find_button).apply {
        setOnClickListener { presenter.matchSubstringsAndRevalidate() }
    }