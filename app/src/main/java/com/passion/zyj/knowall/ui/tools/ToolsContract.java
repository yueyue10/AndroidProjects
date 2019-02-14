package com.passion.zyj.knowall.ui.tools;

import com.passion.zyj.knowall.core.bean.tools.FoodList;
import com.passion.zyj.knowall.core.bean.tools.MenuBean;
import com.passion.zyj.knowall.mvp.presenter.AbstractPresenter;
import com.passion.zyj.knowall.mvp.view.AbstractView;

import java.util.List;

/**
 * @author quchao
 * @date 2017/11/28
 */

public interface ToolsContract {

    interface View extends AbstractView {
        void getFoodCategorySuccess(List<MenuBean> menuBeans);

        void getFoodListSuccess(FoodList foodList);
    }

    interface Presenter extends AbstractPresenter<View> {
        void getFoodCategory(String parentid);

        void getFoodList(String cid, String pn, String rn);
    }

}
