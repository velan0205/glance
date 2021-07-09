package com.example.glance.retrofit

import com.example.glance.main.model.Posts
import com.example.glance.main.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    fun getPosts(): Call<List<Posts>>

    @GET("users")
    fun getUsers(): Call<List<Users>>

}