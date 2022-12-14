package com.olisemeka.countryviewer.ui

import androidx.lifecycle.*
import com.olisemeka.countryviewer.data.model.CountryData
import com.olisemeka.countryviewer.data.repository.CountryDataRepository
import com.olisemeka.countryviewer.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CountryDataViewModel(val countryDataRepository: CountryDataRepository): ViewModel() {
    init{
        getCountryDataResults()
    }

    private var _countryDataResults = MutableLiveData<List<CountryData>>()
    val countryDataResults: LiveData<List<CountryData>> get() = _countryDataResults

    fun getCountryDataResults() = viewModelScope.launch {
        val response = countryDataRepository.getCountryDataResults()
        if (response.isSuccessful && response.body() != null){
            val sortedBody = response.body()!!.sortedBy { it.name?.common }
            _countryDataResults.postValue(sortedBody)
        }
    }
}


class CountryDataViewModelProviderFactory(val countryDataRepository: CountryDataRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountryDataViewModel(countryDataRepository) as T
    }
}