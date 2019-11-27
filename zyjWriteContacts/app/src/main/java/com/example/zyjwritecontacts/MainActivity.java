package com.example.zyjwritecontacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gj.gaojiaohui.qrcode.activity.CaptureActivity;

//写入通讯录，二维码识别，状态栏修改(前两个都是使用高交会的功能)
public class MainActivity extends Activity {
    TextView write_contacts_tv, open_camera, camera_result;
    double doub1 = 59.5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setColor(this, Color.parseColor("#73B3F1"));
        setContentView(R.layout.activity_main);
        int int1 = (int) doub1;
        System.out.println("MainActivity:int1" + int1);
        write_contacts_tv = (TextView) findViewById(R.id.write_contacts_tv);
        open_camera = (TextView) findViewById(R.id.open_camera);
        camera_result = (TextView) findViewById(R.id.camera_result);
        setAllClick();
        Window w = this.getWindow();
        int flag = w.getAttributes().flags;
        if ((flag & 16777216) != 16777216) {
            System.out.println("MainActivity:16777216");
        } else {
            System.out.println("MainActivity:-16777216");
        }
    }

    public void setAllClick() {
        write_contacts_tv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // writeContacts();
                CommonUtils.saveTel(MainActivity.this, "police1", null, "110",
                        null);// 高交会项目摘取
            }
        });
        open_camera.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        CaptureActivity.class);
                startActivityForResult(intent, 200);
            }
        });
    }

    // 网上的方法，直接保存到通讯录里面了。不太好
    public void writeContacts() {
        try {
            ContentResolver resolver = getContentResolver();
            Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
            Uri dataUri = Uri.parse("content://com.android.contacts/data");
            // 查出最后一个id
            Cursor cursor = resolver.query(uri, new String[]{"_id"}, null,
                    null, null);
            cursor.moveToLast();
            int lastId = cursor.getInt(0);
            int newId = lastId + 1;
            // 插入一个联系人id
            ContentValues values = new ContentValues();
            values.put("contact_id", newId);
            resolver.insert(uri, values);
            // 插入电话数据
            ContentValues dataValues = new ContentValues();
            dataValues.put("raw_contact_id", newId);
            dataValues.put("mimetype", "vnd.android.cursor.item/phone_v2");
            dataValues.put("data1", "110");
            resolver.insert(dataUri, dataValues);
            // 插入姓名数据
            ContentValues data1Values = new ContentValues();
            data1Values.put("raw_contact_id", newId);
            data1Values.put("mimetype", "vnd.android.cursor.item/name");
            data1Values.put("data1", "police1");
            resolver.insert(dataUri, data1Values);
            Toast.makeText(this, "写联系人成功", 0).show();
        } catch (Exception e) {
            Toast.makeText(this, "通讯录没有用户!", 0).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                camera_result.setText(data.getStringExtra("qr_result"));
                Toast.makeText(MainActivity.this, data.getStringExtra("qr_result"), 1000).show();
            }
        }
    }

    // 动态修改状态栏颜色
    public void setColor(Activity activity, int color) {
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
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(false);
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
