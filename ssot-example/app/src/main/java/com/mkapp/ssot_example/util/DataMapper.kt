package com.mkapp.ssot_example.util

import com.mkapp.ssot_example.db.entity.PostEntity
import com.mkapp.ssot_example.network.model.Post

object DataMapper {

    fun dataModelToEntity(list: List<Post>, page: Int): List<PostEntity> {
        val newList = ArrayList<PostEntity>()
        list.forEach {
            newList.add(
                PostEntity(
                    it.userId,
                    it.postId,
                    it.postTitle,
                    it.postBody,
                    page
                )
            )
        }
        return newList
    }

    fun entityToDataModel(list: List<PostEntity>): List<Post> {
        val newList = ArrayList<Post>()
        list.forEach {
            newList.add(
                Post(
                    it.userId,
                    it.postId,
                    it.postTitle,
                    it.postBody
                )
            )
        }
        return newList
    }

}