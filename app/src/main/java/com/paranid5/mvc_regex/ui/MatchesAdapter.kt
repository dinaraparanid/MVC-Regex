package com.paranid5.mvc_regex.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.paranid5.mvc_regex.DiffCallback
import com.paranid5.mvc_regex.R
import com.paranid5.mvc_regex.data.SubstringModel

class MatchesAdapter : RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder>() {
    private val differ = AsyncListDiffer(this, DiffCallback())

    private inline val currentList: List<SubstringModel>
        get() = differ.currentList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_substring, parent, false)
            .let(::MatchesViewHolder)

    override fun getItemCount(): Int =
        currentList.size

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) =
        holder bind currentList[position]

    infix fun submitList(matches: List<SubstringModel>) =
        differ.submitList(matches)

    class MatchesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val substringIndex: TextView by lazy {
            view.findViewById(R.id.substring_index)
        }

        private val substringValue: TextView by lazy {
            view.findViewById(R.id.substring_value)
        }

        infix fun bind(substringModel: SubstringModel) {
            substringIndex.text = "${substringModel.index + 1}."
            substringValue.text = substringModel.value
        }
    }
}