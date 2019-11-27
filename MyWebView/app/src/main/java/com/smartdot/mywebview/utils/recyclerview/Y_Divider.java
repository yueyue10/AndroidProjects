package com.smartdot.mywebview.utils.recyclerview;

import android.support.annotation.ColorInt;

/**
 * Created by mac on 2017/5/17.
 */
//网格分割线基类
public class Y_Divider {

    public boolean left = false;
    public boolean top = false;
    public boolean right = false;
    public boolean bottom = false;
    /**
     * A single color value in the form 0xAARRGGBB.
     **/
    public int colorARGB;
    /**
     * 单位dp
     */
    public float xlineWidthDp;
    public float ylineWidthDp;

    public Y_Divider(boolean left, boolean top, boolean right, boolean bottom, float lineWidthDp, @ColorInt int colorARGB) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.colorARGB = colorARGB;
        this.xlineWidthDp = lineWidthDp;
        this.ylineWidthDp = lineWidthDp;
    }
    public Y_Divider(boolean left, boolean top, boolean right, boolean bottom, float xlineWidthDp,float ylineWidthDp, @ColorInt int colorARGB) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.colorARGB = colorARGB;
        this.xlineWidthDp = xlineWidthDp;
        this.ylineWidthDp = ylineWidthDp;
    }

    public boolean getLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean getTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public boolean getRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean getBottom() {
        return bottom;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    public int getColorARGB() {
        return colorARGB;
    }

    public void setColorARGB(@ColorInt int colorARGB) {
        this.colorARGB = colorARGB;
    }

    public float getXlineWidthDp() {
        return xlineWidthDp;
    }

    public void setXlineWidthDp(float xlineWidthDp) {
        this.xlineWidthDp = xlineWidthDp;
    }

    public float getYlineWidthDp() {
        return ylineWidthDp;
    }

    public void setYlineWidthDp(float ylineWidthDp) {
        this.ylineWidthDp = ylineWidthDp;
    }
}