package com.example.eisonhower_kotlin.network

import okhttp3.*
import java.io.IOException
import java.net.URL


class ApiCalls {

    val base_url = URL("http://vps.lemartret.com:3000/")



    fun Auth (username: String, password: String) {
        android.Manifest.permission.INTERNET
    }

    fun Test() {
        println("lol")
    }
}