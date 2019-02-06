package com.example.sample.login

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Handler
import android.support.annotation.RequiresApi
import android.util.Base64
import android.util.Log.d
import android.view.View
import android.widget.Toast
import com.example.sample.R
import com.example.sample.base.BaseActivity
import com.example.sample.dashboard.DashboardActivity
import com.example.sample.databinding.ActivityLoginBinding
import com.example.sample.register.RegisterActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.regex.Pattern
import javax.inject.Inject


class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    lateinit var mBinding: ActivityLoginBinding

    @Inject
    lateinit var loginPresenter: LoginViewModel

    var callbackManager:CallbackManager? = null

    private val VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)

    override fun contentView(): Int = com.example.sample.R.layout.activity_login

    override fun onPermissionsGranted(requestCode: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initUI(binding: ActivityLoginBinding) {
        this.mBinding = binding


        try {
            val info = packageManager.getPackageInfo(
                "com.example.sample",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
              d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {

        } catch (e: NoSuchAlgorithmException) {

        }


        mBinding.txtSkip.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
        mBinding.btnLogin.setOnClickListener {

            if (validate(mBinding.edtId.text.toString(), mBinding.edtPass.text.toString())) {
                loginPresenter.doLogin(mBinding.edtId.text.toString(), mBinding.edtPass.text.toString())
            } else {
                Toast.makeText(this, "Please Enter your Id and Password ", Toast.LENGTH_SHORT).show()
            }
        }

        mBinding.fbRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        mBinding.txtForgotPassword.setOnClickListener {
            if (mBinding.edtId.text.isEmpty()) {
                Toast.makeText(this, "Enter Email Id", Toast.LENGTH_SHORT).show()
            } else {
                loginPresenter.resetPassword(mBinding.edtId.text.toString())
            }
        }

        mBinding.btnFacebook.setOnClickListener {
        mBinding.loginButton.performClick()
        }


        //facebook login

        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create()

        mBinding.loginButton.setReadPermissions("email", "public_profile")
        mBinding.loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                d("facebook", "facebook:onSuccess:$loginResult")
                Toast.makeText(this@LoginActivity,"sucess",Toast.LENGTH_SHORT).show()
            }

            override fun onCancel() {
                d("facebook:onCancel", "facebook:onCancel")
                // ...
            }

            override fun onError(error: FacebookException) {
                d("facebook:onError", "facebook:onError", error)
                // ...
            }
        })
        // ...

        Handler().postDelayed({
            /* Create an Intent that will start the Menu-Activity. */

            mBinding.txtSkip.visibility = View.VISIBLE
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }, 2000)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
    }



    private fun validate(emailStr: String, password: String): Boolean {
        val matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr)
        return (password.length > 0 || password == ";") && matcher.find()
    }
}
