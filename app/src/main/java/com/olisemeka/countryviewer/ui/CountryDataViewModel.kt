package com.olisemeka.countryviewer.ui

import androidx.lifecycle.*
import com.olisemeka.countryviewer.data.model.CountryData
import com.olisemeka.countryviewer.data.repository.CountryDataRepository
import com.olisemeka.countryviewer.util.Resource
import kotlinx.coroutines.launch

class CountryDataViewModel(val countryDataRepository: CountryDataRepository): ViewModel() {
    init{
        getCountryDataResults()
    }

    private var _countryDataResults = MutableLiveData<Resource<CountryData>>()
    val countryDataResults: LiveData<Resource<CountryData>> get() = _countryDataResults

    private fun getCountryDataResults() = viewModelScope.launch {
        val response = countryDataRepository.getCountryDataResults()
    }
}


class CountryDataViewModelProviderFactory(val countryDataRepository: CountryDataRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountryDataViewModel(countryDataRepository) as T
    }
}