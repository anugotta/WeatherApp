package com.asp.weatherapp.features.temperature.repo

import androidx.lifecycle.MutableLiveData
import com.asp.weatherapp.api.ApiInterface
import com.asp.weatherapp.features.temperature.network.Info
import com.asp.weatherapp.preferences.WeatherPreferences
import com.asp.weatherapp.utils.AndroidDisposable
import com.asp.weatherapp.utils.AppRxSchedulers
import com.asp.weatherapp.utils.Constants.Companion.WEATHER_API_KEY
import io.reactivex.Single
import javax.inject.Singleton

@Singleton
class WeatherRepo(
    var api: ApiInterface,
    var pref: WeatherPreferences,
    var rxSchedulers: AppRxSchedulers
) {
    private var disposable = AndroidDisposable()
    private val info = MutableLiveData<Info>()

    companion object {
        val TAG = WeatherRepo::class.java.simpleName
    }


    fun getInfo(): Single<Info> {
        return api.getForecast(WEATHER_API_KEY,"Bangalore", "5")
            .subscribeOn(rxSchedulers.io())
            .observeOn(rxSchedulers.androidThread())
    }


}