package com.example.zyjtakephoto;

import java.io.File;
import java.util.ArrayList;

import utils.ImageUtils;
import utils.PhotoInfo;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

//依赖于ImageSelecter库进行修改，
public class ImageSelecterActivity extends Activity {

	Context cont;
	Button open;
	ImageView image;
	ArrayList<PhotoInfo> photofiles = new ArrayList<PhotoInfo>();// 返回的选中的图片信息
	public final static String target = "/sdcard/zyjTakePhoto/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imageselecter);
		cont = ImageSelecterActivity.this;
		open = (Button) findViewById(R.id.open);
		image = (ImageView) findViewById(R.id.image);
		open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				jump();
			}
		});

	}

	public void jump() {
		Intent intent = new Intent();
		intent.setClass(cont, MultiImageSelectorActivity.class);
		// 是否显示拍摄图片
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
		// 最大可选择图片数量
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 6);
		// 选择模式
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, 1);
		// 已经选择的图片集合传过去
		// if (list != null && list.size() > 0) {
		// ArrayList<String> selectedphotos = new ArrayList<String>();
		// for (int i = 0; i < list.size(); i++) {
		// selectedphotos.add(list.get(i).smallphotourl);//
		// 传给选择图片界面使用smallphotourl
		// // 没有file://
		// // 传给图片展示界面使用phtoturl有file://
		// }
		// intent.putExtra(
		// MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST,
		// selectedphotos);
		// }
		startActivityForResult(intent, 100);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
			this.photofiles.clear();
			ArrayList<String> selectedphotos = (ArrayList<String>) data
					.getSerializableExtra("select_result");
			if (selectedphotos != null && selectedphotos.size() > 0) {
				for (int i = 0; i < selectedphotos.size(); i++) {
					PhotoInfo pi = new PhotoInfo();
					pi.smallphotourl = selectedphotos.get(i);
					pi.photourl = "file://" + selectedphotos.get(i);// imageloader加载本地图片（在这里没什么用）
																	// uri前面要加上"file://"pi.uploadphotourl
					System.out.println("onActivityResult:smallphotourl"
							+ selectedphotos.get(i));
					String imgstring = ImageUtils.getImageStr(selectedphotos // 根据图片路径生成string
							.get(i));
					System.out
							.println("onActivityResult:imgstring" + imgstring);
					try {
						File file = new File(target);
						if (!file.exists()) {
							file.mkdir();
						}
						ImageUtils
								.generateImage(imgstring, target + "aaa.jpeg");// 根据图片的string在本地生成一张图片
					} catch (Exception e) {
						e.printStackTrace();
					}
					displayImg(target + "aaa.jpeg");
					this.photofiles.add(pi);
				}
			}
		}
	}

	public void displayImg(String filepath) {
		File file = new File(filepath);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 2;// 图片宽高都为原来的二分之一，即图片为原来的四分之一
		if (file.exists()) {
			Bitmap bm = BitmapFactory.decodeFile(filepath, options);
			// 将图片显示到ImageView中
			image.setImageBitmap(bm);
		}
	}
}
