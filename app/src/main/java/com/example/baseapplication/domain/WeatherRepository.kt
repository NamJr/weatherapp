package com.example.baseapplication.domain
import retrofit2.Response

interface WeatherRepository {

    suspend fun getCountry(cityName: String): Response<List<Country>>

    suspend fun getWeather(cityId: String): Response<Weather>
}