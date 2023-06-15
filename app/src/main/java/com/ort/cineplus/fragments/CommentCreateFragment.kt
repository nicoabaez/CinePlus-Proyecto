package com.ort.cineplus.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.ort.cineplus.databinding.FragmentCommentCreateBinding
import com.ort.cineplus.entities.Comment
import com.ort.cineplus.viewmodels.CommentCreateViewModel

class CommentCreateFragment : Fragment() {

    companion object {
        fun newInstance() = CommentCreateFragment()
    }

    private var _binding: FragmentCommentCreateBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CommentCreateViewModel
    private lateinit var btnCreateCommentConfirm : Button
    private lateinit var commentInput: EditText
    private lateinit var title: TextView
    private lateinit var comment: Comment

    private val auth: FirebaseAuth = FirebaseAuth.getInstance();

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommentCreateBinding.inflate(inflater,container,false)

        btnCreateCommentConfirm = binding.btnCreateCommentConfirm
        commentInput = binding.plainTxtCommentInput
        title = binding.txtTitle

        title.text = "Crea tu Comentario";

        var movieId = CommentCreateFragmentArgs.fromBundle(requireArguments()).movieId

        btnCreateCommentConfirm.text = "Crear";
        btnCreateCommentConfirm.setOnClickListener() {
            if (viewModel.checkComment(commentInput.text.toString())) {
                comment = Comment(
                    auth.currentUser?.email.toString(),
                    movieId,
                    commentInput.text.toString()
                )
                if (viewModel.postComment(comment)) {
                    Snackbar.make(binding.root, "Your_Text", Snackbar.LENGTH_LONG);

                } else {
                    Snackbar.make(binding.root, "An Error occurred", Snackbar.LENGTH_LONG).show()
                }
            } else {
                Snackbar.make(binding.root, "The comment can´t be empty...", Snackbar.LENGTH_LONG).show()
            }
            val fm = fragmentManager;
            fm?.popBackStack();
        }


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CommentCreateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}