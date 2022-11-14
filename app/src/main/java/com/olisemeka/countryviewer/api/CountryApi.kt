package com.olisemeka.countryviewer.api

import com.olisemeka.countryviewer.data.model.CountryData
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {
    @GET("all")
    suspend fun getCountryDataResults(): Response<MutableList<CountryData>>
}