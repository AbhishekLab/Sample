package com.example.sample.permission

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import com.example.sample.R
import com.example.sample.global.Constants
import javax.inject.Inject

class PermissionClass @Inject constructor()  : RuntimePermissionsActivity() {

    override fun onPermissionsGranted(requestCode: Int) {
       Log.d("pemissionSucess","permissionSucess")
        startActivity(Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + 123456789)))
    }

    fun permission(){
        requestAppPermissions(arrayOf(Manifest.permission.CALL_PHONE),
            R.string.runtime_permissions_txt, 101)
    }
}