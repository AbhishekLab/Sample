package com.example.sample.global

import android.app.Activity
import android.app.Application
import android.app.Service
import android.support.v4.app.Fragment
import com.example.sample.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import dagger.android.support.HasSupportFragmentInjector
import java.util.*
import javax.inject.Inject

class App : Application(), HasActivityInjector, HasServiceInjector, HasSupportFragmentInjector {

    @Inject
    internal lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    internal lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()

        initDagger()
        Locale.setDefault(Locale("en", "IN"))
    }

    //todo remove DaggerAppComponent compile time dependency not found
    private fun initDagger() {
        DaggerAppComponent.builder()
                .application(this)
                .build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return dispatchingActivityInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return dispatchingServiceInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }
}