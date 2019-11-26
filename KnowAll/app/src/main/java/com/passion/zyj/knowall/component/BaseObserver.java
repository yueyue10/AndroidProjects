package com.passion.zyj.knowall.component;

import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;
import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.app.MyApp;
import com.passion.zyj.knowall.core.http.exception.ServiceException;
import com.passion.zyj.knowall.mvp.view.AbstractView;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 * @param <T>
 * @author quchao
 * @date 2017/11/27
 */

public abstract class BaseObserver<T> extends ResourceObserver<T> {

    private AbstractView mView;
    private String mErrorMsg;
    private boolean isShowError = true;

    protected BaseObserver(AbstractView view) {
        this.mView = view;
    }

    protected BaseObserver(AbstractView view, String errorMsg) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseObserver(AbstractView view, boolean isShowError) {
        this.mView = view;
        this.isShowError = isShowError;
    }

    protected BaseObserver(AbstractView view, String errorMsg, boolean isShowError) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        mView.showError();
        if (!isShowError)
            return;
        Logger.e(e.toString());
        if (!TextUtils.isEmpty(mErrorMsg)) {
            mView.showErrorMsg(mErrorMsg);
        } else if (e instanceof HttpException) {
            if (((HttpException) e).code() == 500) {
                mView.showTokenError();
            } else {
                mView.showErrorMsg(MyApp.getInstance().getString(R.string.http_error));
            }
        } else if (e instanceof ServiceException) {
            mView.showErrorMsg(e.getMessage());
        } else if (e instanceof JsonSyntaxException) {
            mView.showErrorMsg(MyApp.getInstance().getString(R.string.json_exception));
        } else {
            mView.showErrorMsg(MyApp.getInstance().getString(R.string.http_error));
        }
    }
}
