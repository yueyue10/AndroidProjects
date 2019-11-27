package com.charlie.mpandroidcharttest.presenter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.charlie.mpandroidcharttest.R;
import com.charlie.mpandroidcharttest.bean.RevenuePieBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PieChartLegendAdapter extends BaseQuickAdapter<RevenuePieBean, PieChartLegendAdapter.ViewHolder> {

    public PieChartLegendAdapter(int layoutResId, @Nullable List<RevenuePieBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, RevenuePieBean item) {
        helper.legendView.setBackgroundColor(Color.parseColor(item.getColorStr()));
        helper.legendNameTv.setText(item.getLegendName());
        helper.legendPriceTv.setText(item.getLegendPrice());
        helper.legendPercentTv.setText(item.getLegendPercent());
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.legend_view)
        View legendView;
        @BindView(R.id.legend_name_tv)
        TextView legendNameTv;
        @BindView(R.id.legend_price_tv)
        TextView legendPriceTv;
        @BindView(R.id.legend_percent_tv)
        TextView legendPercentTv;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
