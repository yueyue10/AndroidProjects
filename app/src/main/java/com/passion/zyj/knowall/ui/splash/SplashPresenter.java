package com.passion.zyj.knowall.ui.splash;

import com.passion.zyj.knowall.core.DataManager;
import com.passion.zyj.knowall.mvp.presenter.BasePresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by zhaoyuejun on 2018/11/14.
 */

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    @Inject
    SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void startProcess() {
        long splashTime = 2000;
        addSubscribe(Observable.timer(splashTime, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> mView.jumpToMain()));
    }
}

