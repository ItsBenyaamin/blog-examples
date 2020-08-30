package com.mkapp.ssot_example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkapp.ssot_example.databinding.PostsAdapterItemBinding
import com.mkapp.ssot_example.network.model.Post

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {
    private var postsList: List<Post> = emptyList()


    fun setPostsList(list: List<Post>) {
        postsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(
            PostsAdapterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.binding.postTitle.text = postsList[holder.adapterPosition].postTitle
        holder.binding.postBody.text = postsList[holder.adapterPosition].postBody
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    class PostsViewHolder(val binding: PostsAdapterItemBinding) : RecyclerView.ViewHolder(binding.root)
}