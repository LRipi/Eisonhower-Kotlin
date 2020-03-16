package com.example.eisonhower_kotlin.network.responseObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NumberOfTasks {
    @SerializedName("success")
    @Expose
    var success: Boolean? = null
    @SerializedName("number")
    @Expose
    var number: Int? = null
}