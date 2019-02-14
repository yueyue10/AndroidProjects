package com.passion.zyj.knowall.ui.main;


import com.passion.zyj.knowall.core.DataManager;
import com.passion.zyj.knowall.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by zhaoyuejun on 2018/11/14.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private DataManager mDataManager;

    @Inject
    MainPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

}

