package com.asp.weatherapp.features.temperature.view

import android.os.Bundle
import com.asp.weatherapp.R
import com.asp.weatherapp.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel>() {
    override fun provideLayout(): Int = R.layout.activity_main

    override fun provideViewModelClass(): Class<MainViewModel> = MainViewModel::class.java


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().getInfo()

    }
}
