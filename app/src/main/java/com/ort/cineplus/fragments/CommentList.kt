package com.ort.cineplus.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.ort.cineplus.R
import com.ort.cineplus.databinding.FragmentCommentListBinding
import com.ort.cineplus.databinding.FragmentMovieDetailBinding

class CommentList : Fragment() {

    companion object {
        fun newInstance() = CommentList()
    }

    private lateinit var viewModel: CommentListViewModel
    private lateinit var btnCommentList: Button
    private var _binding: FragmentCommentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var txtId : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommentListBinding.inflate(inflater,container,false)
        var id = CommentListArgs.fromBundle(requireArguments()).movieId

        return binding.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CommentListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}