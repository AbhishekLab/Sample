package com.example.sample.login

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.Toast
import com.example.sample.R
import com.example.sample.base.BaseActivity
import com.example.sample.databinding.ActivityLoginBinding
import com.example.sample.global.Constants.REQUEST_CODE_REGISTER
import com.example.sample.register.RegisterActivity
import java.util.regex.Pattern
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    lateinit var mBinding: ActivityLoginBinding

    @Inject
    lateinit var loginPresenter: LoginViewModel

    private val VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)

    override fun contentView(): Int = R.layout.activity_login

    override fun onPermissionsGranted(requestCode: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initUI(binding: ActivityLoginBinding) {
        this.mBinding = binding

        mBinding.btnLogin.setOnClickListener {

            if (validate(mBinding.edtId.text.toString(), mBinding.edtPass.text.toString())) {
                if (loginPresenter.doLogin(mBinding.edtId.text.toString(), mBinding.edtPass.text.toString())) {

                }
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
    }

    private fun validate(emailStr: String, password: String): Boolean {
        val matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr)
        return (password.length > 0 || password == ";") && matcher.find()
    }
}
