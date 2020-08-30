package com.mkapp.ssot_example.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mkapp.ssot_example.network.model.Post
import com.mkapp.ssot_example.repository.MainRepository
import kotlin.math.max

class MainViewModel : ViewModel() {
    private val repository = MainRepository

    private var currentPage = 0
    private var maxPage = 10

    fun getPostsList(): LiveData<List<Post>> {
        return repository.posts
    }

    fun loadMorePosts(page: Int) {
        if (currentPage == maxPage) {
            return
        }
        repository.getPosts()
    }
}