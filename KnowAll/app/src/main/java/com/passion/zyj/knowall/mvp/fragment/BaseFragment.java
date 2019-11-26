package com.passion.zyj.knowall.mvp.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.mvp.presenter.AbstractPresenter;
import com.passion.zyj.knowall.mvp.view.AbstractView;
import com.passion.zyj.knowall.utils.CommonUtils;
import com.passion.zyj.knowall.utils.dialog.ZLoadingDialog;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * MVP模式的Base fragment
 *
 * @author quchao
 * @date 2017/11/28
 */

public abstract class BaseFragment<T extends AbstractPresenter> extends AbstractSimpleFragment implements AbstractView {

    /**
     * 页面标签
     */
    public int pageIndex = 0;
    @Inject
    protected T mPresenter;
    protected ZLoadingDialog dialog;

    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
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
    public void useNightMode(boolean isNightMode) {
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        if (isAdded()) {
            CommonUtils.showMessage(_mActivity, errorMsg);
        }
    }

    @Override
    public void showTokenError() {

    }

    @Override
    public void showNormal() {
        hideLoading();
    }

    @Override
    public void showError() {
        hideLoading();
    }

    @Override
    public void showLoading() {
        dialog = new ZLoadingDialog(_mActivity);
        dialog.setLoadingColor(Color.RED)//颜色
                .setHintText(getString(R.string.loading_hint))
                .setHintTextSize(15) // 设置字体大小 dp
                .setHintTextColor(Color.GRAY)  // 设置字体颜色
                .show();
    }

    public void hideLoading() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void reload() {
    }

    @Override
    public void setTitleBack(String text) {

    }

    @Override
    public void showToast(String message) {
        CommonUtils.showMessage(_mActivity, message);
    }

    public void showToast(int resId) {
        CommonUtils.showMessage(_mActivity, getString(resId));
    }

    //=======================================获取不同状态下的页码数的方法=================================

    public int getLoadMorePageIndex() {
        pageIndex++;
        return (pageIndex - 1) * 10 + 1;
    }

    public int getRefreshPageIndex(ArrayList<?>... lists) {
        for (ArrayList<?> list1 : lists) {
            list1.clear();
        }
        pageIndex = 1;
        return (pageIndex - 1) * 10 + 1;
    }

    //=======================================权限的方法=====================================================
    private boolean hasPermission(String perm) {
        return EasyPermissions.hasPermissions(_mActivity, perm);
    }

    /**
     * 获取权限的方法
     */
    public boolean getPermission(String perm, int requestCode, String explain) {
        if (hasPermission(perm)) {
            return true;
        } else {
            // Ask for permission
            EasyPermissions.requestPermissions(this, explain, requestCode, perm);
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

}
