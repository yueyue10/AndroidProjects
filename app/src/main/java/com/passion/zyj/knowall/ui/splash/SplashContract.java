package com.passion.zyj.knowall.ui.splash;

import com.passion.zyj.knowall.mvp.presenter.AbstractPresenter;
import com.passion.zyj.knowall.mvp.view.AbstractView;

/**
 * @author quchao
 * @date 2017/11/28
 */

public interface SplashContract {

    interface View extends AbstractView {

        void jumpToMain();
    }

    interface Presenter extends AbstractPresenter<View> {
        void startProcess();
    }

}
