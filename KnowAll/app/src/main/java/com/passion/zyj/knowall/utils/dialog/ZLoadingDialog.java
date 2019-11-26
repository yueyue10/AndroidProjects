package com.passion.zyj.knowall.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.passion.zyj.knowall.R;

import java.lang.ref.WeakReference;

/**
 * Created by zyao89 on 2017/4/9.
 * For more projects: https://github.com/zyao89
 */
public class ZLoadingDialog {

    private final WeakReference<Context> mContext;
    private final int mThemeResId;
    private int mLoadingBuilderColor;
    private String mHintText;
    private float mHintTextSize = -1;
    private int mHintTextColor = -1;
    private boolean mCancelable = true;
    private boolean mCanceledOnTouchOutside = true;
    private int mDialogBackgroundColor = -1;
    private Dialog mZLoadingDialog;
    LottieAnimationView zLoadingView;

    public ZLoadingDialog(@NonNull Context context) {
        this(context, R.style.alert_dialog);
    }

    public ZLoadingDialog(@NonNull Context context, int themeResId) {
        this.mContext = new WeakReference<>(context);
        this.mThemeResId = themeResId;
    }

    public ZLoadingDialog setLoadingColor(@ColorInt int color) {
        this.mLoadingBuilderColor = color;
        return this;
    }

    public ZLoadingDialog setHintText(String text) {
        this.mHintText = text;
        return this;
    }

    public ZLoadingDialog setHintTextSize(float size) {
        this.mHintTextSize = size;
        return this;
    }

    public ZLoadingDialog setHintTextColor(@ColorInt int color) {
        this.mHintTextColor = color;
        return this;
    }

    public ZLoadingDialog setCancelable(boolean cancelable) {
        mCancelable = cancelable;
        return this;
    }

    public ZLoadingDialog setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mCanceledOnTouchOutside = canceledOnTouchOutside;
        return this;
    }

    public ZLoadingDialog setDialogBackgroundColor(@ColorInt int color) {
        this.mDialogBackgroundColor = color;
        return this;
    }

    @NonNull
    private View createContentView() {
        if (isContextNotExist()) {
            throw new RuntimeException("Context is null...");
        }
        return View.inflate(this.mContext.get(), R.layout.z_loading_dialog, null);
    }

    public Dialog create() {
        if (isContextNotExist()) {
            throw new RuntimeException("Context is null...");
        }
        if (mZLoadingDialog != null) {
            cancel();
        }
        mZLoadingDialog = new Dialog(this.mContext.get(), this.mThemeResId);
        View contentView = createContentView();
        LinearLayout zLoadingRootView = contentView.findViewById(R.id.z_loading);

        // init color
        if (this.mDialogBackgroundColor != -1) {
            final Drawable drawable = zLoadingRootView.getBackground();
            if (drawable != null) {
                drawable.setAlpha(Color.alpha(this.mDialogBackgroundColor));
                drawable.setColorFilter(this.mDialogBackgroundColor, PorterDuff.Mode.SRC_ATOP);
            }
        }

        zLoadingView = contentView.findViewById(R.id.loading_animation);
        TextView zTextView = contentView.findViewById(R.id.z_text_view);
        zLoadingView.setAnimation("loading_data.json");
        zLoadingView.loop(true);
        zLoadingView.playAnimation();

        if (this.mHintTextSize > 0 && !TextUtils.isEmpty(mHintText)) {
            zTextView.setVisibility(View.VISIBLE);
            zTextView.setText(mHintText);
            zTextView.setTextSize(this.mHintTextSize);
            zTextView.setTextColor(this.mHintTextColor == -1 ? this.mLoadingBuilderColor : this.mHintTextColor);
        } else if (!TextUtils.isEmpty(mHintText)) {
            zTextView.setVisibility(View.VISIBLE);
            zTextView.setText(mHintText);
        }
        mZLoadingDialog.setContentView(contentView);
        mZLoadingDialog.setCancelable(this.mCancelable);
        mZLoadingDialog.setCanceledOnTouchOutside(this.mCanceledOnTouchOutside);
        return mZLoadingDialog;
    }

    public void show() {
        if (mZLoadingDialog != null) {
            mZLoadingDialog.show();
        } else {
            final Dialog zLoadingDialog = create();
            zLoadingDialog.show();
        }
    }

    public void cancel() {
        if (mZLoadingDialog != null) {
            mZLoadingDialog.cancel();
        }
        if (zLoadingView != null)
            zLoadingView.cancelAnimation();
        mZLoadingDialog = null;
    }

    public void dismiss() {
        if (mZLoadingDialog != null) {
            mZLoadingDialog.dismiss();
        }
        if (zLoadingView != null)
            zLoadingView.cancelAnimation();
        mZLoadingDialog = null;
    }

    private boolean isContextNotExist() {
        Context context = this.mContext.get();
        return context == null;
    }
}