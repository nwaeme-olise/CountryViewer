package com.olisemeka.countryviewer.data.repository

import com.olisemeka.countryviewer.api.RetrofitInstance

class CountryDataRepository {
    suspend fun getCountryDataResults() = RetrofitInstance.api.getCountryDataResults()
}