package com.ort.cineplus.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import com.ort.cineplus.R
import com.ort.cineplus.activities.LoginActivity
import com.ort.cineplus.activities.MainActivity
import org.w3c.dom.Text
import kotlin.math.log

class ProfileFragment : Fragment() {

    private val CODE = 50
    private lateinit var viewModel: ProfileViewModel
    lateinit var titleInfo: TextView
    lateinit var btnGoToLogin: Button
    lateinit var btnLogout: Button
    lateinit var lblTitleUsernameProfile: TextView
    lateinit var lblTitleEmailProfile: TextView
    lateinit var lblUsernameProfile: TextView
    lateinit var lblEmailProfile: TextView
    lateinit var imgProfile: ImageView
    private val user = Firebase.auth.currentUser
    private val goToMovieList = ProfileFragmentDirections.profileGoToMovieList()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        titleInfo = v.findViewById(R.id.lblMsgNoLogged)
        btnGoToLogin = v.findViewById(R.id.btnGoToLogin)
        btnLogout = v.findViewById(R.id.btnLogout)
        lblEmailProfile = v.findViewById(R.id.lblEmailProfile)
        lblUsernameProfile = v.findViewById(R.id.lblUserNnnnameProfile)
        lblTitleUsernameProfile = v.findViewById(R.id.lblTitleUserNameProfile)
        lblTitleEmailProfile = v.findViewById(R.id.lblTitleEmailProfile)
        imgProfile = v.findViewById(R.id.imgProfile)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.getUserDatabase()

        if(user == null){
            showProfileNoLogged()
        }else {
            showProfileLogged()
        }

        btnLogout.setOnClickListener(){
            viewModel.logout();
            findNavController().navigate(goToMovieList)
        }

        btnGoToLogin.setOnClickListener(){
            goToLoggin()
        }

        imgProfile.setOnClickListener(){
            this.activity?.let { it1 -> viewModel.changeImageProfile(it1, this.CODE) }
        }

        viewModel.user.observe(viewLifecycleOwner) { user ->
            user?.let {
                setInfoProfile()
                showProfileLogged()
            }
        }

        return v
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }


    private fun showProfileLogged(){
        titleInfo.visibility = View.GONE;
        btnGoToLogin.visibility = View.GONE;
        btnLogout.visibility = View.VISIBLE
        lblTitleEmailProfile.visibility = View.VISIBLE
        lblTitleUsernameProfile.visibility = View.VISIBLE
        lblEmailProfile.visibility = View.VISIBLE
        lblUsernameProfile.visibility = View.VISIBLE
        imgProfile.visibility = View.VISIBLE
        setInfoProfile()

    }

    private fun showProfileNoLogged(){
        titleInfo.visibility = View.VISIBLE;
        btnGoToLogin.visibility = View.VISIBLE;
        btnLogout.visibility = View.GONE;
        lblTitleEmailProfile.visibility = View.GONE
        lblTitleUsernameProfile.visibility = View.GONE
        lblEmailProfile.visibility = View.GONE
        lblUsernameProfile.visibility = View.GONE
        imgProfile.visibility = View.GONE
    }


    private fun goToLoggin(){
        startActivity(Intent(activity, LoginActivity::class.java))
    }

    private fun setInfoProfile(){
        lblUsernameProfile.text = viewModel.user.value
        lblEmailProfile.text = user?.email.toString()
    }


}