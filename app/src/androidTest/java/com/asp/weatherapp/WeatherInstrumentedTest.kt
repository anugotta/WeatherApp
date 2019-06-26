package com.asp.weatherapp


import android.Manifest
import android.content.Intent
import android.os.SystemClock
import androidx.recyclerview.widget.RecyclerView
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.asp.weatherapp.features.temperature.view.MainActivity
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class WeatherInstrumentedTest {


    @JvmField
    @field:Rule
    val rule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun grantPhonePermission() {
        val permissions = ArrayList<String>()
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)

        for (i in 0 until permissions.size) {
            val command = String.format("pm grant %s %s", getTargetContext().packageName, permissions[i])
            getInstrumentation().uiAutomation.executeShellCommand(command)
            SystemClock.sleep(1000)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testWhenThereInNoExistingAccount_LaunchesLoginActivity() {
        Intents.init()
        rule.launchActivity(Intent())
        intended(hasComponent(MainActivity::class.java.name))
        Intents.release()
    }

    @Test
    fun checkProgressDialog_isShown() {
        onView(withId(R.id.progressBar)).inRoot(isDialog()).check(matches(isDisplayed()))
    }


    @Test
    fun checkRecyclerView_count() {
        ViewMatchers.assertThat(getRVCount(), Matchers.`is`(4))
    }

    private fun getRVCount(): Int {
        val recyclerView = rule.activity.findViewById(R.id.rvForecast) as RecyclerView
        return recyclerView.adapter!!.itemCount
    }

}

