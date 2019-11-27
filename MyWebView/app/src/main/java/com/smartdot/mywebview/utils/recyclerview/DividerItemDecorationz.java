package com.smartdot.mywebview.utils.recyclerview;

import android.content.Context;
import android.graphics.Color;

//绘制网格分割线
public class DividerItemDecorationz extends Y_DividerItemDecoration {

    public DividerItemDecorationz(Context context) {
        super(context);
    }

    @Override
    public Y_Divider getDivider(int itemPosition) {
        //顺序:left, top, right, bottom
        Y_Divider divider = new Y_Divider(false, false, false, false, 10, 20, Color.parseColor("#00000000"));
        switch (itemPosition % 3) {
            case 0:
                //左边的
                divider.setRight(true);
                divider.setBottom(true);
            case 1:
                //中间的
                divider.setRight(true);
                divider.setBottom(true);
                break;
            case 2:
                //右边的
                divider.setRight(true);
                divider.setBottom(true);
                break;
            default:
                break;
        }
        return divider;
    }
}