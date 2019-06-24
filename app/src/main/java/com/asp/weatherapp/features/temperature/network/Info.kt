package com.asp.weatherapp.features.temperature.network

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("location") val location: Location? = Location(),
    @SerializedName("current") val current: Current? = Current(),
    @SerializedName("forecast") val forecast: Forecast? = Forecast()
) {
    data class Location(
        @SerializedName("name") val name: String? = ""
    )

    data class Current(
        @SerializedName("temp_c") val temp_c: String? = ""
    )

    data class Forecast(
        @SerializedName("forecastday") val forecastday: List<ForecastDay> = ArrayList()
    ){
        data class ForecastDay(
            @SerializedName("date_epoch") var date_epoch: Long = 0,
            @SerializedName("day") val day: Day? = Day()
        ) {
            data class Day(
                @SerializedName("avgtemp_c") val name: String? = ""
            )
        }
    }
}
