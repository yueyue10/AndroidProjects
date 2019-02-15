package com.passion.zyj.knowall.mvp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.mvp.presenter.AbstractPresenter;
import com.passion.zyj.knowall.mvp.view.AbstractView;
import com.passion.zyj.knowall.utils.CommonUtils;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * MVP模式的Base Dialog fragment
 *
 * @author quchao
 * @date 2017/11/28
 */

public abstract class BaseDialogFragment<T extends AbstractPresenter> extends AbstractSimpleDialogFragment implements AbstractView {

    @Inject
    protected T mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogStyle);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }

    private void initDialog() {
        Window window = getDialog().getWindow();
        assert window != null;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.TOP);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroyView();
    }


    @Override
    public void showErrorMsg(String errorMsg) {
        if (getActivity() != null) {
            CommonUtils.showMessage(getActivity(), errorMsg);
        }
    }

    @Override
    public void showTokenError() {

    }

    @Override
    public void useNightMode(boolean isNightMode) {
    }

    @Override
    public void showNormal() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void setTitleBack(String text) {

    }

    @Override
    public void showToast(String message) {
        if (getActivity() == null) {
            return;
        }
        CommonUtils.showMessage(getActivity(), message);
    }

}
