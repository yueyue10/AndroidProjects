package com.example.zyjcheckfile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView hello;
	TextView tosecond;
	TextView content;
	EditText et_folder;
	String folder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		hello = (TextView) findViewById(R.id.hello);
		tosecond = (TextView) findViewById(R.id.tosecond);
		content = (TextView) findViewById(R.id.content);
		et_folder = (EditText) findViewById(R.id.et_folder);
		hello.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				folder = et_folder.getText().toString();
				if (folder.equals("")) {
					Toast.makeText(MainActivity.this, "请输入文件夹名称",
							Toast.LENGTH_SHORT).show();
				} else {
					boolean a = searchFile();
				}
			}
		});
		tosecond.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						SecondActivity.class);
				startActivity(intent);
			}
		});
	}

	private String traget = getRootPath() + File.separator;

	public static String getRootPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在

		if (sdCardExist) { // 如果SD卡存在，则获取跟目录
			sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
		} else {
			sdDir = Environment.getRootDirectory();// 如果没有SD卡，则存放于内存卡根目录
		}
		return sdDir.toString();
	}

	public boolean searchFile() {
		traget=traget+folder;
		System.out.println("aaa:zyj:rootpath" + traget);
		File[] files = new File(traget).listFiles();
		StringBuffer sbf = new StringBuffer();
		sbf.append(traget + "目录下的文件有："+"\n");
		for (File file : files) {
			sbf.append("文件名：" + file.getName() + "\n");
			sbf.append("文件路径：" + file.getName() + "\n");
		}
		content.setText(sbf);
		return false;
	}

	// 从sd卡获取图片资源
	private List<String> getImagePathFromSD() {
		// 图片列表
		List<String> picList = new ArrayList<String>();

		// 得到sd卡内路径
		String imagePath = Environment.getExternalStorageDirectory().toString()
				+ "/image";

		// 得到该路径文件夹下所有的文件
		File mfile = new File(imagePath);
		File[] files = mfile.listFiles();

		// 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (checkIsImageFile(file.getPath())) {
				picList.add(file.getPath());
			}
		}
		// 返回得到的图片列表
		return picList;
	}

	// 检查扩展名，得到图片格式的文件
	private boolean checkIsImageFile(String fName) {
		boolean isImageFile = false;

		// 获取扩展名
		String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
				fName.length()).toLowerCase();
		if (FileEnd.equals("jpg") || FileEnd.equals("gif")
				|| FileEnd.equals("png") || FileEnd.equals("jpeg")
				|| FileEnd.equals("bmp")) {
			isImageFile = true;
		} else {
			isImageFile = false;
		}

		return isImageFile;

	}
}
