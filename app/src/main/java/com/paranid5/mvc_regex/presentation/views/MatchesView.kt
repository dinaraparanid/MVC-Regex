package com.paranid5.mvc_regex.presentation.views

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paranid5.mvc_regex.presentation.MainActivity
import com.paranid5.mvc_regex.R

context(MainActivity)
fun MatchesView(matchesAdapter: MatchesAdapter): RecyclerView =
    findViewById<RecyclerView>(R.id.matches_view).apply {
        adapter = matchesAdapter.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }

        layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        addItemDecoration(
            PaddingItemDecorator(
                horizontalPadding = resources
                    .getDimension(R.dimen.recycler_view_item_padding)
                    .toInt()
            )
        )
    }