package com.passion.zyj.knowall.ui.tools.fooddt;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.core.bean.tools.StepsBean;
import com.passion.zyj.knowall.utils.image.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 游记详情列表适配器
 *
 * @author quchao
 * @date 2018/2/24
 */
public class TravelNoteDtAdapter extends BaseQuickAdapter<StepsBean, TravelNoteDtAdapter.TravelNoteDtViewHolder> {

    public TravelNoteDtAdapter(int layoutResId, @Nullable List<StepsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(TravelNoteDtViewHolder helper, StepsBean item) {
        helper.travel_note_content_tv.setText(item.getStep());
        Glide.with(mContext).load(item.getImg())
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.icon_no_data)
                        .transform(new GlideRoundTransform(mContext, 5))
                        .error(R.mipmap.icon_no_data))
                .into(helper.travel_note_iv);
        helper.addOnClickListener(R.id.travel_note_iv);
    }

    class TravelNoteDtViewHolder extends BaseViewHolder {

        @BindView(R.id.space_view)
        Space space_view;
        @BindView(R.id.travel_note_title_tv)
        TextView travel_note_title_tv;
        @BindView(R.id.travel_note_content_tv)
        TextView travel_note_content_tv;
        @BindView(R.id.travel_note_iv)
        ImageView travel_note_iv;

        public TravelNoteDtViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
