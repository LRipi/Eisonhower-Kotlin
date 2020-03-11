package com.example.eisonhower_kotlin.network.responseObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Login {

    @SerializedName("success")
    @Expose
    var success: Boolean? = null
    @SerializedName("user")
    @Expose
    var user: User? = null

}
