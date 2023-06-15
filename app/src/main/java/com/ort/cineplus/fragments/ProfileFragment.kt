package com.ort.cineplus.fragments

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ort.cineplus.R
import com.ort.cineplus.activities.LoginActivity
import com.ort.cineplus.viewmodels.ProfileViewModel

class ProfileFragment : Fragment() {


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

        if(user == null){
            showProfileNoLogged()
        }else {
            showProfileLogged()
            setInfoProfile()
        }

        btnLogout.setOnClickListener(){
            viewModel.logout();
            findNavController().navigate(goToMovieList)
        }

        btnGoToLogin.setOnClickListener(){
            goToLoggin()
        }

        return v
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
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

    fun setInfoProfile(){
        lblEmailProfile.text = user?.email.toString()
    }
}