package com.asp.weatherapp.features.temperature.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asp.weatherapp.R
import com.asp.weatherapp.base.BaseActivity
import com.asp.weatherapp.features.temperature.network.Info
import com.asp.weatherapp.features.temperature.view.adapter.ForecastAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    override fun provideLayout(): Int = R.layout.activity_main
    override fun provideViewModelClass(): Class<MainViewModel> = MainViewModel::class.java
    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var forecastAdapter: ForecastAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(provideLayout())

        getViewModel().info.observe(this, Observer {
           populateUI(it)
        })


        getViewModel().viewState.observe(this, Observer {
            Log.e("XXXC", it.isError.toString())
        })

        getViewModel().getInfo()

    }



    private fun populateUI(info: Info) {
        tvCurrentTemp.text = info.current!!.temp_c
        tvCurrentLocation.text = info.location!!.name
        setRecyclerView(info.forecast!!.forecastday)
    }


    private fun setRecyclerView(forecastDayList: List<Info.Forecast.ForecastDay>) {
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        forecastAdapter = ForecastAdapter(forecastDayList)
        rvForecast.layoutManager=linearLayoutManager
        rvForecast.adapter = forecastAdapter
    }
}
