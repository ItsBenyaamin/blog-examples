package com.mkapp.ssot_example.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mkapp.ssot_example.db.AppDatabase
import com.mkapp.ssot_example.network.ApiProvider
import com.mkapp.ssot_example.network.model.Post
import com.mkapp.ssot_example.util.DataMapper
import com.mkapp.ssot_example.util.ExecutorsUtil
import com.mkapp.ssot_example.view.MainViewState

object MainRepository {
    private var _posts = MutableLiveData<List<Post>>()
    private val viewState = MutableLiveData<MainViewState>()

    private val database = AppDatabase.instance
    private var currentPage = 0

    val posts get(): LiveData<List<Post>> = _posts

    init {
        _posts.value = emptyList()
    }

    fun getPosts() {
        ExecutorsUtil.runIO {
            viewState.postValue(MainViewState.Loading("loading"))
            val pagePosts = database.postsDao.getPosts(currentPage)
            if (pagePosts.isNotEmpty()) {
                val newList = _posts.value as ArrayList
                newList.addAll(
                    DataMapper.entityToDataModel(pagePosts)
                )
                _posts.postValue(newList)
                viewState.postValue(MainViewState.Successful(_posts.value!!))
            } else {
                getPostsFromServer(currentPage)
            }
        }
    }

    private fun getPostsFromServer(page: Int) {
        ExecutorsUtil.runIO {
            val request = ApiProvider.instance.getPosts(page)
            val response = request.execute()
            if (response.isSuccessful) {
                insertIntoDatabase(response.body()!!, page)
                updateCacheList(page)
            }else {
                viewState.postValue(MainViewState.Error(response.errorBody()?.string()!!))
            }
        }
    }

    private fun insertIntoDatabase(list: List<Post>, page: Int) {
        database.postsDao.insertPosts(
            DataMapper.dataModelToEntity(list, page)
        )
    }

    private fun updateCacheList(page: Int) {
        val list = _posts.value as ArrayList
        val newList = database.postsDao.getPosts(page)
        list.addAll(
            DataMapper.entityToDataModel(newList)
        )
        _posts.postValue(list)
        viewState.postValue(MainViewState.Successful(_posts.value!!))
    }
}