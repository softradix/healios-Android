package com.softradix.healios.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.softradix.healios.R
import com.softradix.healios.model.Comments

class CommentListAdapter(
    val context: Context,
    private val list: List<Comments>?
) :
    RecyclerView.Adapter<CommentListAdapter.RecyclerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecyclerHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.comments_list_item, parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        val comment = list?.get(position)
        holder.commentHeadingTxt.text = comment?.name
        holder.commentDescriptionTxt.text = comment?.body
    }


    class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commentHeadingTxt: TextView = itemView.findViewById(R.id.tv_comment_heading)
        val commentDescriptionTxt: TextView = itemView.findViewById(R.id.tv_comment_description)
    }


    override fun getItemCount() = list?.size ?: 0

}