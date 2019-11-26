package com.passion.zyj.knowall.ui.tools;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.core.bean.tools.FoodBean;
import com.passion.zyj.knowall.core.bean.tools.FoodDetailBean;
import com.passion.zyj.knowall.core.bean.tools.FoodList;
import com.passion.zyj.knowall.core.bean.tools.MenuBean;
import com.passion.zyj.knowall.mvp.fragment.BaseFragment;
import com.passion.zyj.knowall.utils.JudgeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyuejun on 2018/11/14.
 */

public class ToolsFragment extends BaseFragment<ToolsPresenter> implements ToolsContract.View {

    MenuTypeAdapter menuTypeAdapter;
    List<MenuBean> menuBeans;
    FoodTypeAdapter foodTypeAdapter;
    List<FoodBean> foodBeans;
    FoodListAdapter foodListAdapter;
    List<FoodDetailBean> foodDetailBeans;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tools;
    }

    @Override
    protected void initView() {
        initMenuRecyclerView();
        initFoodTypeRecyclerView();
        initFoodListRecyclerView();
        initListener();
    }


    private void initMenuRecyclerView() {
        menuBeans = new ArrayList<>();
        menuTypeAdapter = new MenuTypeAdapter(R.layout.item_menu_type, menuBeans);
        initRecyclerView(R.id.menu_type_rv, menuTypeAdapter, new LinearLayoutManager(_mActivity));
    }

    private void initFoodTypeRecyclerView() {
        foodBeans = new ArrayList<>();
        foodTypeAdapter = new FoodTypeAdapter(R.layout.item_menu_type, foodBeans);
        initRecyclerView(R.id.food_type_rv, foodTypeAdapter, new LinearLayoutManager(_mActivity));
    }

    private void initFoodListRecyclerView() {
        foodDetailBeans = new ArrayList<>();
        foodListAdapter = new FoodListAdapter(R.layout.item_food_list, foodDetailBeans);
        initRecyclerView(R.id.food_list_rv, foodListAdapter, new LinearLayoutManager(_mActivity))
                .addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
    }

    private void initListener() {
        menuTypeAdapter.setOnItemClickListener((adapter, view, position) -> {
            for (int i = 0; i < menuBeans.size(); i++) {
                menuBeans.get(i).setZyj_status("unselected");
            }
            menuBeans.get(position).setZyj_status("selected");
            menuTypeAdapter.notifyDataSetChanged();
            //右边列表刷新
            foodBeans.clear();
            foodBeans.addAll(menuBeans.get(position).getList());
            foodBeans.get(0).setZyj_status("selected");
            foodTypeAdapter.notifyDataSetChanged();
            foodDetailBeans.clear();
            mPresenter.getFoodList(foodBeans.get(0).getId(), null, null);
        });
        foodTypeAdapter.setOnItemClickListener((adapter, view, position) -> {
            for (int i = 0; i < foodBeans.size(); i++) {
                foodBeans.get(i).setZyj_status("unselected");
            }
            foodBeans.get(position).setZyj_status("selected");
            foodTypeAdapter.notifyDataSetChanged();
            foodDetailBeans.clear();
            mPresenter.getFoodList(foodBeans.get(position).getId(), null, null);
        });
        foodListAdapter.setOnItemClickListener((adapter, view, position) -> {
            JudgeUtils.startTravelNoteDtAc(_mActivity, foodDetailBeans.get(position));
        });
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getFoodCategory(null);
    }

    @Override
    public void getFoodCategorySuccess(List<MenuBean> menuBeans) {
        if (menuBeans.size() <= 0)
            return;
        this.menuBeans.addAll(menuBeans);
        this.menuBeans.get(0).setZyj_status("selected");
        menuTypeAdapter.notifyDataSetChanged();
        foodBeans.addAll(menuBeans.get(0).getList());
        foodBeans.get(0).setZyj_status("selected");
        foodTypeAdapter.notifyDataSetChanged();
        mPresenter.getFoodList(foodBeans.get(0).getId(), null, null);
    }

    @Override
    public void getFoodListSuccess(FoodList foodList) {
        foodDetailBeans.addAll(foodList.getData());
        foodListAdapter.notifyDataSetChanged();
    }
}
