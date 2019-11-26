package com.passion.zyj.knowall.utils.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.passion.zyj.knowall.R;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {

    Context mContext;
    int radius;

    public GlideImageLoader(Context context) {
        this.mContext = context;
    }

    public GlideImageLoader(Context context, int radius) {
        this.mContext = context;
        this.radius = radius;
    }

    /**
     * 注意：
     * 1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
     * 2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
     * 传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
     * 切记不要胡乱强转！
     */
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        if (mContext != null)
            context = mContext;
        Glide.with(context).load(path).apply(new RequestOptions()
                .placeholder(R.mipmap.icon_no_data)
                .transform(new GlideRoundTransform(mContext, radius))
                .error(R.mipmap.icon_no_data))
                .into(imageView);
    }

    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
    @Override
    public ImageView createImageView(Context context) {
        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        if (mContext != null)
            context = mContext;
        ImageView imageView = new ImageView(context);
        return imageView;
    }
}