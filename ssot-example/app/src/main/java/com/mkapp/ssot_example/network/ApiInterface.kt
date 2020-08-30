package com.mkapp.ssot_example.network

import com.mkapp.ssot_example.network.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("posts")
    fun getPosts(@Query("_page") page: Int): Call<List<Post>>
}