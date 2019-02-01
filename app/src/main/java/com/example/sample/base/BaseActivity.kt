package com.example.sample.base

import android.app.Fragment
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.SparseIntArray
import android.webkit.WebView
import com.google.firebase.FirebaseApp
import dagger.android.AndroidInjection
import dagger.android.DaggerActivity
import android.app.ProgressDialog



abstract class BaseActivity<in T : ViewDataBinding> : DaggerActivity() {

    private lateinit var mBinding: T
    private var mErrorString: SparseIntArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, contentView())
        mErrorString = SparseIntArray()
        initUI(mBinding)
        WebView(this).settings.allowFileAccess = false

    }

    abstract fun initUI(binding: T)
    abstract fun contentView(): Int

    protected open fun fragmentTransaction(
        transactionType: Int, fragment: Fragment,
        container: Int, isAddToBackStack: Boolean
    ) {
        fragmentTransaction(transactionType, fragment, container, isAddToBackStack, null)
    }

    protected open fun fragmentTransaction(
        transactionType: Int, fragment: Fragment,
        container: Int, isAddToBackStack: Boolean, bundle: Bundle?
    ) {
        if (bundle != null)
            fragment.arguments = bundle
        val trans = fragmentManager.beginTransaction()
        when (transactionType) {
            ADD_FRAGMENT -> trans.add(container, fragment, fragment.javaClass.simpleName)
            REPLACE_FRAGMENT -> {
                trans.replace(container, fragment, fragment.javaClass.simpleName)
                if (isAddToBackStack) trans.addToBackStack(null)
            }
        }
        trans.commit()
    }

    companion object {
        const val ADD_FRAGMENT = 0
        const val REPLACE_FRAGMENT = 1
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var permissionCheck = PackageManager.PERMISSION_GRANTED
        for (permission in grantResults) {
            permissionCheck = permissionCheck + permission
        }
        if (grantResults.size > 0 && permissionCheck == PackageManager.PERMISSION_GRANTED) {
            onPermissionsGranted(requestCode)
        } else {
            Snackbar.make(
                findViewById(android.R.id.content), mErrorString!!.get(requestCode),
                Snackbar.LENGTH_INDEFINITE
            ).setAction(
                "ENABLE"
            ) {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.data = Uri.parse("package:$packageName")
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                startActivity(intent)
            }.show()
        }
    }

    fun requestAppPermissions(requestedPermissions: Array<String>, stringId: Int, requestCode: Int) {

        mErrorString!!.put(requestCode, stringId)
        var permissionCheck = PackageManager.PERMISSION_GRANTED
        var shouldShowRequestPermissionRationale = false
        for (permission in requestedPermissions) {
            permissionCheck = permissionCheck + ContextCompat.checkSelfPermission(this, permission)
            shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale) {
                Snackbar.make(
                    findViewById(android.R.id.content), stringId,
                    Snackbar.LENGTH_INDEFINITE
                ).setAction(
                    "GRANT"
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        requestedPermissions,
                        requestCode
                    )
                }.show()
            } else {
                ActivityCompat.requestPermissions(this, requestedPermissions, requestCode)
            }
        } else {
            onPermissionsGranted(requestCode)
        }
    }

    abstract fun onPermissionsGranted(requestCode: Int)

    fun loader(){
        val progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Wait while loading...")
        progress.setCancelable(false) // disable dismiss by tapping outside of the dialog
        progress.show()
// To dismiss the dialog
        progress.dismiss()
    }
}