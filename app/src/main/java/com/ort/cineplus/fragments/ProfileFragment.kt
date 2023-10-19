package com.ort.cineplus.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
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
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ort.cineplus.R
import com.ort.cineplus.activities.LoginActivity

class ProfileFragment : Fragment() {

    private val CODE: Int = 50
    private lateinit var viewModel: ProfileViewModel
    private lateinit var tittleInfo: TextView
    private lateinit var btnGoToLogin: Button
    private lateinit var btnLogout: Button
    private lateinit var lblTitleUsernameProfile: TextView
    private lateinit var lblTitleEmailProfile: TextView
    private lateinit var lblUsernameProfile: TextView
    private lateinit var lblEmailProfile: TextView
    private lateinit var imgProfile: ImageView
    private val user = Firebase.auth.currentUser
    private val goToMovieList = ProfileFragmentDirections.profileGoToMovieList()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        tittleInfo = v.findViewById(R.id.lblMsgNoLogged)
        btnGoToLogin = v.findViewById(R.id.btnGoToLogin)
        btnLogout = v.findViewById(R.id.btnLogout)
        lblEmailProfile = v.findViewById(R.id.lblEmailProfile)
        lblUsernameProfile = v.findViewById(R.id.lblUserNnnnameProfile)
        lblTitleUsernameProfile = v.findViewById(R.id.lblTitleUserNameProfile)
        lblTitleEmailProfile = v.findViewById(R.id.lblTitleEmailProfile)
        imgProfile = v.findViewById(R.id.imgProfile)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        viewModel.getUserDatabase()

        if(user == null){
            showProfileNoLogged()
        }else {
            showProfileLogged()
        }

        btnLogout.setOnClickListener{
            viewModel.logout()
            findNavController().navigate(goToMovieList)
        }

        btnGoToLogin.setOnClickListener{
            goToLogin()
        }

        imgProfile.setOnClickListener{
           /* this.activity?.let { it1 -> viewModel.changeImageProfile(it1, 50) }*/
            selectImageProfile(CODE)
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
        //viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult: called")
        if (requestCode == 50 && resultCode == Activity.RESULT_OK) {
            data?.data?.let { imageUri ->
                viewModel.saveImageToFirebaseStorage(imageUri)
            }
        }
    }


    private fun showProfileLogged(){
        tittleInfo.visibility = View.GONE
        btnGoToLogin.visibility = View.GONE
        btnLogout.visibility = View.VISIBLE
        lblTitleEmailProfile.visibility = View.VISIBLE
        lblTitleUsernameProfile.visibility = View.VISIBLE
        lblEmailProfile.visibility = View.VISIBLE
        lblUsernameProfile.visibility = View.VISIBLE
        imgProfile.visibility = View.VISIBLE
        setInfoProfile()

    }

    private fun showProfileNoLogged(){
        tittleInfo.visibility = View.VISIBLE
        btnGoToLogin.visibility = View.VISIBLE
        btnLogout.visibility = View.GONE
        lblTitleEmailProfile.visibility = View.GONE
        lblTitleUsernameProfile.visibility = View.GONE
        lblEmailProfile.visibility = View.GONE
        lblUsernameProfile.visibility = View.GONE
        imgProfile.visibility = View.GONE
    }


    private fun goToLogin(){
        startActivity(Intent(activity, LoginActivity::class.java))
    }

    private fun setInfoProfile(){
        lblUsernameProfile.text = viewModel.user.value
        lblEmailProfile.text = user?.email.toString()
        val uri = viewModel.getImgUser(user?.uid.toString() )
        if(uri != ""){
            //falta completar para cambiar la imagen del perfil
            Glide.with(this).load(viewModel.getImgUser(user?.uid.toString())).into(imgProfile)
        }
        Log.d(TAG, "la uri de la imagen es: + ${uri}")

    }

    private fun selectImageProfile(code: Int){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        this.startActivityForResult(intent, code)
    }


}