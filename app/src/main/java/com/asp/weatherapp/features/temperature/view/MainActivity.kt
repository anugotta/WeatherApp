package com.asp.weatherapp.features.temperature.view

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asp.weatherapp.R
import com.asp.weatherapp.base.BaseActivity
import com.asp.weatherapp.features.temperature.network.Info
import com.asp.weatherapp.features.temperature.view.adapter.ForecastAdapter
import com.asp.weatherapp.utils.showErrorScreen
import com.asp.weatherapp.utils.showProgressScreen
import com.asp.weatherapp.utils.slideUp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    override fun provideLayout(): Int = R.layout.activity_main
    override fun provideViewModelClass(): Class<MainViewModel> = MainViewModel::class.java
    private lateinit var progressDialog: Dialog
    private lateinit var errorDialog: Dialog
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var forecastAdapter: ForecastAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(provideLayout())

        initUI()
        initAlertDialogs()

        getViewModel().info.observe(this, Observer {
            populateUI(it)
        })

        getViewModel().viewState.observe(this, Observer {

            if (it.isLoading) {
                progressDialog.show()
                errorDialog.dismiss()
            }

            if (it.isError) {
                progressDialog.dismiss()
                errorDialog.show()
            }

            if (it.showData) {
                progressDialog.dismiss()
                errorDialog.dismiss()
            }

        })

        getViewModel().getInfo()

    }

    private fun initAlertDialogs() {
        progressDialog = this.showProgressScreen()
        errorDialog = this.showErrorScreen()
        errorDialog.findViewById<Button>(R.id.btnRetry).setOnClickListener {
            getViewModel().getInfo()
            getViewModel().setLoadingState()
        }
    }

    private fun initUI() {
        rvForecast.visibility = View.INVISIBLE
    }


    private fun populateUI(info: Info) {
        tvCurrentTemp.text = "${info.current!!.temp_c!!.substringBefore(".")}\u00B0"
        tvCurrentLocation.text = info.location!!.name
        setRecyclerView(info.forecast!!.forecastday)
    }


    private fun setRecyclerView(forecastDayList: List<Info.Forecast.ForecastDay>) {
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        forecastAdapter = ForecastAdapter(forecastDayList.subList(1, 5))
        rvForecast.layoutManager = linearLayoutManager
        rvForecast.adapter = forecastAdapter
        rvForecast.slideUp()
    }
}
