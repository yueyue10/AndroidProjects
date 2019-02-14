package com.passion.zyj.knowall.ui.home;

import android.support.v7.widget.Toolbar;

import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.mvp.fragment.BaseFragment;
import com.passion.zyj.knowall.utils.ModelUtil;
import com.passion.zyj.knowall.utils.StatusBarUtil;
import com.passion.zyj.knowall.utils.image.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by zhaoyuejun on 2018/11/14.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.play_way_banner)
    Banner play_way_banner;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ArrayList<String> imgUrls;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setPaddingSmart(_mActivity, toolbar);
        initHeaderBanner();
    }

    /**
     * 初始化轮播图
     */
    private void initHeaderBanner() {
        imgUrls = ModelUtil.getPictureUrls();
        play_way_banner.setImageLoader(new GlideImageLoader(_mActivity));
        play_way_banner.update(imgUrls);
        play_way_banner.setOnBannerListener(position -> {
        });
    }

    @Override
    protected void initEventAndData() {

    }

}
