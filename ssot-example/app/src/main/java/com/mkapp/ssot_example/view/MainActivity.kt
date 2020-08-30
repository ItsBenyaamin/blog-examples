package com.mkapp.ssot_example.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkapp.ssot_example.adapter.PostsAdapter
import com.mkapp.ssot_example.databinding.ActivityMainBinding
import com.mkapp.ssot_example.util.addEndOfListListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setupRecyclerView()
        observeTheDataFromViewModel()
    }

    private fun setupRecyclerView() {
        postsAdapter = PostsAdapter()
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.postsRecyclerView.adapter = postsAdapter

        binding.postsRecyclerView.addEndOfListListener {
            viewModel.loadPosts()
        }
    }

    private fun observeTheDataFromViewModel() {
        viewModel.getViewState().observe(this) {
            when(it) {
                is MainViewState.Loading ->  {
                    loadingState(true)
                }
                is MainViewState.Successful -> {
                    postsAdapter.setPostsList(it.list)
                    loadingState(false)
                }
                is MainViewState.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                    loadingState(false)
                }
            }
        }
        viewModel.loadPosts()
    }

    private fun loadingState(state: Boolean) {
        if (state)
            binding.loading.visibility = VISIBLE
        else
            binding.loading.visibility = INVISIBLE
    }
}