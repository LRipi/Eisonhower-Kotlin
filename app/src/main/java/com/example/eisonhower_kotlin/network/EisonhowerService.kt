package com.example.eisonhower_kotlin.network

import com.example.eisonhower_kotlin.network.responseObject.Login
import com.example.eisonhower_kotlin.network.responseObject.NumberOfTasks
import com.example.eisonhower_kotlin.network.responseObject.Register
import com.example.eisonhower_kotlin.network.responseObject.Tasks
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.bind.util.ISO8601Utils
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

    @Headers(
        "Accept: application/json"
    )
    @POST("tasks")
    fun createTask(
        @Header("x-access-token") jwtToken : String,
        @Body toCreateTask : toCreateTask
    ) : Call<Void>

    @Headers(
        "Accept: application/json"
    )
    @POST("tasks")
    fun updateTask(
        @Header("x-access-token") jwtToken : String,
        @Body toCreateTask : toCreateTask
    ) : Call<Void>
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

data class toCreateTask(
    @SerializedName("urgence") var urgence : String,
    @SerializedName("importance") var importance : String,
    @SerializedName("title") var title : String,
    @SerializedName("description") var description : String,
    @SerializedName("deadline") var deadline : String,
    @SerializedName("status") var status : String
)