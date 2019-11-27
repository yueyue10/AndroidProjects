package com.zyj.baidumap;

//import com.sds.gw_phone.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.Toast;

public class PluginDatePickerActivity extends Activity {
	private DatePickerDialog mDatePickerDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setFinishOnTouchOutside(true);//
		datepicker();
	}

	@SuppressWarnings("deprecation")
	public void datepicker() {
		Intent intent = getIntent();
		// String[] str = new String[3];
		String[] str = new String[] { "2016", "5", "1" };
		if (intent.getStringArrayExtra("send") != null) {
			str = intent.getStringArrayExtra("send");
		}
		mDatePickerDialog = new DatePickerDialog(this,
				new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						Intent result = new Intent();
						String date = String.format("%04d.%02d.%02d", year,
								monthOfYear + 1, dayOfMonth).toString();
						result.putExtra("date", date);
						setResult(RESULT_OK, result);
						finish();
					}

				}, Integer.parseInt(str[0]), Integer.parseInt(str[1]) - 1,
				Integer.parseInt(str[2]));

		// datepicker에서 취소를 눌렀을 경우 종료함
		mDatePickerDialog.setButton2(
				this.getResources().getString(R.string.btn_cancel),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						setResult(RESULT_CANCELED);
						finish();
					}
				});

		// datepicker에서 backkey를 눌렀을 경우 종료함
		mDatePickerDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == KeyEvent.ACTION_UP) {
					if (keyCode == KeyEvent.KEYCODE_BACK) {
						setResult(RESULT_CANCELED);
						finish();
					}
				}
				return false;
			}
		});
		mDatePickerDialog
				.setOnDismissListener(new DialogInterface.OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						System.out
								.println("PluginDatePickerActivity:setOnDismissListener");
					}
				});
		// mDatePickerDialog.setCanceledOnTouchOutside(false);
		// mDatePickerDialog.setCanceledOnTouchOutside(true);
		// mDatePickerDialog.setCancelable(true);
		mDatePickerDialog.show();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		Toast.makeText(this, "onConfigurationChanged", Toast.LENGTH_SHORT)
				.show();
		mDatePickerDialog.dismiss();
		this.finish();
	}
}
