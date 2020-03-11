package com.example.eisonhower_kotlin.network.responseObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("login")
    @Expose
    var login: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("token")
    @Expose
    var token: String? = null
    @SerializedName("tokenExpires")
    @Expose
    var tokenExpires: Int? = null

}
