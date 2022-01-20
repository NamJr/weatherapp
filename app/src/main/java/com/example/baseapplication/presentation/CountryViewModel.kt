package com.example.baseapplication.presentation

import android.util.Log
import androidx.lifecycle.*
import com.example.baseapplication.common.Resource
import com.example.baseapplication.domain.WeatherRepository
import com.example.baseapplication.domain.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: WeatherRepository): ViewModel() {
    private val TAG = "CountryViewModel"

    private val _weather = MutableLiveData<Resource<Weather?>>()
    val weather: LiveData<Resource<Weather?>>
        get() = _weather

    fun getCountry(cityName: String) {
        viewModelScope.launch {
            repository.getCountry(cityName).let {
                Log.d(TAG, "result ${it.body()}")
                if(it.isSuccessful){
                    val id = it.body()?.get(0)?.woeid.toString()
                    if (id.isNotBlank()){
                        repository.getWeather(id).let {
                            _weather.postValue(Resource.Success(it.body()))
                            Log.d(TAG, "result getWeather ${it.body()}")
                        }
                    }
                }

            }

//            when(result.value) {
//                is Resource.Success<*> -> {
//
//                }
//
//                is Resource.Error -> {
//
//                }
//
//                is Resource.Loading -> {
//
//                }
//            }
        }
    }

}