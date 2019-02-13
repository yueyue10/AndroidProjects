package com.passion.zyj.knowall.mvp.presenter;



import com.passion.zyj.knowall.core.DataManager;
import com.passion.zyj.knowall.mvp.view.AbstractView;
import com.passion.zyj.knowall.utils.CommonUtils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Base Presenter
 * 管理事件流订阅的生命周期
 *
 * @author quchao
 * @date 2017/11/28
 */

public class BasePresenter<T extends AbstractView> implements AbstractPresenter<T> {

    protected T mView;
    private CompositeDisposable compositeDisposable;
    private DataManager mDataManager;

    public BasePresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void addRxBindingSubscribe(Disposable disposable) {
        addSubscribe(disposable);
    }

    @Override
    public void clearAppData() {
        mDataManager.clearAppData();
    }

    @Override
    public void setCookie(String domain, String cookie) {
        mDataManager.setCookie(domain, cookie);
    }

    @Override
    public String getCookie(String domain) {
        return mDataManager.getCookie(domain);
    }

    @Override
    public boolean getNightModeState() {
        return mDataManager.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean b) {
        mDataManager.setNightModeState(b);
    }

    protected void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

}
