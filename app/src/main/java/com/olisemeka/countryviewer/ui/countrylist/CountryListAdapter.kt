package com.olisemeka.countryviewer.ui.countrylist

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CountryListAdapter(private val context: Context): ListAdapter<Any, CountryListAdapter.ViewHolder>(CountryDiffCallback) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

object CountryDiffCallback: DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        TODO("Not yet implemented")
    }

}