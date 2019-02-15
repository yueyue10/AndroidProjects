package com.passion.zyj.knowall.utils;

import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;
import com.passion.zyj.knowall.core.bean.tools.FoodDetailBean;
import com.passion.zyj.knowall.ui.common.ShowPictureAc;
import com.passion.zyj.knowall.ui.tools.fooddt.TravelNoteDtAc;

import java.util.ArrayList;

public class JudgeUtils {

    public static void startTravelNoteDtAc(Context mActivity, FoodDetailBean foodDetailBean) {
        mActivity.startActivity(new Intent(mActivity, TravelNoteDtAc.class).putExtra("foodDetailBean", foodDetailBean));
    }

    public static void startShowPictureAc(Context mActivity, ArrayList<String> urls, int position) {
        try {
            mActivity.startActivity(new Intent(mActivity, ShowPictureAc.class)
                    .putExtra("data", urls)
                    .putExtra("position", position));
        } catch (Exception e) {
            Logger.e(e.toString());
        }
    }
}
