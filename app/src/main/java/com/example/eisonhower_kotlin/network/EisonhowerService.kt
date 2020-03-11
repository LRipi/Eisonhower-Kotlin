package com.example.eisonhower_kotlin.network

import com.example.eisonhower_kotlin.network.responseObject.Login
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*
import com.example.eisonhower_kotlin.network.responseObject.About as About

interface EisonhowerService {

    @GET("about.json")
    fun about() : Call<About>

    @Headers(
        "Accept: application/json",
        "Content-Type:application/json"
    )
    @POST("users/login")
    fun login(
        @Body LoginData : LoginData
    ) : Call<Login>
}

data class LoginData (
    @SerializedName("login") var login : String,
    @SerializedName("password") var password : String
)