package com.example.sample.register

import android.app.ProgressDialog
import android.content.Context
import android.support.annotation.NonNull
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private var context: RegisterActivity) {

    lateinit var mAuth: FirebaseAuth
    lateinit var progress: ProgressDialog

    fun doRegister(email: String, password: String, repassword: String) {
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Wait while loading...")
        progress.setCancelable(false)
        progress.show()
        if (password.equals(repassword)) {

            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(context, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(@NonNull task: Task<AuthResult>) {
                        if (task.isSuccessful()) {
                            progress.cancel()
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("createUserWithEmail", "createUserWithEmail:success")
                            Toast.makeText(
                                context, "Authentication Sucess.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            progress.cancel()
                            // If sign in fails, display a message to the user.
                            Log.w("createUserWithEmail", "createUserWithEmail:failure", task.exception)
                            if (task.exception!=null)
                            {
                                Toast.makeText(
                                    context, "Email Id Already Registered",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                })
        } else {
            Toast.makeText(context, "password mismatch", Toast.LENGTH_SHORT).show()
        }
    }
}