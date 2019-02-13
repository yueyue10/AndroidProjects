package com.passion.zyj.knowall.utils.string;

import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import com.passion.zyj.knowall.app.MyApp;
import com.passion.zyj.knowall.utils.ScreenUtils;

public class zSpannableStringBuilder extends SpannableStringBuilder {

    public zSpannableStringBuilder(CharSequence text) {
        super(text);
    }

    public zSpannableStringBuilder setTextStyleBold() {
        this.setSpan(new StyleSpan(Typeface.BOLD), 0, this.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public zSpannableStringBuilder setTextSize(int spValue) {
        this.setSpan(new AbsoluteSizeSpan(ScreenUtils.sp2px(MyApp.getInstance().getApplicationContext(), spValue)), 0, this.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public zSpannableStringBuilder setTextStyleNormal() {
        this.setSpan(new StyleSpan(Typeface.NORMAL), 0, this.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public zSpannableStringBuilder setTextColor(@ColorInt int color) {
        this.setSpan(new ForegroundColorSpan(color), 0, this.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return this;
    }
}
