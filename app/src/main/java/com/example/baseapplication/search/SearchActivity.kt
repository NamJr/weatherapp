package com.example.baseapplication.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.baseapplication.R
import com.example.baseapplication.presentation.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.tvAir
import kotlinx.android.synthetic.main.activity_main.tvDate
import kotlinx.android.synthetic.main.activity_main.tvDegree
import kotlinx.android.synthetic.main.activity_main.tvTitle
import kotlinx.android.synthetic.main.activity_main.tvVisibility
import kotlinx.android.synthetic.main.activity_main.tvWindDirection
import kotlinx.android.synthetic.main.activity_main.tvWindStatus
import kotlinx.android.synthetic.main.activity_search.*

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private lateinit var viewModel: CountryViewModel
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)


        btnSearch.setOnClickListener {
            resultView.visibility = View.GONE
            viewModel.getCountry(edtName.text.toString().trim().replace(" ", "+"))
        }

        viewModel.weather.observe(this, {
            val weather = it.data ?: return@observe
            resultView.visibility = View.VISIBLE
            val info = weather.consolidated_weather[0]

            tvTitle.text = "Location: " + weather.title
            tvDate.text = "Date: " + info.applicable_date
            tvDegree.text = "Min temp: " + info.min_temp + " Max temp: " + info.max_temp
            tvWindStatus.text = "Wind speed: "+info.wind_speed
            tvWindDirection.text = "Wind direction: " + info.wind_direction
            tvVisibility.text = "Visibility: " + info.visibility
            tvHumidity.text = "Humidity: " + info.humidity
            tvAir.text = "Air pressure: "+ info.air_pressure
        })
    }
}