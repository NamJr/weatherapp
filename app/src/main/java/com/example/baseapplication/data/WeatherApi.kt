package com.example.baseapplication.data

import androidx.lifecycle.LiveData
import com.example.baseapplication.domain.Country
import com.example.baseapplication.domain.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("location/search/")
    suspend fun getCountry(@Query("query") cityName: String): Response<List<Country>>

    @GET("location/location/{cityId}/")
    suspend fun getWeatherCountry(@Path("cityId") cityId: String): Response<Weather>

    @GET("location/location/1252431/")
    suspend fun getWeatherByDate(): Response<Weather>
}