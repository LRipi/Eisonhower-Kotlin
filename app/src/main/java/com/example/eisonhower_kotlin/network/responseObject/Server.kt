package com.example.eisonhower_kotlin.network.responseObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Server {

    @SerializedName("version")
    @Expose
    var version: String? = null
    @SerializedName("port")
    @Expose
    var port: Int? = null
    @SerializedName("current_time")
    @Expose
    var currentTime: Int? = null
    @SerializedName("alive")
    @Expose
    var alive: Boolean? = null

}
