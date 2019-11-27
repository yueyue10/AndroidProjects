package com.example.zyjtakephoto;

import java.io.File;

import me.nereo.multi_image_selector.utils.FileUtils;
import utils.DipUtils;
import utils.FilePathUtils;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

//网上找的方法,比较好用
public class SecondActivity extends Activity {

	private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
	private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
	private static final int PHOTO_REQUEST_CUT = 3;// 结果
	private ImageView iv_image;
	ImageView gallery_tv;
	ImageView camera_tv;
	Button open;
	/* 头像名称 */
	private File tempFile;
	private PopupWindow popupWindow;
	private View poplayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		this.iv_image = (ImageView) this.findViewById(R.id.iv_image);
		// 加载poplayout
		poplayout = LayoutInflater.from(this).inflate(
				R.layout.popwindow_tologin, null);
		gallery_tv = (ImageView) poplayout.findViewById(R.id.gallery_tv);
		camera_tv = (ImageView) poplayout.findViewById(R.id.camera_tv);
		open = (Button) findViewById(R.id.open);
		open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showPopUp(poplayout);
			}
		});
		gallery_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				gallery();
				popupWindow.dismiss();
			}
		});
		camera_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				camera();
				popupWindow.dismiss();
			}
		});
	}

	public void jumpToImageSelecterActivity(View view) {
		Intent intent = new Intent(SecondActivity.this,
				ImageSelecterActivity.class);
		startActivity(intent);
	}

	public void showPopUp(View v) {
		int width = 6 * DipUtils.getWindows(this)[0] / 7;
		int height = 1 * DipUtils.getWindows(this)[0] / 2;
		popupWindow = new PopupWindow(poplayout, width, height, true);

		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		int[] location = new int[2];
		v.getLocationOnScreen(location);

		// popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0]
		// + DipUtils.getWindows(this)[0] / 12,
		// location[1] - popupWindow.getHeight());
		// popupWindow.setAnimationStyle(R.style.popwin_anim_style);
		popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
		// 设置背景颜色变暗
		WindowManager.LayoutParams lp = getWindow().getAttributes();

		lp.alpha = 0.4f;
		getWindow().setAttributes(lp);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // 加上这句
		popupWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = 1f;
				getWindow().setAttributes(lp);
			}
		});
	}

	/*
	 * 从相册获取
	 */
	public void gallery() {
		// 激活系统图库，选择一张图片
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");
		// 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
		startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
	}

	/*
	 * 从相机获取
	 */
	public void camera() {
		// 激活相机
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		// 判断存储卡是否可以用，可用进行存储
		if (hasSdcard()) {
			// tempFile = new File(Environment.getExternalStorageDirectory(),
			// PHOTO_FILE_NAME);
			tempFile = FileUtils.createTmpFile(this);
			// 从文件中创建uri
			Uri uri = Uri.fromFile(tempFile);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		}
		// 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
		startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
	}

	/*
	 * 判断sdcard是否被挂载
	 */
	private boolean hasSdcard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	// 展示图片
	public void displayImg(String filepath) {
		File file = new File(filepath);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 2;// 图片宽高都为原来的二分之一，即图片为原来的四分之一
		if (file.exists()) {
			Bitmap bm = BitmapFactory.decodeFile(filepath, options);
			// 将图片显示到ImageView中
			iv_image.setImageBitmap(bm);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PHOTO_REQUEST_GALLERY) {
			// 从相册返回的数据
			if (data != null) {
				// 得到图片的全路径
				Uri uri = data.getData();
				String realpath = FilePathUtils.getRealFilePath(this, uri);
				System.out.println("onActivityResult:realpath" + realpath);
				// realpath = uri.getPath();
				// System.out.println("onActivityResult:uri.getPath" +
				// realpath);

				// onActivityResult:realpath-----/storage/emulated/0/DCIM/Camera/20161222_165324.jpg
				// onActivityResult:uri.getPath--/external/images/media/2880
				displayImg(realpath);
				// crop(uri);
			}

		} else if (requestCode == PHOTO_REQUEST_CAREMA) {
			// 从相机返回的数据
			if (resultCode == Activity.RESULT_OK) {
				if (tempFile != null) {
					displayImg(tempFile.getPath());
				}
			} else {
				if (tempFile != null && tempFile.exists()) {
					tempFile.delete();
				}
			}
		} else if (requestCode == PHOTO_REQUEST_CUT) {
			// 从剪切图片返回的数据
			if (data != null) {
				Bitmap bitmap = data.getParcelableExtra("data");
				this.iv_image.setImageBitmap(bitmap);
			}
			try {
				// 将临时文件删除
				tempFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/*
	 * 剪切图片
	 */
	private void crop(Uri uri) {
		// 裁剪图片意图
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		// 裁剪框的比例，1：1
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// 裁剪后输出图片的尺寸大小
		intent.putExtra("outputX", 250);
		intent.putExtra("outputY", 250);

		intent.putExtra("outputFormat", "JPEG");// 图片格式
		intent.putExtra("noFaceDetection", true);// 取消人脸识别
		intent.putExtra("return-data", true);
		// 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
		startActivityForResult(intent, PHOTO_REQUEST_CUT);
	}

}
