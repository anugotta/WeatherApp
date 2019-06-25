package com.asp.weatherapp.features.temperature.view

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asp.weatherapp.features.temperature.network.Info
import com.asp.weatherapp.features.temperature.repo.WeatherRepo
import com.asp.weatherapp.utils.AndroidDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(var infoRepo: WeatherRepo) : ViewModel() {

    private var disposable = AndroidDisposable()

    private val _viewState = MutableLiveData<InfoViewState>()
    private val _infoLD = MutableLiveData<Info>()

    val viewState: LiveData<InfoViewState>
        get() = _viewState

    val info: LiveData<Info>
        get() = _infoLD


    init {
        _viewState.value = InfoViewState(isLoading = true)
    }

    fun setLoadingState(){
        _viewState.value = InfoViewState(isLoading = true)
    }

    fun getInfo(location: Location) {
        disposable.add(
            infoRepo.getInfo(location)
                .subscribe(
                    {
                        _infoLD.value = it
                        _viewState.value?.let {
                            val newState =
                                this.viewState.value?.copy(isLoading = false, isError = false, showData = true)
                            _viewState.postValue(newState)
                        }
                    },
                    {
                        _viewState.value = viewState.value?.copy(isLoading = false, isError = true, showData = false)
                    }
                )
        )
    }



    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}
