package com.paranid5.mvc_regex.views

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paranid5.mvc_regex.MainActivity
import com.paranid5.mvc_regex.R

context(MainActivity)
fun MatchesView(matchesAdapter: MatchesAdapter): RecyclerView =
    findViewById<RecyclerView>(R.id.matches_view).apply {
        adapter = matchesAdapter

        layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        addItemDecoration(PaddingItemDecorator(horizontalPadding = 10))
    }