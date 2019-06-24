package com.asp.weatherapp.preferences

import com.asp.weatherapp.utils.SharedPrefDelegate


class WeatherPreferences : SharedPrefDelegate("weather_pref") {

    var samplePref by stringPref("samplePref", "")
}
