package com.asp.weatherapp.api

import com.asp.weatherapp.features.temperature.network.Info
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("forecast.json")
    fun getForecast(@Query(value = "key") key: String, @Query(value = "q") query: String, @Query(value = "days") days: String): Single<Info>
}
