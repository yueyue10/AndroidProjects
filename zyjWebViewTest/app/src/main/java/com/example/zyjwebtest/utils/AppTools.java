package com.example.zyjwebtest.utils;

import java.io.File;

import android.os.Environment;

public class AppTools {
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
}
