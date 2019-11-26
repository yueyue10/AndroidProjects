package com.passion.zyj.knowall.ui.tools;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.core.bean.tools.FoodDetailBean;
import com.passion.zyj.knowall.utils.CommonUtils;
import com.passion.zyj.knowall.utils.image.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 景点信息列表适配器
 *
 * @author quchao
 * @date 2018/2/24
 */
public class FoodListAdapter extends BaseQuickAdapter<FoodDetailBean, FoodListAdapter.ViewHolder> {

    public FoodListAdapter(int layoutResId, @Nullable List<FoodDetailBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, FoodDetailBean item) {
        Glide.with(mContext).load(item.getAlbums().size() > 0 ? item.getAlbums().get(0) : "")
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.icon_no_data)
                        .transform(new GlideRoundTransform(mContext, 4))
                        .error(R.mipmap.icon_no_data))
                .into(helper.food_iv);
        helper.food_name_tv.setText(item.getTitle());
        helper.food_content_tv.setText(item.getImtro());
        helper.food_tags_recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        helper.food_tags_recyclerView.setAdapter(new FoodTagAdapter(R.layout.item_spots_list_tag, CommonUtils.stringToList(item.getTags(), ";")));
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.food_iv)
        ImageView food_iv;
        @BindView(R.id.food_name_tv)
        TextView food_name_tv;
        @BindView(R.id.food_content_tv)
        TextView food_content_tv;
        @BindView(R.id.food_tags_recyclerView)
        RecyclerView food_tags_recyclerView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
