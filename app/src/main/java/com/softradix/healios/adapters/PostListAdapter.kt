package com.softradix.healios.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.softradix.healios.R
import com.softradix.healios.model.Posts

class PostListAdapter(val context: Context,
                      private val list: List<Posts>,
                      private val onCallback:( Posts)->Unit) :
    RecyclerView.Adapter<PostListAdapter.RecyclerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecyclerHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_item, parent, false)
    )


    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        val post = list[position]
        holder.postTitleTxt.text = post.title
        holder.postDescriptionTxt.text = post.body

        holder.itemView.setOnClickListener { onCallback(post) }
    }


    class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postTitleTxt: TextView = itemView.findViewById(R.id.postTitleTxt)
         val postDescriptionTxt: TextView = itemView.findViewById(R.id.postDescriptionTxt)
    }

    override fun getItemCount() = list.size

}