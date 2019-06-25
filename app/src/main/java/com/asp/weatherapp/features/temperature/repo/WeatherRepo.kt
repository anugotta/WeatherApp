package com.asp.weatherapp.features.temperature.repo

import android.location.Location
import com.asp.weatherapp.api.ApiInterface
import com.asp.weatherapp.features.temperature.data.Info
import com.asp.weatherapp.preferences.WeatherPreferences
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

    companion object {
        val TAG = WeatherRepo::class.java.simpleName
    }


    fun getInfo(location: Location): Single<Info> {
        return api.getForecast(WEATHER_API_KEY,location.latitude.toString() + "," + location.longitude.toString(), "5")
            .subscribeOn(rxSchedulers.io())
            .observeOn(rxSchedulers.androidThread())
    }


}
