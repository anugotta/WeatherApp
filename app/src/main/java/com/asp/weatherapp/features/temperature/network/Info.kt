package com.asp.weatherapp.features.temperature.network

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("name") var name: String? = "",
    @SerializedName("founder") var founder: String? = "",
    @SerializedName("founded") var founded: Int? = 0,
    @SerializedName("employees") var employees: Int? = 0,
    @SerializedName("vehicles") var vehicles: Int? = 0,
    @SerializedName("launch_sites") var launchSites: Int? = 0,
    @SerializedName("test_sites") var testSites: Int? = 0,
    @SerializedName("ceo") var ceo: String? = "",
    @SerializedName("cto") var cto: String? = "",
    @SerializedName("coo") var coo: String? = "",
    @SerializedName("cto_propulsion") var ctoPropulsion: String? = "",
    @SerializedName("valuation") var valuation: Long? = 0L,
    @SerializedName("summary") var summary: String? = "",
    @SerializedName("headquarters") var headquarters: Headquarters? = Headquarters(),
    @SerializedName("links") var links: Links? = Links()
) {
    data class Headquarters(
        @SerializedName("address") var address: String? = "",
        @SerializedName("city") var city: String? = "",
        @SerializedName("state") var state: String? = ""
    )

    data class Links(
        @SerializedName("elon_twitter") var elonTwitter: String? = "",
        @SerializedName("flickr") var flickr: String? = "",
        @SerializedName("twitter") var twitter: String? = "",
        @SerializedName("website") var website: String? = ""
    )
}
