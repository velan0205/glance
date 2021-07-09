package com.example.glance.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.glance.BR
import com.example.glance.R
import com.example.glance.databinding.GridListRowBinding
import com.example.glance.main.model.Posts

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    var list: List<Posts> = emptyList()

    inner class ViewHolder(val binding: GridListRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding: GridListRowBinding = DataBindingUtil.inflate(

            LayoutInflater.from(parent.context),
            R.layout.grid_list_row,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        val postsList = list[position]
        holder.binding.setVariable(BR.userId, postsList.userId)
        holder.binding.setVariable(BR.id, postsList.id)
        holder.binding.setVariable(BR.title, postsList.title)
        holder.binding.setVariable(BR.body, postsList.body)
        holder.binding.executePendingBindings()
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: List<Posts>) {
        this.list = list
        notifyDataSetChanged()
    }

}