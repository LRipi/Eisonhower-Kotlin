package com.example.eisonhower_kotlin.network.responseObject

import com.example.eisonhower_kotlin.network.responseObject.Server
import com.example.eisonhower_kotlin.network.responseObject.Client
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class About {

    @SerializedName("client")
    @Expose
    var client: Client? = null
    @SerializedName("server")
    @Expose
    var server: Server? = null

}
