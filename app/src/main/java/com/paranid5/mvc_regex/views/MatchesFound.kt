package com.paranid5.mvc_regex.views

import android.widget.TextView
import com.paranid5.mvc_regex.MainActivity
import com.paranid5.mvc_regex.R

context(MainActivity)
fun MatchesFound(): TextView =
    findViewById(R.id.matches_found)