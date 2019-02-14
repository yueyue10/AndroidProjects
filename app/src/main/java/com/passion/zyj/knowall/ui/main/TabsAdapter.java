package com.passion.zyj.knowall.ui.main;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.core.bean.main.MainTabBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author quchao
 * @date 2018/2/24
 */
public class TabsAdapter extends BaseQuickAdapter<MainTabBean, TabsAdapter.TabsViewHolder> {

    public TabsAdapter(int layoutResId, @Nullable List<MainTabBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(TabsViewHolder helper, MainTabBean item) {
        switch (item.getZyj_status()) {
            case "selected":
                helper.tab_tv.setTextColor(ContextCompat.getColor(mContext, R.color.bottom_tab_tv_color_yes));
                helper.tab_iv.setImageDrawable(mContext.getResources().getDrawable(item.getSelectedIcon()));
                break;
            case "unselected":
                helper.tab_tv.setTextColor(ContextCompat.getColor(mContext, R.color.bottom_tab_tv_color_no));
                helper.tab_iv.setImageDrawable(mContext.getResources().getDrawable(item.getNormalIcon()));
                break;
        }
        helper.tab_tv.setText(item.getName());
    }

    class TabsViewHolder extends BaseViewHolder {

        @BindView(R.id.tab_iv)
        ImageView tab_iv;
        @BindView(R.id.tab_tv)
        TextView tab_tv;

        public TabsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
