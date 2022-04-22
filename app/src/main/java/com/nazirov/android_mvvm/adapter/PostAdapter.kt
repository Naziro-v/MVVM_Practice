package com.nazirov.android_mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nazirov.android_mvvm.R
import com.nazirov.android_mvvm.activity.MainActivity
import com.nazirov.android_mvvm.model.Post
import com.nazirov.android_mvvm.utils.Utils

class PostAdapter(var activity: MainActivity, var items: ArrayList<Post>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_poster_list,parent , false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = items[position]
        if (holder is PostViewHolder){
            var llPoster = holder.llPoster
            var tvTitle = holder.tvTitle
            var tvBody = holder.tvBody
            tvTitle.setText(post.title.toUpperCase())
            tvBody.setText(post.body)

            llPoster.setOnLongClickListener {
                deletePostDialog(post)
                false
            }



        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class PostViewHolder(var view: View): RecyclerView.ViewHolder(view){
        var llPoster  : LinearLayout
        var tvTitle: TextView
        var tvBody: TextView

        init {
            llPoster = view.findViewById(R.id.llPoster)
            tvTitle = view.findViewById(R.id.tvTitle)
            tvBody = view.findViewById(R.id.tvBody)
        }
    }

    fun deletePostDialog(post: Post) {
        val title = "Delete"
        val body = "Do you want to delete?"
        Utils.customDialog(activity, title, body, object : Utils.DialogListener {
            override fun onPositiveClick() {
                activity.viewModel.apiPostList()
                // apiPostDelete(post)
            }

            override fun onNegativeClick() {

            }
        })
    }
}