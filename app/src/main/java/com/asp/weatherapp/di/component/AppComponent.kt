package com.asp.weatherapp.di.component

import com.asp.weatherapp.WeatherApp
import com.asp.weatherapp.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            ContextModule::class,
            ActivityModule::class,
            ApiModule::class,
            AppModule::class,
            AndroidSupportInjectionModule::class,
            ViewModelModule::class
        ]
)
interface AppComponent : AndroidInjector<WeatherApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: WeatherApp): Builder

        fun build(): AppComponent
    }
}
