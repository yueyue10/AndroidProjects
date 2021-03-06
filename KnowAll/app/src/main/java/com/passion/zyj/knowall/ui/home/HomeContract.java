package com.passion.zyj.knowall.ui.home;

import com.passion.zyj.knowall.core.bean.home.WeatherBean;
import com.passion.zyj.knowall.mvp.presenter.AbstractPresenter;
import com.passion.zyj.knowall.mvp.view.AbstractView;

/**
 * @author quchao
 * @date 2017/11/28
 */

public interface HomeContract {

    interface View extends AbstractView {
        void getWeatherSuccess(WeatherBean weatherbean);
    }

    interface Presenter extends AbstractPresenter<View> {
        void getWeather(String cityname);
    }

}
