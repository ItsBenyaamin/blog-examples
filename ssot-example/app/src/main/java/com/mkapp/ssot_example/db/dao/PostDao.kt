package com.mkapp.ssot_example.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mkapp.ssot_example.db.entity.PostEntity

interface PostDao {
    @Query("select * from post where page=:page")
    fun getPosts(page: Int): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostEntity>)
}