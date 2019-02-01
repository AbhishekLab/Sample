package com.example.sample.login

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

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    lateinit var mBinding: ActivityRegisterBinding

    override fun contentView(): Int = R.layout.activity_register

    override fun onPermissionsGranted(requestCode: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initUI(binding: ActivityRegisterBinding) {
        mBinding = binding

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation()
        }

        mBinding.fab.setOnClickListener {
            animateRevealClose()
        }
    }

    fun animateRevealClose() {
        val mAnimator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewAnimationUtils.createCircularReveal(
                mBinding.cvAdd,
                mBinding.cvAdd.getWidth() / 2,
                0,
                mBinding.cvAdd.getHeight().toFloat(),
                (mBinding.fab.getWidth() / 2).toFloat()
            )
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")

        }
        mAnimator.duration = 500
        mAnimator.interpolator = AccelerateInterpolator()
        mAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                mBinding.cvAdd.setVisibility(View.INVISIBLE)
                super.onAnimationEnd(animation)
                mBinding.fab.setImageResource(R.drawable.ic_signup)
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
                    mBinding.cvAdd.setVisibility(View.GONE)
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
            mBinding.cvAdd,
            mBinding.cvAdd.getWidth() / 2,
            0,
            (mBinding.fab.getWidth() / 2).toFloat(),
            mBinding.cvAdd.getHeight().toFloat()
        )
        mAnimator.duration = 500
        mAnimator.interpolator = AccelerateInterpolator()
        mAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
            }

            override fun onAnimationStart(animation: Animator) {
                mBinding.cvAdd.setVisibility(View.VISIBLE)
                super.onAnimationStart(animation)
            }
        })
        mAnimator.start()
    }
}