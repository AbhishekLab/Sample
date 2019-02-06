package com.example.sample.dashboard

import android.content.Intent
import android.support.v4.view.GravityCompat
import android.widget.Toast
import com.example.sample.R
import com.example.sample.base.BaseActivity
import com.example.sample.databinding.ActivityDashboardBinding
import com.example.sample.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.toolbar_dashboard.*

class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    lateinit var mBinding: ActivityDashboardBinding

    override fun contentView(): Int = R.layout.activity_dashboard

    override fun onPermissionsGranted(requestCode: Int) {
    }

    override fun initUI(binding: ActivityDashboardBinding) {
        mBinding = binding

        setSideMenu()
        fl_toggle.setOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }

        if (FirebaseAuth.getInstance().currentUser != null) {
            mBinding.btnLogout.text = "Logout"
        } else {
            mBinding.btnLogout.text = "Sign-In"
        }

        mBinding.btnLogout.setOnClickListener {

            if (mBinding.btnLogout.text.equals("Sign-In")) {
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                FirebaseAuth.getInstance().signOut()
                finish()
            }
        }
    }

    private fun setSideMenu() {

        mBinding.navigationView.layoutParams.width = (resources.displayMetrics.widthPixels * 0.80).toInt()
        mBinding.navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    drawer_layout.closeDrawers()
                }
                R.id.menu_profile -> {
                    Toast.makeText(this, "share", Toast.LENGTH_SHORT).show()
                    drawer_layout.closeDrawers()
                }
                R.id.menu_faq -> {
                    Toast.makeText(this, "help", Toast.LENGTH_SHORT).show()
                    drawer_layout.closeDrawers()
                }
            }
            true
        }
    }
}