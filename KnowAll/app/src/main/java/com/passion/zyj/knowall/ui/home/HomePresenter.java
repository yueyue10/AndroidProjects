package com.passion.zyj.knowall.ui.home;

import com.passion.zyj.knowall.component.BaseObserver;
import com.passion.zyj.knowall.component.RxBus;
import com.passion.zyj.knowall.component.RxUtils;
import com.passion.zyj.knowall.core.DataManager;
import com.passion.zyj.knowall.core.bean.CreateNoteResponse;
import com.passion.zyj.knowall.core.bean.home.WeatherBean;
import com.passion.zyj.knowall.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by zhaoyuejun on 2018/11/14.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    private DataManager mDataManager;

    @Inject
    HomePresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(HomeContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(CreateNoteResponse.class)
                .filter(scenicArea -> scenicArea.getId() != 0)
                .subscribe(scenicArea -> refreshScenicInfo(scenicArea)));
    }

    private void refreshScenicInfo(CreateNoteResponse scenicArea) {

    }

    @Override
    public void getWeather(String cityname) {
        addSubscribe(mDataManager.getWeather(cityname, "5e4718f626b25b88617f5501a4622983")
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<WeatherBean>(mView) {
                    @Override
                    public void onNext(WeatherBean playWayBootStraps) {
                        mView.getWeatherSuccess(playWayBootStraps);
                    }
                }));
    }

}

