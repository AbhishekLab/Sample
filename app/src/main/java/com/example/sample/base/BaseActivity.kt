package com.example.sample.base

import android.app.Fragment
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.webkit.WebView
import com.example.sample.permission.RuntimePermissionsActivity
import dagger.android.AndroidInjection
import dagger.android.DaggerActivity

abstract class BaseActivity<in T : ViewDataBinding> : DaggerActivity() {

    private lateinit var mBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, contentView())
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
}