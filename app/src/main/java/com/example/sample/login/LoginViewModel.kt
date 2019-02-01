package com.example.sample.login

import android.app.ProgressDialog
import android.util.Log.d
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.yarolegovich.lovelydialog.LovelyInfoDialog
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

    fun resetPassword(email: String) {
        mAuth = FirebaseAuth.getInstance()
        mAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    d("reset","Reset")
                }
                else{
                    d("FailedNotReset","NotReset")
                }
            }
    }
}