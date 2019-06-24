package com.asp.weatherapp.features.temperature.view.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asp.weatherapp.R
import com.asp.weatherapp.features.temperature.network.Info
import com.asp.weatherapp.utils.convertEpochToDay
import com.asp.weatherapp.utils.inflate

class ForecastAdapter(private val forecastList : List<Info.Forecast.ForecastDay>) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return  ForecastViewHolder(parent.inflate(R.layout.rv_item_forecast))
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {

        val item = forecastList[position]

        holder.tvDayTitle.text = convertEpochToDay(item.date_epoch.toString())
        holder.tvTemperature.text = "${item.day!!.avgtemp_c} C"

    }

    inner class ForecastViewHolder(v : View) : RecyclerView.ViewHolder(v) , View.OnClickListener{

        var view = v
        var tvDayTitle: TextView = view.findViewById(R.id.tvDayTitle)
        var tvTemperature: TextView = view.findViewById(R.id.tvTemperature)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
           // NIL
        }
    }


}
