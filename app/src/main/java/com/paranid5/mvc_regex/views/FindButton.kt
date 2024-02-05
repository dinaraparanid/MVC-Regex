package com.paranid5.mvc_regex.views

import android.widget.Button
import com.paranid5.mvc_regex.MainActivity
import com.paranid5.mvc_regex.MainActivityViewModel
import com.paranid5.mvc_regex.R

fun MainActivity.FindButton(viewModel: MainActivityViewModel): Button =
    findViewById<Button>(R.id.find_button).apply {
        setOnClickListener {
            viewModel matchSubstringsAndRevalidate this@FindButton
        }
    }