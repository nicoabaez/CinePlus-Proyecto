package com.ort.cineplus.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ort.cineplus.R
import com.ort.cineplus.databinding.ItemMovieBinding
import com.ort.cineplus.entities.Comment

class CommentAdapter (
    private var commentList: MutableList<Comment>,
    var onClick : (Int) -> Unit
    ) : RecyclerView.Adapter<CommentAdapter.CommentHolder>() {

        class CommentHolder(v: View) : RecyclerView.ViewHolder(v) {

            private val binding = ItemMovieBinding.bind(v)

            fun bind(m: Comment){
                //this.setTitle(m.title)
                //this.setDesc(m.overview)
            }

            //private fun setTitle(title: String) { binding.txtTitle.text = title    }
            //private fun setDesc(desc: String)   { binding.txtDesc.text = desc       }

            fun getCard(): CardView { return binding.movieCard }

        }

        override fun getItemCount(): Int { return commentList.size }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
            return CommentHolder(view)
        }

        override fun onBindViewHolder(holder: CommentHolder, position: Int) {
            val item : Comment = commentList[position]
            holder.bind(item)
            holder.getCard().setOnClickListener { onClick(position) }
        }
}