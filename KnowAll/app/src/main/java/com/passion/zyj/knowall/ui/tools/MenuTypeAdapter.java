package com.passion.zyj.knowall.ui.tools;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.core.bean.tools.MenuBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author quchao
 * @date 2018/2/24
 */
public class MenuTypeAdapter extends BaseQuickAdapter<MenuBean, MenuTypeAdapter.ViewHolder> {

    public MenuTypeAdapter(int layoutResId, @Nullable List<MenuBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, MenuBean item) {
        switch (item.getZyj_status()) {
            case "selected":
                helper.scenic_name_tv.setSelected(true);
                helper.scenic_name_tv.setTextColor(Color.parseColor("#FF333335"));
                helper.scenic_name_tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                helper.scenic_layout.setBackgroundColor(Color.parseColor("#FFF6F7F9"));
                helper.select_line.setVisibility(View.VISIBLE);
                break;
            case "unselected":
            default:
                helper.scenic_name_tv.setSelected(false);
                helper.scenic_name_tv.setTextColor(Color.parseColor("#FF5C5C5E"));
                helper.scenic_name_tv.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                helper.scenic_layout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                helper.select_line.setVisibility(View.INVISIBLE);
                break;
        }
        helper.scenic_name_tv.setText(item.getName());
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.scenic_layout)
        LinearLayout scenic_layout;
        @BindView(R.id.select_line)
        View select_line;
        @BindView(R.id.scenic_name_tv)
        TextView scenic_name_tv;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
