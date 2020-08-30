package com.mkapp.ssot_example.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mkapp.ssot_example.db.entity.PostEntity

@Dao
interface PostDao {
    @Query("select * from post where page=:requestedPage")
    fun getPostsWithPage(requestedPage: Int): List<PostEntity>

    @Query("select * from post")
    fun getPosts(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostEntity>)
}