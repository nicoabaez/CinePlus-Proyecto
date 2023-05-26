package com.ort.cineplus.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ort.cineplus.adapters.CommentAdapter
import com.ort.cineplus.databinding.FragmentCommentListBinding
import com.ort.cineplus.entities.Comment

class CommentListFragment : Fragment() {

    companion object {
        fun newInstance() = CommentListFragment()
    }

    private var _binding: FragmentCommentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CommentListViewModel
    private lateinit var adapter: CommentAdapter
    private var commentList : MutableList<Comment> = mutableListOf()


    //private lateinit var btnCommentList: Button
    //private lateinit var txtId : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentListBinding.inflate(inflater,container,false)

        //CREO UN COMENTARIO PARA PROBAR
        val c = Comment(1, "Pepancio", 12345,  "BEST PELICULA")
        commentList.add(c)

        initRecyclerView()
        //var id = CommentListArgs.fromBundle(requireArguments()).movieId

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
            val action = CommentListFragmentDirections.actionCommentListToCommentDetailFragment(commentList[position])
            findNavController().navigate(action)
        }
        binding.recyclerComment.adapter = adapter
    }

}