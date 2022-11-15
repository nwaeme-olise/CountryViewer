package com.olisemeka.countryviewer.ui.countrylist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.olisemeka.countryviewer.R
import com.olisemeka.countryviewer.data.model.CountryData
import com.olisemeka.countryviewer.databinding.ListItemCountryBinding
import java.util.*
import kotlin.collections.ArrayList

class CountryListAdapter(private val context: Context): ListAdapter<CountryData, CountryListAdapter.ViewHolder>(CountryDiffCallback) {
    private var countriesList = mutableListOf<CountryData>()

    fun setData(countriesList: MutableList<CountryData>){
        this.countriesList = countriesList!!
        submitList(countriesList)
    }

    class ViewHolder(val binding: ListItemCountryBinding) : RecyclerView.ViewHolder(binding.root){
        val ivCountry = binding.ivCountry
        val tvCountry = binding.tvCountry
        val tvCapital = binding.tvCapital
        var country: CountryData? = null

        init{
            binding.root.setOnClickListener{ view ->
                val action = CountryListFragmentDirections.actionCountryListFragmentToCountryDetailsFragment(country!!)
                view.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = getItem(position)
        holder.country = country
        holder.binding.apply{
            tvCountry.text = country.name?.common ?: ""
            tvCapital.text = country.capital?.get(0) ?: ""
            Glide.with(context)
                .load(country.flags?.png)
                .transform(RoundedCorners(7))
                .into(ivCountry)

        }

    }

    fun getFilter(): Filter{
        return countryFilter
    }

    private val countryFilter = object: Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<CountryData>()
            if (constraint == null || constraint.isEmpty()){
                filteredList.addAll(countriesList)
            }else{
                val query = constraint.toString().trim().lowercase()
                countriesList.forEach{
                    if ((it.name?.common)?.lowercase()?.contains(query) == true){
                        filteredList.add(it)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            submitList(results?.values as MutableList<CountryData>)
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

