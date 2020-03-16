package com.example.eisonhower_kotlin.network.responseObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tasks {

    @SerializedName("success")
    @Expose
    var success: Boolean? = null
    @SerializedName("tasks")
    @Expose
    var tasks: List<Task>? = null

}
