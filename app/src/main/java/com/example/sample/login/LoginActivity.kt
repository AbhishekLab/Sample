package com.example.sample.login

import android.util.Log.d
import com.example.sample.R
import com.example.sample.base.BaseActivity
import com.example.sample.databinding.ActivityLoginBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class LoginActivity :BaseActivity<ActivityLoginBinding>()  {

    lateinit var mBinding:ActivityLoginBinding

    lateinit var mAuth: FirebaseAuth

    override fun contentView(): Int = R.layout.activity_login

    override fun onPermissionsGranted(requestCode: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initUI(binding: ActivityLoginBinding) {
        this.mBinding=binding

        mAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.getCurrentUser()
        d("currentUser",currentUser.toString())
    }
}