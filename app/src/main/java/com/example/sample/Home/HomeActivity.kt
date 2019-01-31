package com.example.sample.Home

import android.Manifest
import android.util.Log
import android.util.Log.d
import com.example.sample.R
import com.example.sample.base.BaseActivity
import com.example.sample.databinding.ActivityHomeBinding
import com.example.sample.fileUtil.FetchFiles
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import javax.inject.Inject


class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    @Inject
    lateinit var backgroundOperation: FetchFiles
    lateinit var mBinding: ActivityHomeBinding
    private var directoriess: ArrayList<String>? = ArrayList()
    var mAuth: FirebaseAuth?=null

    private var mStorageRef: StorageReference? = null

    override fun contentView(): Int = R.layout.activity_home

    override fun onPermissionsGranted(requestCode: Int) {
       /* directoriess = backgroundOperation.fetchFileFromBackGround()
        if (directoriess!!.size != 0) {
            //firebase call
            if (directoriess!!.contains("no file")) {
                Log.d("nomedia", directoriess!!.size.toString())
            }

        }*/
    }

    override fun initUI(binding: ActivityHomeBinding) {
        mBinding = binding
        mAuth= FirebaseAuth.getInstance()
        d("mAuthFirebase",mAuth.toString())

        mBinding.btnFile.setOnClickListener {
            requestAppPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                R.string.runtime_permissions_txt, 101
            )
        }
       // mStorageRef = FirebaseStorage.getInstance().getReference()

    }
}




