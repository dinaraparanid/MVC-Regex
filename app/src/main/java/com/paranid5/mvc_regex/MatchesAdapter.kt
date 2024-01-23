package com.paranid5.mvc_regex

import androidx.recyclerview.widget.DiffUtil

fun DiffCallback() =
    object : DiffUtil.ItemCallback<SubstringModel>() {
        override fun areItemsTheSame(oldItem: SubstringModel, newItem: SubstringModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: SubstringModel, newItem: SubstringModel) =
            oldItem == newItem
    }