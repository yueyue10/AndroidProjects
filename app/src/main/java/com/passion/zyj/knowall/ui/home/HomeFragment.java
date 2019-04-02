package com.passion.zyj.knowall.ui.home;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.core.bean.home.WeatherBean;
import com.passion.zyj.knowall.mvp.fragment.BaseFragment;
import com.passion.zyj.knowall.ui.common.LineActivity;
import com.passion.zyj.knowall.utils.image.ImageResUtil;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by zhaoyuejun on 2018/11/14.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.normal_view)
    CardView normal_view;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.normal_view)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.normal_view:
                startActivity(new Intent(_mActivity, LineActivity.class));
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getWeather("北京");
    }

    @Override
    public void getWeatherSuccess(WeatherBean weatherbean) {
        Logger.d(weatherbean.toString());
        setImageView(R.id.weather_iv1, ImageResUtil.getImgRes(weatherbean.getToday().getWeather_id().getFa()));
        setImageView(R.id.weather_iv2, ImageResUtil.getImgRes(weatherbean.getToday().getWeather_id().getFb()));
        setTextView(R.id.time_tv, weatherbean.getSk().getTime());
        setTextView(R.id.date_tv, weatherbean.getToday().getDate_y());
        setTextView(R.id.temperature_tv, weatherbean.getToday().getTemperature());
        setTextView(R.id.weather_tv, weatherbean.getToday().getWeather());
    }
}
