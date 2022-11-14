package com.olisemeka.countryviewer.ui.countrylist

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.olisemeka.countryviewer.R
import com.olisemeka.countryviewer.api.RetrofitInstance
import com.olisemeka.countryviewer.data.model.CountryData
import com.olisemeka.countryviewer.data.repository.CountryDataRepository
import com.olisemeka.countryviewer.databinding.FragmentCountryListBinding
import com.olisemeka.countryviewer.ui.CountryDataViewModel
import com.olisemeka.countryviewer.ui.CountryDataViewModelProviderFactory
import com.olisemeka.countryviewer.util.Resource
import retrofit2.HttpException
import java.io.IOException
import java.io.PrintWriter
import java.io.StringWriter

class CountryListFragment : Fragment() {
    private var _binding: FragmentCountryListBinding? = null
    private val binding get() = _binding!!
    private val countryDataRepository by lazy{ CountryDataRepository() }
    private val adapter by lazy{ CountryListAdapter(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountryListBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: CountryDataViewModel by activityViewModels { CountryDataViewModelProviderFactory(countryDataRepository) }

        binding.rvCountries.adapter = adapter
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_item_light_mode -> switchDisplayMode()
                R.id.menu_item_dark_mode -> switchDisplayMode()
            }
            true
        }


        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try{
                RetrofitInstance.api.getCountryDataResults()
            } catch(e: IOException){
                binding.progressBar.isVisible = false
                val sw = StringWriter()
                e.printStackTrace(PrintWriter(sw))
                Log.e("CountryListFragment", sw.toString())
                return@launchWhenCreated

            } catch(e: HttpException){
                binding.progressBar.isVisible=false
                val sw = StringWriter()
                e.printStackTrace(PrintWriter(sw))
                Log.e("CountryListFragment", sw.toString())
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                val body = response.body()!!
                val sortedBody = (body.sortedBy { it.name?.common }).toMutableList()
                adapter.setData(sortedBody)
            }
            binding.progressBar.isVisible = false
        }


        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.getFilter().filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.getFilter().filter(newText)
                return true
            }

        })

    }

    private fun switchDisplayMode() {
        val isNightTheme = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (isNightTheme) {
            Configuration.UI_MODE_NIGHT_YES ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Configuration.UI_MODE_NIGHT_NO ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding  = null
    }
}