package com.passion.zyj.knowall.ui;


import com.passion.zyj.knowall.mvp.presenter.AbstractPresenter;
import com.passion.zyj.knowall.mvp.view.AbstractView;

/**
 * 路线推荐
 *
 * @author quchao
 * @date 2017/11/28
 */

public interface MainContract {

    interface View extends AbstractView {
        /**
         * 获取路线推荐列表数据成功
         */
        //void getRecomdRouteSuccess(List<RecomdRouteBean> recomdRouteBeans);
    }

    interface Presenter extends AbstractPresenter<View> {
        /**
         * 获取路线推荐列表数据
         */
        //void getRecomdRoute(int scenicId, String userId);
    }

}
