package com.asp.weatherapp.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.TranslateAnimation
import androidx.annotation.LayoutRes
import com.asp.weatherapp.R
import java.util.*


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}


fun convertEpochToDay(epoch: String): String {
    val c = Calendar.getInstance()
    c.timeInMillis = epoch.toLong() * 1000
    return when (c.get(Calendar.DAY_OF_WEEK)) {
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


fun View.slideUp() {
    this.visibility = View.VISIBLE
    val animate = TranslateAnimation(
        0f,
        0f,
        this.height.toFloat(),
        0f
    )
    animate.duration = 500
    animate.fillAfter = true
    this.startAnimation(animate)
}


fun Context.showProgressScreen(): Dialog {
    val dialog = Dialog(this,R.style.ProgressDialog)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(false)
    dialog.setCanceledOnTouchOutside(false)
    dialog.setContentView(R.layout.layout_progress_fullscreen)
    return dialog
}


fun Context.showErrorScreen(): Dialog {
    val dialog = Dialog(this,R.style.ProgressDialog)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(false)
    dialog.setCanceledOnTouchOutside(false)
    dialog.setContentView(R.layout.layout_error_fullscreen)
    return dialog
}


