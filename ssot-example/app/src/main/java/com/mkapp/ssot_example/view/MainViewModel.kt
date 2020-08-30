package com.mkapp.ssot_example.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mkapp.ssot_example.repository.MainRepository

class MainViewModel : ViewModel() {
    private val repository = MainRepository

    private var currentPage = 0
    private var maxPage = 10

    fun getViewState(): LiveData<MainViewState> {
        return repository.viewState
    }

    fun loadPosts() {
        if (currentPage == maxPage) {
            return
        }
        currentPage += 1
        repository.getPosts(currentPage)
    }
}