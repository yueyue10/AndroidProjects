package com.passion.zyj.knowall.ui.tools.fooddt;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.core.bean.tools.FoodDetailBean;
import com.passion.zyj.knowall.core.bean.tools.StepsBean;
import com.passion.zyj.knowall.mvp.activity.BaseNorActivity;
import com.passion.zyj.knowall.utils.CommonUtils;
import com.passion.zyj.knowall.utils.JudgeUtils;
import com.passion.zyj.knowall.utils.image.GlideRoundTransform;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 游记详情
 * Created by zhaoyuejun on 2018/11/14.
 */

public class TravelNoteDtAc extends BaseNorActivity {

    @BindView(R.id.food_iv)
    ImageView food_iv;
    TravelNoteDtAdapter travelNoteDtAdapter;
    ArrayList<StepsBean> travelNoteContents;
    MaterialsAdapter materialsAdapter;
    ArrayList<String> materialStrs;
    MaterialsAdapter burdensAdapter;
    ArrayList<String> burdenStrs;
    ArrayList<String> imgUrls = new ArrayList<>();
    FoodDetailBean foodDetailBean;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_travel_note_detail;
    }

    @Override
    protected void initToolbar() {
        initRecyclerView1();
        initRecyclerView2();
        initRecyclerView3();
        initListener();
    }

    private void initRecyclerView1() {
        materialStrs = new ArrayList<>();
        materialsAdapter = new MaterialsAdapter(R.layout.item_text_view, materialStrs);
        initRecyclerView(R.id.material_of_food_rv, materialsAdapter, new LinearLayoutManager(mActivity));
    }

    private void initRecyclerView2() {
        burdenStrs = new ArrayList<>();
        burdensAdapter = new MaterialsAdapter(R.layout.item_text_view, burdenStrs);
        initRecyclerView(R.id.burden_of_food_rv, burdensAdapter, new LinearLayoutManager(mActivity));
    }

    private void initRecyclerView3() {
        travelNoteContents = new ArrayList<>();
        travelNoteDtAdapter = new TravelNoteDtAdapter(R.layout.item_travel_note_detail, travelNoteContents);
        initRecyclerView(R.id.stepsRecyclerView, travelNoteDtAdapter, new LinearLayoutManager(mActivity));
    }

    private void initListener() {
        travelNoteDtAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.travel_note_iv)
                JudgeUtils.startShowPictureAc(mActivity, imgUrls, position);
        });
    }

    @Override
    protected void initEventAndData() {
        foodDetailBean = (FoodDetailBean) getIntent().getSerializableExtra("foodDetailBean");
        setTitleBack(foodDetailBean.getTitle());
        Glide.with(mActivity).load(foodDetailBean.getAlbums().size() > 0 ? foodDetailBean.getAlbums().get(0) : "").apply(new RequestOptions()
                .placeholder(R.mipmap.icon_no_data)
                .transform(new GlideRoundTransform(mActivity, 4))
                .error(R.mipmap.icon_no_data))
                .into(food_iv);
        setTextView(R.id.food_imtro_tv, foodDetailBean.getImtro());
        //标签
        setTextView(R.id.food_tags_tv, foodDetailBean.getTags().replace(";", " "));
        //食材
        materialStrs.addAll(CommonUtils.stringToList(foodDetailBean.getIngredients(), ";"));
        materialsAdapter.notifyDataSetChanged();
        //调料
        burdenStrs.addAll(CommonUtils.stringToList(foodDetailBean.getBurden(), ";"));
        burdensAdapter.notifyDataSetChanged();
        //步骤
        travelNoteContents.addAll(foodDetailBean.getSteps());
        for (StepsBean stepsBean : travelNoteContents) {
            imgUrls.add(stepsBean.getImg());
        }
        travelNoteDtAdapter.notifyDataSetChanged();
    }

}
