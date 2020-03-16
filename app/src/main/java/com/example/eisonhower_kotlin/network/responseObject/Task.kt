package com.example.eisonhower_kotlin.network.responseObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Task {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("id_user")
    @Expose
    var idUser: Int? = null
    @SerializedName("urgence")
    @Expose
    var urgence: Int? = null
    @SerializedName("importance")
    @Expose
    var importance: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("date_creation")
    @Expose
    var dateCreation: String? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("deadline")
    @Expose
    var deadline: String? = null

}
