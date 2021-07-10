package com.example.glance.base

import androidx.lifecycle.ViewModel
import com.example.glance.BuildConfig
import com.example.glance.retrofit.ApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseViewModel : ViewModel() {

    val apiInterface: ApiInterface = Retrofit.Builder()
        .client(OkHttpClient.Builder().build())
        .baseUrl("${BuildConfig.BASE_URL}/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiInterface::class.java)
}