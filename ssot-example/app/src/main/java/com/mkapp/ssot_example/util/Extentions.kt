package com.mkapp.ssot_example.util

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addEndOfListListener(callback: () -> Unit) {
    this.addOnScrollListener(object: RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                callback.invoke()
            }
        }
    })
}