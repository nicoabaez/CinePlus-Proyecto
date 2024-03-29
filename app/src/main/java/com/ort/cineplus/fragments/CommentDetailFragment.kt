package com.ort.cineplus.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ort.cineplus.R
import com.ort.cineplus.viewmodels.CommentDetailViewModel
class CommentDetailFragment : Fragment() {

    companion object {
        fun newInstance() = CommentDetailFragment()
    }

    private lateinit var viewModel: CommentDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[CommentDetailViewModel::class.java]
        // TODO: Use the ViewModel
    }
}