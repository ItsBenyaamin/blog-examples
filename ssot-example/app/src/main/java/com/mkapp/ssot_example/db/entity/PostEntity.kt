package com.mkapp.ssot_example.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class PostEntity(
    @ColumnInfo(name = "user_id")
    var userId: Int,
    @ColumnInfo(name = "post_id")
    var postId: Int,
    @ColumnInfo(name = "post_title")
    var postTitle: String,
    @ColumnInfo(name = "post_body")
    var postBody: String,
    @ColumnInfo(name = "page")
    var page: Int
) {
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int? = null
}