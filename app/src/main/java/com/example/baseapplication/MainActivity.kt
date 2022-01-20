package com.example.baseapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.baseapplication.presentation.CountryViewModel
import com.example.baseapplication.search.SearchActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CountryViewModel
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)

        viewModel.getCountry("ho chi minh")

        viewModel.weather.observe(this, {
            val weather = it.data ?: return@observe
            val info = weather.consolidated_weather[0]

            tvTitle.text = "Location: " + weather.title
            tvDate.text = "Date: " + info.applicable_date
            tvDegree.text = "Min temp: " + info.min_temp + " Max temp: " + info.max_temp
            tvWindStatus.text = "Wind speed: "+info.wind_speed
            tvWindDirection.text = "Wind direction: " + info.wind_direction
            tvVisibility.text = "Visibility: " + info.visibility
            tvHumid.text = "Humidity: " + info.humidity
            tvAir.text = "Air pressure: "+ info.air_pressure
        })

        search.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }
}