package com.asp.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.asp.weatherapp.features.temperature.data.Info
import com.asp.weatherapp.features.temperature.repo.WeatherRepo
import com.asp.weatherapp.features.temperature.view.InfoViewState
import com.asp.weatherapp.features.temperature.view.MainViewModel
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class WeatherUnitTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var infoRepo: WeatherRepo

    lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.mainViewModel = MainViewModel(this.infoRepo)
    }

    @Test
    fun getInfo_positiveResponse(){
        Mockito.`when`(this.infoRepo.getInfo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .thenReturn(Single.just(Info()))

        val observer = mock(Observer::class.java) as Observer<InfoViewState>
        this.mainViewModel.viewState.observeForever(observer)

        this.mainViewModel.getInfo(ArgumentMatchers.anyString(),ArgumentMatchers.anyString())
        assertNotNull(this.mainViewModel.viewState.value)
        assertEquals(false,this.mainViewModel.viewState.value!!.isError)
        assertEquals(false,this.mainViewModel.viewState.value!!.isLoading)
        assertEquals(true,this.mainViewModel.viewState.value!!.showData)
    }


    @Test
    fun getInfo_failureResponse(){
        Mockito.`when`(this.infoRepo.getInfo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .thenReturn(Single.error(Throwable("Network Error")))

        val observer = mock(Observer::class.java) as Observer<InfoViewState>
        this.mainViewModel.viewState.observeForever(observer)

        this.mainViewModel.getInfo(ArgumentMatchers.anyString(),ArgumentMatchers.anyString())
        assertNotNull(this.mainViewModel.viewState.value)
        assertEquals(true,this.mainViewModel.viewState.value!!.isError)
        assertEquals(false,this.mainViewModel.viewState.value!!.isLoading)
        assertEquals(false,this.mainViewModel.viewState.value!!.showData)
    }

}
