package com.example.sample.Home

import android.Manifest
import android.widget.Toast
import com.example.sample.R
import com.example.sample.base.BaseActivity
import com.example.sample.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override fun onPermissionsGranted(requestCode: Int) {
        Toast.makeText(this,"permission Granted",Toast.LENGTH_SHORT).show()
    }

    lateinit var mBinding: ActivityHomeBinding

    override fun contentView(): Int = R.layout.activity_home

    override fun initUI(binding: ActivityHomeBinding) {
        mBinding = binding

        mBinding.btnFile.setOnClickListener {

            requestAppPermissions(arrayOf(Manifest.permission.CALL_PHONE),
                R.string.runtime_permissions_txt, 101)
        }
    }
}




