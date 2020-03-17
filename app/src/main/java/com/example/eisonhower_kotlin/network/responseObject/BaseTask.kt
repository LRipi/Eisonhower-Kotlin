package com.example.eisonhower_kotlin.network.responseObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseTask {

    @SerializedName("urgence")
    @Expose
    var urgence: String? = null
    @SerializedName("importance")
    @Expose
    var importance: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("deadline")
    @Expose
    var deadline: String? = null
    @SerializedName("status")
    @Expose
    var status: String? = null

}
