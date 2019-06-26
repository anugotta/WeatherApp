package com.asp.weatherapp.di.module

import com.asp.weatherapp.api.ApiInterface
import com.asp.weatherapp.features.temperature.repo.WeatherRepo
import com.asp.weatherapp.utils.AppRxSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(ViewModelModule::class)])
class AppModule {

    @Singleton
    @Provides
    fun provideInfoRepo(
        api: ApiInterface,
        rxSchedulers: AppRxSchedulers
    ): WeatherRepo = WeatherRepo(api = api, rxSchedulers = rxSchedulers)
}
