package com.asp.weatherapp.api

import com.asp.weatherapp.features.temperature.network.Info
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {

    @GET("info")
    fun getInfo(): Single<Info>
}
