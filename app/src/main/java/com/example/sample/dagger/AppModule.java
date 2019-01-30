package com.example.sample.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.sample.dagger.scopes.ApplicationContext;
import com.example.sample.global.App;
import com.example.sample.global.Constants;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
abstract class AppModule {

    @Singleton
    @Provides
    static SharedPreferences getSharedPreference(@ApplicationContext Context context) {
        return context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
    }

    @ApplicationContext
    @Binds
    abstract Context provideApplicationContext(App application);
}