package com.asp.weatherapp.features.temperature.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asp.weatherapp.R
import com.asp.weatherapp.base.BaseActivity
import com.asp.weatherapp.features.temperature.data.Info
import com.asp.weatherapp.features.temperature.view.adapter.ForecastAdapter
import com.asp.weatherapp.utils.showErrorScreen
import com.asp.weatherapp.utils.showProgressScreen
import com.asp.weatherapp.utils.slideUp
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {


    private var tempLocation: Location? = null
    private lateinit var rxPermissions: RxPermissions
    private var locationReq: LocationRequest? = null
    private var fusedLocationClient: FusedLocationProviderClient? = null
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
        initLocationAccess()

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

    }

    @SuppressLint("CheckResult")
    private fun initLocationAccess() {
        rxPermissions = RxPermissions(this)
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            rxPermissions
                .request(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe { granted ->
                    run {
                        if (granted) {
                            startLocationUpdates()
                        }
                    }
                }
        } else {
            startLocationUpdates()
        }

    }

    private fun initAlertDialogs() {
        progressDialog = this.showProgressScreen()
        errorDialog = this.showErrorScreen()
        errorDialog.findViewById<Button>(R.id.btnRetry).setOnClickListener {
            tempLocation?.let {
                getViewModel().getInfo(it.latitude.toString(), it.longitude.toString())
            }

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


    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        locationReq = LocationRequest().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val locationSettingsReqBuilder = LocationSettingsRequest.Builder()
        locationSettingsReqBuilder.addLocationRequest(locationReq!!)
        val locationSettingsRequest = locationSettingsReqBuilder.build()

        val settingsClient = LocationServices.getSettingsClient(this)
        settingsClient.checkLocationSettings(locationSettingsRequest)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        fusedLocationClient?.lastLocation?.addOnSuccessListener { location ->
            if (location != null) {

                tempLocation = location

                getViewModel().getInfo(location.latitude.toString(), location.longitude.toString())

            } else {
                Log.d("LOCATION", "Location is null")
            }
        }?.addOnFailureListener { exp ->
            Log.d("LOCATION", "Error trying to get last GPS location")
            exp.printStackTrace()
        }
    }
}
