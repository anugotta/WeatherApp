package com.asp.weatherapp.features.temperature.view

data class InfoViewState(
    var isLoading: Boolean = false,
    var isError: Boolean = false,
    var showData: Boolean = false
)
