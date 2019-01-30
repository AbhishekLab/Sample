package com.example.sample.Home

import com.example.sample.R
import com.example.sample.base.BaseActivity
import com.example.sample.databinding.ActivityHomeBinding
import com.example.sample.permission.PermissionClass
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    lateinit var mBinding: ActivityHomeBinding

    @Inject
    lateinit var permission: PermissionClass

    override fun contentView(): Int = R.layout.activity_home

    override fun initUI(binding: ActivityHomeBinding) {
        mBinding = binding

        mBinding.btnFile.setOnClickListener {

          permission.permission()
        }
    }
}




