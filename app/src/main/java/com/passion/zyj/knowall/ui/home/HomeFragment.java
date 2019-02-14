package com.passion.zyj.knowall.ui.home;

import com.orhanobut.logger.Logger;
import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.core.bean.home.WeatherBean;
import com.passion.zyj.knowall.mvp.fragment.BaseFragment;
import com.passion.zyj.knowall.utils.image.ImageResUtil;


/**
 * Created by zhaoyuejun on 2018/11/14.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

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
