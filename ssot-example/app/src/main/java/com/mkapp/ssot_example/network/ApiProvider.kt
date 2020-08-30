package com.mkapp.ssot_example.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiProvider {
    private lateinit var api: ApiInterface

    companion object {
        val instance by lazy {
            val provider = ApiProvider()
            val retrofitClient = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            provider.api = retrofitClient.create(ApiInterface::class.java)
            provider.api
        }
    }
}