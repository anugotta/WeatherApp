package com.asp.weatherapp.di.module

import android.content.Context
import com.asp.weatherapp.WeatherApp
import dagger.Module
import dagger.Provides

@Module
class ContextModule {

    @Provides
    fun provideContext(application: WeatherApp): Context {
        return application.applicationContext
    }
}
