package com.olisemeka.countryviewer.ui.countrylist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.olisemeka.countryviewer.R
import com.olisemeka.countryviewer.data.model.CountryData
import com.olisemeka.countryviewer.databinding.ListItemCountryBinding

class CountryListAdapter(private val context: Context): ListAdapter<CountryData, CountryListAdapter.ViewHolder>(CountryDiffCallback) {

    class ViewHolder(val binding: ListItemCountryBinding) : RecyclerView.ViewHolder(binding.root){
        val ivCountry = binding.ivCountry
        val tvCountry = binding.tvCountry
        val tvCapital = binding.tvCapital
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply{
            val country = getItem(position)
            tvCountry.text = country.name?.common ?: ""
            tvCapital.text = country.capital?.get(0) ?: ""
            Glide.with(context)
                .load(country.flags?.png)
                .transform(RoundedCorners(7))
                .into(ivCountry)
        }
    }
}

object CountryDiffCallback: DiffUtil.ItemCallback<CountryData>() {
    override fun areItemsTheSame(oldItem: CountryData, newItem: CountryData): Boolean {
        return oldItem.name?.official == newItem.name?.official
    }

    override fun areContentsTheSame(oldItem: CountryData, newItem: CountryData): Boolean {
        return oldItem == newItem
    }

}