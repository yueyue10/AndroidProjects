package com.passion.zyj.knowall.ui.splash;

import android.Manifest;
import android.content.Intent;
import android.support.v4.content.ContextCompat;

import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.mvp.activity.BaseActivity;
import com.passion.zyj.knowall.ui.main.MainActivity;
import com.passion.zyj.knowall.utils.StatusBarUtil;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * 启动界面
 * Created by zhaoyuejun on 2018/5/15.
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View, EasyPermissions.PermissionCallbacks {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initToolbar() {
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.white), 1f);
        StatusBarUtil.setStatusDarkColor(getWindow());
    }

    @Override
    protected void initEventAndData() {
        if (!this.isTaskRoot()) {
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {
                //finish()之后该活动会继续执行后面的代码，你可以logCat验证，加return避免可能的exception
                finish();
                return;
            }
        }
        String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE};
        if (getPermissions(permissions, 1212, "这个应用需要获取您的位置以此提供更好的服务。"))
            mPresenter.startProcess();
    }

    @Override
    public void jumpToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        mPresenter.startProcess();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        mPresenter.startProcess();
    }
}
