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
    private var postsList = MutableLiveData<List<Post>>()
    private val mainViewState = MutableLiveData<MainViewState>()

    private val database = AppDatabase.instance

    val viewState get(): LiveData<MainViewState> = mainViewState

    init {
        postsList.value = ArrayList()
    }

    fun getPosts(page: Int) {
        ExecutorsUtil.runIO {
            mainViewState.postValue(MainViewState.Loading("loading"))
            val pagePosts = database.postsDao().getPostsWithPage(page)
            if (pagePosts.isNotEmpty()) {
                val newList = postsList.value as ArrayList
                newList.addAll(
                    DataMapper.entityToDataModel(pagePosts)
                )
                postsList.postValue(newList)
                mainViewState.postValue(MainViewState.Successful(postsList.value!!))
            } else {
                getPostsFromServer(page)
            }
        }
    }

    private fun getPostsFromServer(page: Int) {
        ExecutorsUtil.runIO {
            val request = ApiProvider.instance.getPosts(page)
            val response = request.execute()
            if (response.isSuccessful) {
                insertIntoDatabase(response.body()!!, page)
                updateCacheList()
            }else {
                mainViewState.postValue(MainViewState.Error(response.errorBody()?.string()!!))
            }
        }
    }

    private fun insertIntoDatabase(list: List<Post>, page: Int) {
        database.postsDao().insertPosts(
            DataMapper.dataModelToEntity(list, page)
        )
    }

    private fun updateCacheList() {
        val list = database.postsDao().getPosts()
        postsList.postValue(
            DataMapper.entityToDataModel(list)
        )
        mainViewState.postValue(MainViewState.Successful(postsList.value!!))
    }
}