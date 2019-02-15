package com.passion.zyj.knowall.ui.tools.fooddt;

import android.support.v7.widget.LinearLayoutManager;

import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.core.bean.tools.FoodDetailBean;
import com.passion.zyj.knowall.core.bean.tools.StepsBean;
import com.passion.zyj.knowall.mvp.activity.BaseNorActivity;
import com.passion.zyj.knowall.utils.JudgeUtils;

import java.util.ArrayList;

/**
 * 游记详情
 * Created by zhaoyuejun on 2018/11/14.
 */

public class TravelNoteDtAc extends BaseNorActivity {

    TravelNoteDtAdapter travelNoteDtAdapter;
    ArrayList<StepsBean> travelNoteContents;
    ArrayList<String> imgUrls = new ArrayList<>();
    FoodDetailBean foodDetailBean;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_travel_note_detail;
    }

    @Override
    protected void initToolbar() {
        initRecyclerView();
        initListener();
    }

    private void initRecyclerView() {
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
        setTextView(R.id.food_imtro_tv, foodDetailBean.getImtro());
        setTextView(R.id.food_tags_tv, foodDetailBean.getTags());
        setTextView(R.id.food_ingredients_tv, foodDetailBean.getIngredients());
        setTextView(R.id.food_burden_tv, foodDetailBean.getBurden());
        travelNoteContents.addAll(foodDetailBean.getSteps());
        for (StepsBean stepsBean : travelNoteContents) {
            imgUrls.add(stepsBean.getImg());
        }
        travelNoteDtAdapter.notifyDataSetChanged();
    }

}
