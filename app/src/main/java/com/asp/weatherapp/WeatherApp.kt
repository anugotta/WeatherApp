package com.asp.weatherapp

import android.content.Context
import com.asp.weatherapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class WeatherApp: DaggerApplication() {

    init {
        instance = this
    }

    companion object {
        private var instance: WeatherApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this@WeatherApp)
            .build()
    }

}
