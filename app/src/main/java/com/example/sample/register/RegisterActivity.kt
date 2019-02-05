package com.example.sample.register

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.support.annotation.RequiresApi
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import com.example.sample.R
import com.example.sample.base.BaseActivity
import com.example.sample.databinding.ActivityRegisterBinding
import com.example.sample.login.LoginViewModel
import javax.inject.Inject

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    lateinit var mBinding: ActivityRegisterBinding

    @Inject
    lateinit var registerViewModel: RegisterViewModel

    override fun contentView(): Int = R.layout.activity_register

    override fun onPermissionsGranted(requestCode: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initUI(binding: ActivityRegisterBinding) {
        mBinding = binding

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation()
        }

        mBinding.fbClose.setOnClickListener {
            animateRevealClose()
        }
        mBinding.btnRegister.setOnClickListener {
            registerViewModel.doRegister(mBinding.edtId.text.toString(),mBinding.edtPass.text.toString(),mBinding.edtRePass.text.toString())
        }
    }

    fun animateRevealClose() {
        val mAnimator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewAnimationUtils.createCircularReveal(
                mBinding.clRoot,
                mBinding.clRoot.getWidth() / 2,
                0,
                mBinding.clRoot.getHeight().toFloat(),
                (mBinding.fbClose.getWidth() / 2).toFloat()
            )
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")

        }
        mAnimator.duration = 500
        mAnimator.interpolator = AccelerateInterpolator()
        mAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                mBinding.clRoot.setVisibility(View.INVISIBLE)
                super.onAnimationEnd(animation)
                mBinding.fbClose.setImageResource(R.drawable.ic_signup)
                super@RegisterActivity.onBackPressed()
            }
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
            }
        })
        mAnimator.start()
    }

    private fun ShowEnterAnimation() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            val transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition)
            window.sharedElementEnterTransition = transition

            transition.addListener(object : Transition.TransitionListener {
                override fun onTransitionStart(transition: Transition) {
                    mBinding.clRoot.setVisibility(View.GONE)
                }

                override fun onTransitionEnd(transition: Transition) {
                    transition.removeListener(this)
                    animateRevealShow()
                }

                override fun onTransitionCancel(transition: Transition) {

                }

                override fun onTransitionPause(transition: Transition) {

                }

                override fun onTransitionResume(transition: Transition) {

                }
            })
        }
    }

    override fun onBackPressed() {
        animateRevealClose()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun animateRevealShow() {
        val mAnimator = ViewAnimationUtils.createCircularReveal(
            mBinding.clRoot,
            mBinding.clRoot.getWidth() / 2,
            0,
            (mBinding.fbClose.getWidth() / 2).toFloat(),
            mBinding.clRoot.getHeight().toFloat()
        )
        mAnimator.duration = 500
        mAnimator.interpolator = AccelerateInterpolator()
        mAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
            }

            override fun onAnimationStart(animation: Animator) {
                mBinding.clRoot.setVisibility(View.VISIBLE)
                super.onAnimationStart(animation)
            }
        })
        mAnimator.start()
    }
}