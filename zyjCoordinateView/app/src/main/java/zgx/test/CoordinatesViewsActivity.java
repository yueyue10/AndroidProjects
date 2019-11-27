package zgx.test;

import zgx.widget.Coordinates;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ZoomControls;

/**
 * @ClassName : TestCoordinates
 * @Description : TODO
 * @author : ZGX zhangguoxiao_happy@163.com
 * @date : 2011-10-9 下午02:38:02
 * 
 */
// 自定义坐标轴及画线图
public class CoordinatesViewsActivity extends Activity {
	private int mXScale = 2, mYScale = 2;
	private Coordinates mCoordinates;
	private ZoomControls mZoomControls;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setColor(this, Color.parseColor("#73B3F1"));
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coordinatersviews);

		mCoordinates = (Coordinates) findViewById(R.id.coordinates);
		Paint paint1 = new Paint();
		paint1.setColor(Color.RED);
		mCoordinates.addPoints(getTest1Points(), paint1);
		Paint paint2 = new Paint();
		paint2.setColor(Color.BLUE);
		mCoordinates.addPoints(getTest2Points(), paint2);
		mCoordinates.setScaleXY(mXScale, mYScale);
		// 设置标题
		mCoordinates.setTitleHeight(20);
		mCoordinates.setTitleName("身高记录表");
		// 设置横轴纵轴单位
		mCoordinates.setAxisNamePrickleXY("年龄", "天", "身高", "厘米");
		// 设置边距
		mCoordinates.setCoordinatesPadding(0, 0, 0, 0);

		mZoomControls = (ZoomControls) findViewById(R.id.zoomControls);
		mZoomControls.setOnZoomInClickListener(mOnZoomInClickListener);
		mZoomControls.setOnZoomOutClickListener(mOnZoomOutClickListener);
		// initNavigationBar();
	}

	private PointF[] getTest1Points() {
		PointF[] points = new PointF[5];
		points[0] = new PointF(0, 0);
		points[1] = new PointF(5, 5);
		points[2] = new PointF(15, 10);
		points[3] = new PointF(20, 20);
		points[4] = new PointF(60, 40);
		return points;
	}

	private PointF[] getTest2Points() {
		PointF[] points = new PointF[4];
		points[0] = new PointF(0, 40);
		points[1] = new PointF(20, 5);
		points[2] = new PointF(30, 30);
		points[3] = new PointF(50, 20);
		return points;
	}

	// 放大点击事件
	private View.OnClickListener mOnZoomInClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (mXScale < 8 && mYScale < 8) {
				mXScale *= 2;
				mYScale *= 2;
				mCoordinates.setScaleXY(mXScale, mYScale);
				mCoordinates.invalidate();
			}
		}
	};
	// 缩小点击事件
	private View.OnClickListener mOnZoomOutClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (mXScale > 1 && mYScale > 1) {
				mXScale /= 2;
				mYScale /= 2;
				mCoordinates.setScaleXY(mXScale, mYScale);
				mCoordinates.invalidate();
			}
		}
	};

	public void turnTo(View view) {
		Intent intent = new Intent(this, CoordinatesViewActivity.class);
		startActivity(intent);
	}

	public void setColor(Activity activity, int color) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// 设置状态栏透明
			activity.getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			activity.getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

			// activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			// WindowManager.LayoutParams.FLAG_FULLSCREEN);

			// 生成一个状态栏大小的矩形
			View statusView = createStatusView(activity, color);
			// 添加 statusView 到布局中
			ViewGroup decorView = (ViewGroup) activity.getWindow()
					.getDecorView();
			decorView.addView(statusView);
			// int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
			// |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
			// ;
			// decorView.setSystemUiVisibility(uiOptions);
			// 设置根布局的参数
			ViewGroup rootView = (ViewGroup) ((ViewGroup) activity
					.findViewById(android.R.id.content)).getChildAt(0);
			if (rootView != null) {
				rootView.setFitsSystemWindows(true);
				rootView.setClipToPadding(false);
			}
		}
	}

	/**
	 * * 生成一个和状态栏大小相同的矩形条 * * @param activity 需要设置的activity * @param color
	 * 状态栏颜色值 * @return 状态栏矩形条
	 */
	private View createStatusView(Activity activity, int color) {
		// 获得状态栏高度
		int resourceId = activity.getResources().getIdentifier(
				"status_bar_height", "dimen", "android");
		int statusBarHeight = activity.getResources().getDimensionPixelSize(
				resourceId);

		// 绘制一个和状态栏一样高的矩形
		View statusView = new View(activity);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
		statusView.setLayoutParams(params);
		statusView.setBackgroundColor(color);
		return statusView;
	}
}
