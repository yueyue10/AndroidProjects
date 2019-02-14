package com.passion.zyj.knowall.ui.tools;

import com.passion.zyj.knowall.component.BaseObserver;
import com.passion.zyj.knowall.component.RxBus;
import com.passion.zyj.knowall.component.RxUtils;
import com.passion.zyj.knowall.core.DataManager;
import com.passion.zyj.knowall.core.bean.CreateNoteResponse;
import com.passion.zyj.knowall.core.bean.home.WeatherBean;
import com.passion.zyj.knowall.core.bean.tools.MenuBean;
import com.passion.zyj.knowall.mvp.presenter.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by zhaoyuejun on 2018/11/14.
 */

public class ToolsPresenter extends BasePresenter<ToolsContract.View> implements ToolsContract.Presenter {

    private DataManager mDataManager;

    @Inject
    ToolsPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void getFoodCategory(String parentid) {
        addSubscribe(mDataManager.getFoodCategory(null, parentid, "9d7643dff84e96088b3ca7ca6e85d026")
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<MenuBean>>(mView) {
                    @Override
                    public void onNext(List<MenuBean> menuBeans) {
                        mView.getFoodCategorySuccess(menuBeans);
                    }
                }));
    }
}

