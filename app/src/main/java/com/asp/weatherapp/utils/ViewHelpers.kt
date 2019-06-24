package com.asp.weatherapp.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import java.util.*


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}


fun convertEpochToDay(epoch : String): String {
    val c = Calendar.getInstance()
    c.timeInMillis = epoch.toLong()
    return  when(c.get(Calendar.DAY_OF_WEEK)){
      1 -> "Sunday"
      2 -> "Monday"
      3 -> "Tuesday"
      4 -> "Wednesday"
      5 -> "Thursday"
      6 -> "Friday"
      7 -> "Saturday"
      else -> "Error"
  }
}
