package zgx.test;

import android.app.Application;

/**
 * @ClassName : MyApplication
 * @Description : TODO
 * @author : ZGX zhangguoxiao_happy@163.com
 * @date : 2011-9-24 下午03:01:00
 * 
 */
public class MyApplication extends Application {
	private int num;
	private static MyApplication application = null;

	@Override
	public void onCreate() {
		super.onCreate();
		application = this;
		setNum(10);
	}

	public static MyApplication getInstance() {
		return application;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
