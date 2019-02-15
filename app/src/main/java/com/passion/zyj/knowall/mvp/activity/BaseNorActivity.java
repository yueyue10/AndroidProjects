package com.passion.zyj.knowall.mvp.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.mvp.view.AbstractView;
import com.passion.zyj.knowall.utils.CommonUtils;
import com.passion.zyj.knowall.utils.ScreenUtils;
import com.passion.zyj.knowall.utils.dialog.ZLoadingDialog;

import java.util.ArrayList;

import pub.devrel.easypermissions.EasyPermissions;

import static com.passion.zyj.knowall.mvp.activity.BaseActivity.PADDING_ALL;
import static com.passion.zyj.knowall.mvp.activity.BaseActivity.PADDING_LR;
import static com.passion.zyj.knowall.mvp.activity.BaseActivity.PADDING_TB;

public abstract class BaseNorActivity extends AbstractSimpleActivity implements AbstractView {

    /**
     * 页面标签
     */
    public int pageIndex = 0;
    /**
     * 页数
     */
    public int pageNum = 0;
    protected ZLoadingDialog dialog;

    @Override
    protected void onViewCreated() {

    }

    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        CommonUtils.showMessage(this, errorMsg);
    }

    @Override
    public void showNormal() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void showError() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void showTokenError() {

    }

    @Override
    public void showLoading() {
        dialog = new ZLoadingDialog(mActivity);
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
        Toolbar toolbar = findViewById(R.id.common_toolbar);
        toolbar.setTitle(text);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    public void setTextView(int resId, String s) {
        TextView textView = findViewById(resId);
        textView.setText(s);
    }

    /**
     * 给view设置内边距
     *
     * @param resId         控件id
     * @param dp_padding    内边距值
     * @param padding_style 内边距类型
     */
    public void setViewPadding(int resId, int dp_padding, int padding_style) {
        int marginPx = ScreenUtils.dip2px(mActivity, dp_padding);
        switch (padding_style) {
            case PADDING_LR:
                findViewById(resId).setPadding(marginPx, 0, marginPx, 0);
                break;
            case PADDING_TB:
                findViewById(resId).setPadding(0, marginPx, 0, marginPx);
                break;
            case PADDING_ALL:
                findViewById(resId).setPadding(marginPx, marginPx, marginPx, marginPx);
                break;
        }
    }

    public RecyclerView initRecyclerView(int resId, RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView = findViewById(resId);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        return recyclerView;
    }

    @Override
    public void showToast(String message) {
        CommonUtils.showMessage(mActivity, message);
    }

    public void showToast(int resId) {
        CommonUtils.showMessage(mActivity, getString(resId));
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
    //=======================================获取不同状态下的页码数的方法=================================

    public int getLoadMorePageNum() {
        pageNum++;
        return pageNum;
    }

    public int getRefreshPageNum(ArrayList<?>... lists) {
        for (ArrayList<?> list1 : lists) {
            list1.clear();
        }
        pageNum = 1;
        return pageNum;
    }

    //=======================================权限的方法=====================================================
    private boolean hasPermission(String perm) {
        return EasyPermissions.hasPermissions(mActivity, perm);
    }

    private boolean hasPermissions(String[] perms) {
        return EasyPermissions.hasPermissions(this, perms);
    }

    /**
     * 获取权限的方法
     */
    public boolean getPermission(String perm, int requestCode, String explain) {
        if (hasPermission(perm)) {
            return true;
        } else {
            EasyPermissions.requestPermissions(this, explain, requestCode, perm);
            return false;
        }
    }

    /**
     * 批量获取权限的方法
     */
    public boolean getPermissions(String[] perms, int requestCode, String explain) {
        if (hasPermissions(perms)) {
            return true;
        } else {
            EasyPermissions.requestPermissions(this, explain, requestCode, perms);
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
