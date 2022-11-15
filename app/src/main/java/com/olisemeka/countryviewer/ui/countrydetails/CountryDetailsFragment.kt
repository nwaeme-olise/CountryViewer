package com.olisemeka.countryviewer.ui.countrydetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.olisemeka.countryviewer.data.repository.CountryDataRepository
import com.olisemeka.countryviewer.databinding.FragmentCountryDetailsBinding
import com.olisemeka.countryviewer.ui.CountryDataViewModel
import com.olisemeka.countryviewer.ui.CountryDataViewModelProviderFactory
import java.text.NumberFormat


class CountryDetailsFragment : Fragment() {
    private var _binding: FragmentCountryDetailsBinding? = null
    private val binding get() = _binding!!
    val countryDataRepository by lazy { CountryDataRepository() }
    val args: CountryDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val country = args.country


            Glide.with(requireContext())
                .load(country.flags?.png)
                .transform(RoundedCorners(7))
                .into(binding.ivCountryFlag)

            binding.apply{
                tvCountryName.text = country.name?.common
                if (country.population != null) {
                    tvPopulation.text = NumberFormat.getIntegerInstance().format(country.population)
                }
                else{
                    tvPopulation.text = "N/A"
                }
                tvRegion.text = country.region ?: "N/A"
                tvCapital.text = country.capital?.get(0) ?: "N/A"
                tvSubRegion.text = country.subregion ?: "N/A"
                tvOfficialLanguage.text = country.languages?.values?.first()
                tvDemonym.text = country.demonyms?.eng?.m ?: "N/A"
                tvLandlocked.text = if (country.landlocked == true) "Yes" else "No"
                tvUnMember.text = if (country.unMember == true) "Yes" else "No"
                tvIndependence.text = if (country.independent == true) "Yes" else "No"
                tvArea.text = "${country.area.toString()}km" ?: "N/A"
                tvCurrency.text = country.currencies?.values?.first()?.name ?: "N/A"
                tvSymbol.text = country.currencies?.values?.first()?.symbol ?: "N/A"
                tvTimeZone.text = country.timezones?.get(0) ?: "N/A"
                tvContinent.text = country.continents?.get(0) ?: "N/A"
                tvDiallingCode.text = "${country.idd?.root}${country.idd?.suffixes}" ?: "N/A"
                tvStartofWeek.text = country.startOfWeek?.capitalize() ?: "N/A"

            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}