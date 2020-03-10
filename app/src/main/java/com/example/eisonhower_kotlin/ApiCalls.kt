package com.example.eisonhower_kotlin

import android.Manifest
import androidx.core.app.ActivityCompat
import java.net.HttpURLConnection
import java.net.URL


class ApiCalls {

    val base_url = URL("http://vps.lemartret.com:3000/")

    public fun Auth (username: String, password: String) {
        android.Manifest.permission.INTERNET
    }
}