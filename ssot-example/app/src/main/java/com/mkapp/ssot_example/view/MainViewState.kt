package com.mkapp.ssot_example.view

import com.mkapp.ssot_example.network.model.Post

sealed class MainViewState {
    data class Loading(var msg: String): MainViewState()
    data class Successful(var list: List<Post>): MainViewState()
    data class Error(var error: String): MainViewState()
}