package com.example.sample.login

import android.app.ProgressDialog
import android.support.annotation.NonNull
import android.util.Log
import android.util.Log.d
import android.widget.Toast
import com.example.sample.login.LoginActivity
import com.example.sample.register.RegisterActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginViewModel @Inject constructor(private var context: LoginActivity) {

    lateinit var mAuth: FirebaseAuth
    lateinit var progress: ProgressDialog

    fun doLogin(email: String, password: String) {
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Wait while loading...")
        progress.setCancelable(false)
        progress.show()

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    progress.dismiss()
                    // Sign in success, update UI with the signed-in user's information
                    d("signInWithEmail", "signInWithEmail:success")
                    Toast.makeText(
                        context, "Authentication Sucess.",
                        Toast.LENGTH_SHORT
                    ).show()
                    val user = mAuth.currentUser
                } else {
                    progress.dismiss()
                    // If sign in fails, display a message to the user.
                    d("signInWithEmail", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }


}