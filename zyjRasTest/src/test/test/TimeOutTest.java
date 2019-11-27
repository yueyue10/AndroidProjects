package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeOutTest {

	public static void main(String[] args) {
		long currentTime = System.currentTimeMillis();
		System.out.println("当前时间:" + currentTime);
		System.out.println("字符串转时间戳:");
		System.out.println("字符串转时间戳:" + getTime("2016-11-09 09:26:42"));
		System.out.println("时间戳转字符串:" + getStrTime("1478654802000"));
		// String aaString=3* 60 * 60 * 1000;
		float time1 = Float.valueOf(getTime(""));
		if (currentTime > time1 + 3 * 60 * 60 * 1000) {
			//如果当前时间大于超时时间+3小时就可以登陆
		}
	}

	// 字符串转时间戳
	public static String getTime(String timeString) {
		String timeStamp = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d;
		try {
			d = sdf.parse(timeString);
			long l = d.getTime();
			timeStamp = String.valueOf(l);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timeStamp;
	}

	// 时间戳转字符串
	public static String getStrTime(String timeStamp) {
		String timeString = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long l = Long.valueOf(timeStamp);
		timeString = sdf.format(new Date(l));// 单位秒
		return timeString;
	}

	private long timecurrentTimeMillis;
	private long timeGetTime;
	private long timeSeconds;
	private long timeMillis;

	private void init() {
		/**
		 * 以下是获取现在的系统时间戳的几种方法，实际开发中可能需要服务端返回所需的时间戳 ；
		 * 但是实际开发中服务端返回的时间戳可能是字符串等，需要转换为long等，可采用如下方法直接转换：
		 * Integer.parseInt(String s) Long.parseLong(String s)
		 * Float.parseFloat(String s) Double.parseDouble(String s)
		 */
		timecurrentTimeMillis = System.currentTimeMillis();
		timeGetTime = new Date().getTime();
		timeSeconds = System.currentTimeMillis();
		timeMillis = Calendar.getInstance().getTimeInMillis();
		/**
		 * 通过打印信息可以看到，这几种获取系统时间的时间戳几乎是一样的。
		 */
		System.out.println("timecurrentTimeMillis" + timecurrentTimeMillis
				+ "@@@" + "timeGetTime" + timeGetTime + "@@@timeSeconds"
				+ timeSeconds + "timeMillis" + timeMillis);
		/**
		 * 时间戳转换成具体时间形式
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日",
				Locale.getDefault());
		String time1 = sdf.format(timeSeconds);
		String time2 = sdf.format(timeGetTime);
		System.out.println(timeSeconds + "  现在的时间1->:" + time1);
		System.out.println(timeGetTime + "  现在的时间2-->:" + time2);

		SimpleDateFormat sdfTwo = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒E",
				Locale.getDefault());
		String time11 = sdfTwo.format(timeSeconds);
		String time22 = sdfTwo.format(timeGetTime);
		System.out.println(timeSeconds + "  现在的时间11->:" + time11);
		System.out.println(timeGetTime + "  现在的时间22-->:" + time22);
		/**
		 * 而最常用的： 由于服务端返回的一般是UNIX时间戳，所以需要把UNIX时间戳timeStamp转换成固定格式的字符串
		 */
		String result = formatData("yyyy-MM-dd", 1414994617);
		System.out.println(result);

	}

	public static String formatData(String dataFormat, long timeStamp) {
		if (timeStamp == 0) {
			return "";
		}
		timeStamp = timeStamp * 1000;
		String result = "";
		SimpleDateFormat format = new SimpleDateFormat(dataFormat);
		result = format.format(new Date(timeStamp));
		return result;
	}
}
