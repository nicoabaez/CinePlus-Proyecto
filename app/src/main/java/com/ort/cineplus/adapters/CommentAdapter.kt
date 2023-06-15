package com.ort.cineplus.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ort.cineplus.R
import com.ort.cineplus.databinding.ItemCommentBinding
import com.ort.cineplus.entities.Comment

class CommentAdapter (
    private var commentList: MutableList<Comment>,
    var onClick : (Int) -> Unit
    ) : RecyclerView.Adapter<CommentAdapter.CommentHolder>() {

        class CommentHolder(v: View) : RecyclerView.ViewHolder(v) {

            private val binding = ItemCommentBinding.bind(v)

            fun bind(m: Comment){
                this.setDetailComment(m.detailComment)
                this.setUserEmailComment(m.userEmail)
            }

            private fun setDetailComment(detailComment: String) { binding.detailComment.text = detailComment    }
            private fun setUserEmailComment(userEmail: String) {
                if(userEmail == "null"){
                    binding.userEmailComment.text = "Anonimo";
                }
                else{
                    binding.userEmailComment.text = userEmail;
                }

            }

            fun getCard(): CardView { return binding.commentCard }

        }

        override fun getItemCount(): Int { return commentList.size }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
            return CommentHolder(view)
        }

        override fun onBindViewHolder(holder: CommentHolder, position: Int) {
            val item : Comment = commentList[position]
            holder.bind(item)
            holder.getCard().setOnClickListener { onClick(position) }
        }
}