package com.passion.zyj.knowall.di.module;


import com.passion.zyj.knowall.core.DataManager;
import com.passion.zyj.knowall.core.http.api.GeeksApis;
import com.passion.zyj.knowall.core.prefs.PreferenceHelper;
import com.passion.zyj.knowall.core.prefs.PreferenceHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author quchao
 * @date 2017/11/27
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    PreferenceHelper providePreferencesHelper(PreferenceHelperImpl implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(GeeksApis httpHelper, PreferenceHelper preferencesHelper) {
        return new DataManager(httpHelper, preferencesHelper);
    }

}
