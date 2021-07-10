package com.example.glance.main.view

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.glance.main.adapter.PostsAdapter
import com.example.glance.databinding.ActivityMainBinding
import com.example.glance.main.viewmodel.ViewModelMainActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var postsAdapter: PostsAdapter
    private val viewModel: ViewModelMainActivity by lazy {
        ViewModelProvider(this).get(ViewModelMainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postsAdapter = PostsAdapter()

        val layoutManager1 = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        binding.rvRecyclerView.adapter = postsAdapter
        with(binding.rvRecyclerView) {
            val attrs = intArrayOf(android.R.attr.listDivider)
            val a = context.obtainStyledAttributes(attrs)
            val dividerColor = ColorDrawable(0xAAACACAC.toInt())
            val insetDivider = InsetDrawable(dividerColor, 0, 0, 0, 0)
            a.recycle()
            val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(insetDivider)
            addItemDecoration(
                itemDecoration
            )
            layoutManager = layoutManager1
        }

        viewModel.liveDataPosts.observe(this, {
            postsAdapter.setData(it)
        })

        viewModel.liveDataUsers.observe(this, {
            binding.tvCount.text = "Count: $it"
        })

        viewModel.liveDataTime.observe(this, {
            binding.tvTimeTaken.text = "Total Time Taken: $it"
        })

        viewModel.liveError.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    fun onStart(view: View) {
        viewModel.fetchPosts()
        viewModel.progress.observe(this, {
            binding.progressBar.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })
    }
}