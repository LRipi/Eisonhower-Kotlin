package com.example.eisonhower_kotlin.network

import com.example.eisonhower_kotlin.network.responseObject.Login
import com.example.eisonhower_kotlin.network.responseObject.NumberOfTasks
import com.example.eisonhower_kotlin.network.responseObject.Register
import com.example.eisonhower_kotlin.network.responseObject.Tasks
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

    @Headers(
        "Accept: application/json",
        "Content-Type:application/json"
    )
    @POST("users/create")
    fun register(
        @Body RegisterData : RegisterData
    ) : Call<Register>

    @Headers(
        "Accept: application/json"
    )
    @GET("tasks/total")
    fun numberOfTasks(
        @Header("x-access-token") jwtToken : String,
        @Query ("importance") importance : Boolean,
        @Query ("urgence") urgence : Boolean
    ) : Call<NumberOfTasks>

    @Headers(
        "Accept: application/json"
    )
    @GET("tasks")
    fun listOfTasks (
        @Header("x-access-token") jwtToken : String,
        @Query ("importance") importance : Boolean,
        @Query ("urgence") urgence : Boolean
    ) : Call<Tasks>
}

data class LoginData (
    @SerializedName("login") var login : String,
    @SerializedName("password") var password : String
)

data class RegisterData (
    @SerializedName("login") var login: String,
    @SerializedName("password") var password: String,
    @SerializedName("name") var name: String
)