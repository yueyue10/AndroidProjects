package com.passion.zyj.knowall.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * Common simple fragment
 *
 * @author quchao
 * @date 2017/11/28
 */

public abstract class AbstractSimpleFragment extends SupportFragment {

    private View mView;
    private Unbinder unBinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        unBinder = ButterKnife.bind(this, mView);
        initView();
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unBinder != null && unBinder != Unbinder.EMPTY) {
            unBinder.unbind();
            unBinder = null;
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initEventAndData();
    }

    /**
     * 有些初始化必须在onCreateView中，例如setAdapter,
     * 否则，会弹出 No adapter attached; skipping layout
     */
    protected void initView() {

    }

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initEventAndData();

    public RecyclerView initRecyclerView(int resId, RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView = mView.findViewById(resId);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        return recyclerView;
    }

    public void setTextView(int resId, String text) {
        TextView textView = mView.findViewById(resId);
        textView.setText(text);
    }
}
