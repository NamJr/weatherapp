package com.example.baseapplication.data.repository

import android.util.Log
import com.example.baseapplication.data.WeatherApi
import com.example.baseapplication.domain.WeatherRepository
import com.example.baseapplication.domain.Country
import com.example.baseapplication.domain.Weather
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl
@Inject
constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getCountry(cityName: String): Response<List<Country>> {
        Log.d("TAG", "getCountry ")
        val res = api.getCountry(cityName)
        Log.d("TAG", "getCountry res"+ res)
        return res
    }

    override suspend fun getWeather(cityId: String): Response<Weather> {
        Log.d("TAG", "getWeatherCountry ")
        val res = api.getWeatherCountry(cityId)
        Log.d("TAG", "getWeatherCountry res"+ res)
        return res
    }

//    override suspend fun getCoinById(coinId: String): CoinDetailDto {
//        return api.getCoinById(coinId)
//    }


}