package com.example.sample.login

import android.content.Intent
import android.os.Handler
import com.example.sample.R
import com.example.sample.base.BaseActivity
import com.example.sample.databinding.SplashBinding

class SplashActivity : BaseActivity<SplashBinding>() {
    override fun onPermissionsGranted(requestCode: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initUI(binding: SplashBinding) {

        Handler().postDelayed({
            kotlin.run {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }, 4000)
    }

    override fun contentView(): Int {
        return R.layout.activity_splash
    }
}