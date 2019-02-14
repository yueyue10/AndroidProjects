package com.passion.zyj.knowall.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.core.bean.main.MainTabBean;
import com.passion.zyj.knowall.mvp.activity.BaseActivity;
import com.passion.zyj.knowall.ui.home.HomeFragment;
import com.passion.zyj.knowall.ui.tools.ToolsFragment;
import com.passion.zyj.knowall.utils.StatusBarUtil;

import java.util.ArrayList;

import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    private String[] mTitles = {"首页", "工具"};
    private int[] mSelectedIcons = {R.mipmap.icon_tab_home_sel, R.mipmap.icon_tab_tools_sel};
    private int[] mNormalIcons = {R.mipmap.icon_tab_home, R.mipmap.icon_tab_tools};
    private ArrayList<Fragment> mFragments;
    private ArrayList<MainTabBean> mainTabBeans;
    private TabsAdapter tabsAdapter;
    private int mLastFgIndex;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {
        StatusBarUtil.immersive(this);
        initTab();
        initRecyclerView();
    }

    @Override
    protected void initEventAndData() {

    }

    @OnClick({R.id.fab})
    public void onClick(View view) {
        showToast("提出建议");
    }

    private void initTab() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new ToolsFragment());
        switchFragment(0);
    }

    private void initRecyclerView() {
        mainTabBeans = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            mainTabBeans.add(new MainTabBean(mTitles[i], mNormalIcons[i], mSelectedIcons[i]));
        }
        mainTabBeans.get(0).setZyj_status("selected");
        tabsAdapter = new TabsAdapter(R.layout.item_home_tab, mainTabBeans);
        tabsAdapter.setOnItemClickListener((adapter, view, position) -> selectItem(position));
        initRecyclerView(R.id.mainTabBar, tabsAdapter, new GridLayoutManager(mActivity, mainTabBeans.size()));
    }

    /**
     * 选中一项的逻辑
     *
     * @param position
     */
    public void selectItem(int position) {
        for (int i = 0; i < mainTabBeans.size(); i++) {
            mainTabBeans.get(i).setZyj_status("unselected");
        }
        mainTabBeans.get(position).setZyj_status("selected");
        tabsAdapter.notifyDataSetChanged();
        switchFragment(position);
    }

    /**
     * 切换fragment
     *
     * @param position 要显示的fragment的下标
     */
    private void switchFragment(int position) {
        if (position >= mFragments.size()) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = mFragments.get(position);
        Fragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if (!targetFg.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(targetFg).commit();
            ft.add(R.id.normal_view, targetFg);
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }
}
