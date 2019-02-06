package com.example.sample.login

import android.content.Intent
import android.util.Log.d
import android.view.View
import android.widget.Toast
import com.example.sample.dashboard.DashboardActivity
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginViewModel @Inject constructor(private var context: LoginActivity) {

    lateinit var mAuth: FirebaseAuth

    fun doLogin(email: String, password: String) {
        context.mBinding.progressBar.visibility = View.VISIBLE
        mAuth = FirebaseAuth.getInstance()

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    context.mBinding.progressBar.visibility = View.GONE
                    context.startActivity(Intent(context, DashboardActivity::class.java))
                    context.mBinding.edtId.setText("")
                    context.mBinding.edtPass.setText("")
                } else {
                    // If sign in fails, display a message to the user.
                    context.mBinding.edtPass.setText("")
                    context.mBinding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        context, "Authentication failed." + task.exception.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun resetPassword(email: String) {
        mAuth = FirebaseAuth.getInstance()
        mAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    d("reset", "Reset")
                } else {
                    d("FailedNotReset", "NotReset")
                }
            }
    }
}