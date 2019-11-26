package com.passion.zyj.knowall.di.module;

import com.passion.zyj.knowall.di.scope.FragmentScoped;
import com.passion.zyj.knowall.ui.home.HomeFragment;
import com.passion.zyj.knowall.ui.home.HomeModule;
import com.passion.zyj.knowall.ui.tools.ToolsFragment;
import com.passion.zyj.knowall.ui.tools.ToolsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = ToolsModule.class)
    abstract ToolsFragment ToolsFragment();

    @FragmentScoped
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeFragment HomeFragment();
}