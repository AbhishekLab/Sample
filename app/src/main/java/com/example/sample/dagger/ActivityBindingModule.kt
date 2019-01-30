package com.example.sample.dagger

import com.example.sample.dagger.scopes.ActivityScope
import com.example.sample.Home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun homeActivity(): HomeActivity

}
