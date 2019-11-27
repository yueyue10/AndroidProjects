package com.example.zyjtakephoto;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//网上找的方法,不太好用
public class MainActivity extends Activity {

	private Bitmap photo;
	private String picPath;
	TextView showContent;
	private Uri mUri;
	int CAMERA_WITH_DATA = 100;
	private Bitmap bmp_selectedPhoto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showContent = (TextView) findViewById(R.id.showContent);
	}

	public void doPhoto(View view) {
		destoryBimap();
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
			startActivityForResult(intent, 1);
		} else {
			Toast.makeText(MainActivity.this, "没有SD卡", Toast.LENGTH_LONG)
					.show();
		}
	}

	// public void doPhoto(View view) {
	//
	// Intent cameraIntent = new Intent(
	// android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	// File appDir = new File(Environment.getExternalStorageDirectory()
	// + "/KengDieA");
	// if (!appDir.exists()) {
	// appDir.mkdir();
	// }
	// mUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory()
	// + "/KengDieA/", "kengDiePic"
	// + String.valueOf(System.currentTimeMillis()) + ".jpg"));
	//
	// cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mUri);
	//
	// startActivityForResult(cameraIntent, CAMERA_WITH_DATA);
	// }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Uri uri = data.getData();
		System.out.println("onActivityResult:data.getData():" + uri.toString());
		if (uri != null) {
			this.photo = BitmapFactory.decodeFile(uri.getPath());// uri.getPath()这里得到的不是绝对路径
			System.out.println("onActivityResult:BitmapFactory.decodeFile:"
					+ uri.toString());
		}
		if (this.photo == null) {
			Bundle bundle = data.getExtras();
			if (bundle != null) {
				this.photo = (Bitmap) bundle.get("data");
				System.out.println("onActivityResult:bundle.getdata:"
						+ this.photo.toString());
			} else {
				Toast.makeText(MainActivity.this, "拍照失败", Toast.LENGTH_LONG)
						.show();
				return;
			}
		}

		FileOutputStream fileOutputStream = null;
		try {
			// 获取 SD 卡根目录
			String saveDir = Environment.getExternalStorageDirectory()
					+ "/meitian_photos";
			// 新建目录
			File dir = new File(saveDir);
			if (!dir.exists())
				dir.mkdir();
			// 生成文件名
			SimpleDateFormat t = new SimpleDateFormat("yyyyMMddssSSS");
			String filename = "MT" + (t.format(new Date())) + ".jpg";
			// 新建文件
			File file = new File(saveDir, filename);
			// 打开文件输出流
			fileOutputStream = new FileOutputStream(file);
			// 生成图片文件
			this.photo.compress(Bitmap.CompressFormat.JPEG, 100,
					fileOutputStream);
			// 相片的完整路径
			this.picPath = file.getPath();
			ImageView imageView = (ImageView) findViewById(R.id.showPhoto);
			imageView.setImageBitmap(this.photo);
			showContent.setText(this.picPath);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// @Override
	// public void onActivityReenter(int resultCode, Intent data) {
	// ContentResolver cr = this.getContentResolver();
	// try {
	// if (bmp_selectedPhoto != null)// 如果不释放的话，不断取图片，将会内存不够
	// bmp_selectedPhoto.recycle();
	// bmp_selectedPhoto = BitmapFactory.decodeStream(cr
	// .openInputStream(mUri));
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// }
	// ImageView imageView = (ImageView) findViewById(R.id.showPhoto);
	// imageView.setBackgroundDrawable(new BitmapDrawable(getResources(),
	// bmp_selectedPhoto));
	// }

	/**
	 * 销毁图片文件
	 */
	private void destoryBimap() {
		if (photo != null && !photo.isRecycled()) {
			photo.recycle();
			photo = null;
		}
	}
}
