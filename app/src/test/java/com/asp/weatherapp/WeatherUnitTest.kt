package com.asp.weatherapp

import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass


class WeatherUnitTest {


    @Before
    fun setup() {
        println("Ready for testing!")
    }

    @After
    fun cleanup() {
        println("Done with unit test!")
    }

    @BeforeClass
    fun testClassSetup() {
        println("Getting test class ready")
    }

    @AfterClass
    fun testClassCleanup() {
        println("Done with tests")
    }




}
