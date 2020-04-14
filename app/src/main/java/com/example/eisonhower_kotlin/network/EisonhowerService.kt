package com.example.eisonhower_kotlin.network

import com.example.eisonhower_kotlin.network.responseObject.*
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*
import com.example.eisonhower_kotlin.network.responseObject.About as About

interface EisonhowerService {

    @GET("about.json")
    fun about() : Call<About>

    @Headers(
        "Content-Type:application/json"
    )
    @POST("users/login")
    fun login(
        @Body LoginData : LoginData
    ) : Call<Login>

    @Headers(
        "Content-Type: application/json"
    )
    @POST("users/create")
    fun register(
        @Body RegisterData : RegisterData
    ) : Call<Register>

    @GET("tasks/total")
    fun numberOfTasks(
        @Header("x-access-token") jwtToken : String,
        @Query ("importance") importance : Boolean,
        @Query ("urgence") urgence : Boolean
    ) : Call<NumberOfTasks>

    @GET("tasks")
    fun listOfTasks (
        @Header("x-access-token") jwtToken : String,
        @Query ("importance") importance : Boolean,
        @Query ("urgence") urgence : Boolean
    ) : Call<Tasks>

    @GET("tasks")
    fun AllTasks (
        @Header("x-access-token") jwtToken : String
    ) : Call<Tasks>

    @Headers(
        "Content-Type: application/json"
    )
    @POST("tasks")
    fun createTask(
        @Header("x-access-token") jwtToken : String,
        @Body toCreateTask : toCreateTask
    ) : Call<Void>

    @Headers(
        "Content-Type: application/json"
    )
    @PUT("tasks/{id}")
    fun updateTask(
        @Header("x-access-token") jwtToken : String,
        @Path("id") id : String,
        @Body toCreateTask : toCreateTask
    ) : Call<Void>

    @GET("tasks/{id}")
    fun getTask(
        @Header("x-access-token") jwtToken : String,
        @Path("id") id : String
    ) : Call<BaseTask>

    @DELETE ("users")
    fun deleteUser (
        @Header("x-access-token") jwtToken : String
    ) : Call<Void>

    @DELETE ("tasks/all")
    fun deleteAllTasks (
        @Header("x-access-token") jwtToken : String
    ) : Call<Void>

    @DELETE ("tasks/{id}")
    fun deleteTask (
        @Header("x-access-token") jwtToken : String,
        @Path("id") id : String
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
