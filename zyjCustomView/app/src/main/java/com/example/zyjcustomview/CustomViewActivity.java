package com.example.zyjcustomview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class CustomViewActivity extends Activity {
	ImageView imageView = null;

	@TargetApi(Build.VERSION_CODES.KITKAT)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		imageView = new ImageView(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		//透明状态栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		//透明导航栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);		
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.linear_parent);
		ll.addView(imageView);
		// 向左移动
		Button botton0 = (Button) findViewById(R.id.buttonLeft);
		botton0.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				imageView.setPosLeft();
			}
		});

		// 向右移动
		Button botton1 = (Button) findViewById(R.id.buttonRight);
		botton1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				imageView.setPosRight();
			}
		});
		// 左旋转
		Button botton2 = (Button) findViewById(R.id.buttonRotationLeft);
		botton2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				imageView.setRotationLeft();
			}
		});

		// 右旋转
		Button botton3 = (Button) findViewById(R.id.buttonRotationRight);
		botton3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				imageView.setRotationRight();
			}
		});

		// 缩小
		Button botton4 = (Button) findViewById(R.id.buttonNarrow);
		botton4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				imageView.setNarrow();
			}
		});

		// 放大
		Button botton5 = (Button) findViewById(R.id.buttonEnlarge);
		botton5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				imageView.setEnlarge();
			}
		});
		// 动画
		Button button6 = (Button) findViewById(R.id.buttonframeani);
		button6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(CustomViewActivity.this,
						FramAnimation.class);
				startActivity(intent);
			}
		});
		// 上下回弹 scollView
		Button button7 = (Button) findViewById(R.id.buttonmyscrollview);
		button7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(CustomViewActivity.this,
						CustomScrollViewActivity.class);
				startActivity(intent);
			}
		});
		// 上下回弹 scollView
		Button button8 = (Button) findViewById(R.id.buttonscrollvgesture);
		button8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(CustomViewActivity.this,
						ScrollvGestureActivity.class);
				startActivity(intent);
			}
		});
		// Scroller
		Button button9 = (Button) findViewById(R.id.buttonscroller);
		button9.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(CustomViewActivity.this,
						ScrollerActivity.class);
				startActivity(intent);
			}
		});

		super.onCreate(savedInstanceState);

	}

	class ImageView extends View {
		Paint mPaint = null;
		Bitmap bitMap = null;
		Bitmap bitMapDisplay = null;
		int m_posX = 120;
		int m_posY = 50;
		int m_bitMapWidth = 0;
		int m_bitMapHeight = 0;
		Matrix mMatrix = null;
		float mAngle = 0.0f;
		float mScale = 1f;// 1为原图的大小

		public ImageView(Context context) {
			super(context);
			mPaint = new Paint();
			mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
			bitMap = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.image);
			bitMapDisplay = bitMap;
			mMatrix = new Matrix();
			// 获取图片宽高
			m_bitMapWidth = bitMap.getWidth();
			m_bitMapHeight = bitMap.getHeight();
		}

		// 向左移动
		public void setPosLeft() {
			m_posX -= 10;
		}

		// 向右移动
		public void setPosRight() {
			m_posX += 10;
		}

		// 向左旋转
		public void setRotationLeft() {
			mAngle--;
			setAngle();
		}

		// 向右旋转
		public void setRotationRight() {
			mAngle++;
			setAngle();
		}

		// 缩小图片
		public void setNarrow() {
			if (mScale > 0.5) {
				mScale -= 0.1;
				setScale();
			}
		}

		// 放大图片
		public void setEnlarge() {
			if (mScale < 2) {
				mScale += 0.1;
				setScale();
			}
		}

		// 设置缩放比例
		public void setAngle() {
			mMatrix.reset();
			mMatrix.setRotate(mAngle);
			bitMapDisplay = Bitmap.createBitmap(bitMap, 0, 0, m_bitMapWidth,
					m_bitMapHeight, mMatrix, true);
		}

		// 设置旋转比例
		public void setScale() {
			mMatrix.reset();
			// float sx X轴缩放
			// float sy Y轴缩放
			mMatrix.postScale(mScale, mScale);
			bitMapDisplay = Bitmap.createBitmap(bitMap, 0, 0, m_bitMapWidth,
					m_bitMapHeight, mMatrix, true);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.drawBitmap(bitMapDisplay, m_posX, m_posY, mPaint);
			invalidate();
		}
	}
}