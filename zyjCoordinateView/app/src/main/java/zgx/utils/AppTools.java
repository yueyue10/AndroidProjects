package zgx.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;

import zgx.test.MyApplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * App涓父鐢ㄧ殑宸ュ叿锟?br> 杩欎釜绫讳腑瀹氫箟浜嗕竴绯诲垪绉佹湁鐨勫父閲忥紝渚夸簬淇濆瓨ShareData鏃剁洿鎺ヨ皟鐢紝闃叉淇濆瓨鎴栵拷?鑾峰彇鏁版嵁鏃跺洜涓哄悕绉颁笉锟??锟斤拷閫犳垚bug
 * 
 * @author Administrator
 * 
 */
public class AppTools {

	private static TelephonyManager telephonyManager = null;

	private AppTools() {
	}

	/**
	 * 鑾峰彇鎵嬫満IMEI
	 * 
	 * @param mContext
	 * @return
	 */
	public static String getIMEI(Context mContext) {
		String imei;
		try {
			if (telephonyManager == null) {
				telephonyManager = (TelephonyManager) mContext
						.getSystemService(Context.TELEPHONY_SERVICE);
			}
			imei = telephonyManager.getDeviceId();
		} catch (Exception e) {
			return "";
		}
		if (imei == null) {
			return "";
		}
		return imei;
	}

	public static <E> boolean isBlack(List<E> list) {
		return (list == null || list.size() == 0);
	}

	public static <E> boolean isNotBlack(List<E> list) {
		return !isBlack(list);
	}

	public static void setListViewHeightBasedOnChildren(ListView listView) {
		if (listView == null)
			return;

		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = listView.getListPaddingTop()
				+ listView.getListPaddingBottom() + totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	/**
	 * 鑾峰彇鎸囧畾绫荤殑娉涘瀷
	 */
	public static Class<?> getGenericClass(Class<?> clazz) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (!(params[0] instanceof Class)) {
			return Object.class;
		}
		return (Class<?>) params[0];
	}

	/**
	 * 璋冪敤绯荤粺娴忚锟?
	 */
	public static void invokeBrowser(Activity activity, String url) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		Uri content_url = Uri.parse(url);
		intent.setData(content_url);
		activity.startActivity(intent);
	}

	/**
	 * 璋冪敤绯荤粺鎵撶數璇濋〉锟?
	 */
	public static void call(Activity activity, String number) {
		Uri uri = Uri.parse("tel:" + number);
		Intent intent = new Intent(Intent.ACTION_DIAL, uri);
		activity.startActivity(intent);
	}

	public static void copy(Context myContext, String ASSETS_NAME,
			String savePath, String saveName) {
		String filename = savePath + File.separator + saveName;
		File dir = new File(savePath);
		if (!dir.exists())
			dir.mkdir();
		try {
			if (!(new File(filename)).exists()) {
				InputStream is = myContext.getResources().getAssets()
						.open(ASSETS_NAME);
				FileOutputStream fos = new FileOutputStream(filename);
				byte[] buffer = new byte[7168];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copyFromUrl(String path, String savePath, String saveName) {
		String filename = savePath + File.separator + saveName;
		File dir = new File(savePath);
		if (!dir.exists())
			dir.mkdir();
		try {
			// File f = new File(filename);
			// if (f.exists()) {
			// f.delete();
			// }
			URL url = new URL(path);
			InputStream is = url.openStream();
			FileOutputStream fos = new FileOutputStream(filename);
			byte[] buffer = new byte[1024];
			int count = 0;
			while ((count = is.read(buffer)) > 0) {
				fos.write(buffer, 0, count);
			}
			fos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 鎷疯礉ghmap.data ghmap.sld ghmap.lst鍒板伐绋嬮粯璁ゅ瓨鍌ㄧ洰褰曚腑
	 */
	public static void copyMapData(Context context) {
		// String path = getRootPath();
		String path = context.getCacheDir().getAbsolutePath();
		System.out.println(path);
		String filename = "ghmap.dat";
		{
			File dataFile = new File(path + File.separator + filename);
			if (dataFile.exists()) {
				dataFile.delete();
			}
		}
		{
			File dataFile = new File(path + File.separator + "ghmap.sld");
			if (dataFile.exists()) {
				dataFile.delete();
			}
		}
		// {
		// File dataFile = new File(path + File.separator + "ghmap.lst");
		// if (dataFile.exists()) {
		// dataFile.delete();
		// }
		// }
		copy(context, filename, path, filename);
		copy(context, "ghmap.sld", path, "ghmap.sld");
	}

	public static String getRootPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // 鍒ゆ柇sd鍗℃槸鍚﹀瓨锟?

		if (sdCardExist) { // 濡傛灉SD鍗″瓨鍦紝鍒欒幏鍙栬窡鐩綍
			sdDir = Environment.getExternalStorageDirectory();// 鑾峰彇璺熺洰锟?
		} else {
			sdDir = Environment.getRootDirectory();// 濡傛灉娌℃湁SD鍗★紝鍒欏瓨鏀句簬鍐呭瓨鍗℃牴鐩綍
		}
		return sdDir.toString();
	}

	public static String getVersionCode() {
		String packageName = MyApplication.getInstance().getPackageName();
		try {
			return MyApplication.getInstance().getPackageManager()
					.getPackageInfo(packageName, 0).versionCode
					+ "";
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "0";
		}
	}

	public static String getVersionName() {
		String packageName = MyApplication.getInstance().getPackageName();
		try {
			return MyApplication.getInstance().getPackageManager()
					.getPackageInfo(packageName, 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "1.0";
		}
	}

	public static boolean getVerify(Context context) {
		// boolean isVerify = true;
		// if (isWifiEnabled(context)) {
		// WifiManager wifiManager = (WifiManager)
		// context.getSystemService(context.WIFI_SERVICE);
		// WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		// if (null != wifiInfo) {
		// Log.d("wifiInfo", wifiInfo.toString());
		// String ssid = wifiInfo.getSSID();
		// if (!StringTools.isBlank(ssid)) {
		// Log.d("SSID", ssid);
		// ssid = ssid.toUpperCase();
		// if (ssid.contains("RSE-FREE")) {
		// isVerify = false;
		// }
		// }
		// }
		// }

		return true;
	}

	/**
	 * 鍒ゆ柇WIFI鏄惁鎵撳紑
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiEnabled(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		TelephonyManager mgrTel = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return ((cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo()
				.getState() == NetworkInfo.State.CONNECTED) || mgrTel
				.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
	}

	/**
	 * 鍒ゆ柇鏄惁锟?G缃戠粶
	 * 
	 * @param context
	 * @return
	 */
	public static boolean is3rd(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkINfo = cm.getActiveNetworkInfo();
		if (networkINfo != null
				&& networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
			return true;
		}
		return false;
	}

	public static String getLocalMacAddress(Context context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}

	/**
	 * Android 鑾峰彇鏈満IP鍦板潃鏂规硶锟?
	 * 
	 * @return
	 */
	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e("WifiPreference IpAddress", ex.toString());
		}
		return null;
	}

	/**
	 * 鑾峰緱鎵嬫満锟?
	 * 
	 * @param activity
	 * @return
	 */
	public static String getLocalPhoneNum(Activity activity) {
		TelephonyManager tm = (TelephonyManager) activity
				.getSystemService(Context.TELEPHONY_SERVICE);
		String phoneId = tm.getLine1Number();

		return phoneId;
	}
}
