package com.example.sample.dagger

import com.example.sample.dagger.scopes.ActivityScope
import com.example.sample.Home.HomeActivity
import com.example.sample.dashboard.DashboardActivity
import com.example.sample.login.LoginActivity
import com.example.sample.login.SplashActivity
import com.example.sample.register.RegisterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun homeActivity(): HomeActivity

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun loginActivity(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun registerActivity(): RegisterActivity

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun splashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun dashboardActivity(): DashboardActivity
}
