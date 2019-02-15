package com.passion.zyj.knowall.ui.common;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.photoview.PhotoView;
import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.mvp.activity.BaseNorActivity;
import com.passion.zyj.knowall.utils.view.HackyViewPager;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 展示图片的界面
 */
public class ShowPictureAc extends BaseNorActivity {

    @BindView(R.id.picture_vp)
    HackyViewPager picture_vp;
    @BindView(R.id.tv_number)
    TextView tv_number;
    SamplePagerAdapter pagerAdapter;
    ArrayList<String> picture_list = new ArrayList<>();
    int mPosition = 0;
    boolean isFull = false;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_showpicture;
    }

    @Override
    protected void initToolbar() {
        setTitleBack("图片详情");
    }

    @Override
    protected void initEventAndData() {
        if (getIntent().getStringArrayListExtra("data") != null)
            picture_list = getIntent().getStringArrayListExtra("data");
        mPosition = getIntent().getIntExtra("position", 0);
        isFull = getIntent().getBooleanExtra("isFull", false);
        process();
    }

    private void process() {
        tv_number.setText(mPosition + 1 + "/" + picture_list.size());
        pagerAdapter = new SamplePagerAdapter(picture_list);
        picture_vp.setAdapter(pagerAdapter);
        picture_vp.setCurrentItem(mPosition);
        picture_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (picture_list.size() > 0) {
                    tv_number.setText(position + 1 + "/" + picture_list.size());
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    class SamplePagerAdapter extends PagerAdapter {

        public ArrayList<String> list;

        public SamplePagerAdapter(ArrayList<String> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size() > 0 ? list.size() : null;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            Glide.with(mActivity).load(list.get(position))
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.icon_no_data)
                            .error(R.mipmap.icon_no_data))
                    .into(photoView);
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
