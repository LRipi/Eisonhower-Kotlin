package com.example.eisonhower_kotlin.network.responseObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Client {

    @SerializedName("host")
    @Expose
    var host: String? = null

}
