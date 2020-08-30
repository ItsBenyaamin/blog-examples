package com.mkapp.ssot_example.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId")
    @Expose
    val userId: Int,
    @SerializedName("id")
    @Expose
    val postId: Int,
    @SerializedName("title")
    @Expose
    val postTitle: String,
    @SerializedName("body")
    @Expose
    val postBody: String,
)