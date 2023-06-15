package com.ort.cineplus.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.cineplus.adapters.CommentAdapter
import com.ort.cineplus.databinding.FragmentCommentListBinding
import com.ort.cineplus.entities.Comment
import com.ort.cineplus.viewmodels.CommentListViewModel

class CommentListFragment : Fragment() {

    companion object {
        fun newInstance() = CommentListFragment()
    }

    private var _binding: FragmentCommentListBinding? = null
    private val binding get() = _binding!!

    var database = Firebase.firestore

    private lateinit var viewModel: CommentListViewModel
    private lateinit var adapter: CommentAdapter
    private var commentList : MutableList<Comment> = mutableListOf()
    private lateinit var btnCreateComment : Button


    //private lateinit var btnCommentList: Button
    //private lateinit var txtId : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentListBinding.inflate(inflater,container,false)

        var movieId = CommentListFragmentArgs.fromBundle(requireArguments()).movieId

        commentList.clear();

        database.collection("Comments")
            .whereEqualTo("movieId", movieId)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    commentList.add(document.toObject<Comment>())
                }

                initRecyclerView()
            }
            .addOnFailureListener { exception ->
                Log.w("Main Activity", "Error getting documents.", exception)
            }

        btnCreateComment = binding.btnCreateComment;

        btnCreateComment.text = "Crear Comentario";
        btnCreateComment.setOnClickListener(){
            val action = CommentListFragmentDirections.actionCommentListToCommentCreateFragment(movieId)
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CommentListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun initRecyclerView(){
        binding.recyclerComment.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL,false)
        adapter = CommentAdapter(commentList){position ->

        }
        binding.recyclerComment.adapter = adapter
    }

}