package com.passion.zyj.knowall.mvp.contract;


import com.passion.zyj.knowall.mvp.presenter.AbstractPresenter;
import com.passion.zyj.knowall.mvp.view.AbstractView;

/**
 * @author quchao
 * @date 2017/11/28
 */

public interface AbstractContract {

    interface View extends AbstractView {
        /**
         * 获取验证码成功
         */

        /**
         * 登录成功
         */
        void toLoginSuccess();
    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * 获取验证码
         */
        void getYzm(String phoneNum);

        /**
         * 获取登录信息
         */
        void toLogin(String phoneNum, String yzm);
    }

}
