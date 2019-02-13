package com.passion.zyj.knowall.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.passion.zyj.knowall.app.Constants;
import com.passion.zyj.knowall.app.MyApp;
import com.passion.zyj.knowall.utils.string.zSpannableStringBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * @author quchao
 * @date 2017/11/27
 */

public class CommonUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        final float scale = MyApp.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * Show message
     *
     * @param activity Activity
     * @param msg      message
     */
    public static void showMessage(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Show message
     */
    public static void showMessageCenter(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * Show message
     */
    public static void showMessageCenter(Context context, int resId) {
        Toast toast = Toast.makeText(context, context.getText(resId), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * Show message
     *
     * @param msg message
     */
    public static void showMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 判断2个对象是否相等
     *
     * @param a Object a
     * @param b Object b
     * @return isEqual
     */
    public static boolean isEquals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApp.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取随机rgb颜色值
     */
    public static int randomColor() {
        Random random = new Random();
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int red = random.nextInt(150);
        //0-190
        int green = random.nextInt(150);
        //0-190
        int blue = random.nextInt(150);
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red, green, blue);
    }


    /**
     * 泛型转换工具方法 eg:object ==> map<String, String>
     *
     * @param object Object
     * @param <T>    转换得到的泛型对象
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object object) {
        return (T) object;
    }

    public static void IntentUrl(Context context, String str) {
        try {
            Uri uri = Uri.parse(str);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 调来拨打电话的页面
     *
     * @param context
     * @param phoneNum
     */
    public static void call(Context context, String phoneNum) {
        try {
            Uri uri = Uri.parse("tel:" + phoneNum);
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 获取设备唯一ID
     */
    public static String getUniqueId(Context context) {
        String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        String id = androidID + Build.SERIAL;
        try {
            return toMD5(id);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return id;
        }
    }

    private static String toMD5(String text) throws NoSuchAlgorithmException {
        //获取摘要器 MessageDigest
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        //通过摘要器对字符串的二进制字节数组进行hash计算
        byte[] digest = messageDigest.digest(text.getBytes());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            //循环每个字符 将计算结果转化为正整数;
            int digestInt = digest[i] & 0xff;
            //将10进制转化为较短的16进制
            String hexString = Integer.toHexString(digestInt);
            //转化结果如果是个位数会省略0,因此判断并补0
            if (hexString.length() < 2) {
                sb.append(0);
            }
            //将循环结果添加到缓冲区
            sb.append(hexString);
        }
        //返回整个结果
        return sb.toString();
    }

    /**
     * 隐藏键盘
     */
    public static void hideInputMethod(Activity context) {
        try {
            InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(context.getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            Logger.e(e.toString());
        }
    }

    public static void closeKeyboard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static Map<String, String> putToken() {
        Map<String, String> header = new HashMap<>();
        header.put("X-TOKEN", Constants.TOKEN);
        return header;
    }

    /**
     * 设置某个View的margin
     *
     * @param view   需要设置的view
     * @param isDp   需要设置的数值是否为DP
     * @param left   左边距
     * @param right  右边距
     * @param top    上边距
     * @param bottom 下边距
     * @return
     */
    public static ViewGroup.LayoutParams setViewMargin(View view, boolean isDp, int left, int right, int top, int bottom) {
        if (view == null) {
            return null;
        }
        int leftPx = left;
        int rightPx = right;
        int topPx = top;
        int bottomPx = bottom;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginParams = null;
        //获取view的margin设置参数
        if (params instanceof ViewGroup.MarginLayoutParams) {
            marginParams = (ViewGroup.MarginLayoutParams) params;
        } else {
            //不存在时创建一个新的参数
            marginParams = new ViewGroup.MarginLayoutParams(params);
        }
        //根据DP与PX转换计算值
        if (isDp) {
            leftPx = dp2px(left);
            rightPx = dp2px(right);
            topPx = dp2px(top);
            bottomPx = dp2px(bottom);
        }
        //设置margin
        marginParams.setMargins(leftPx, topPx, rightPx, bottomPx);
        view.setLayoutParams(marginParams);
        return marginParams;
    }

    public static void makeAskAnsStatus(TextView textView, int status) {
        switch (status) {
            case 0:
                textView.setVisibility(View.GONE);
                break;
            case 1:
                textView.setText("审核中");
                textView.setTextColor(Color.parseColor("#FF07B6C4"));
                break;
            case 2:
                textView.setText("审核失败");
                textView.setTextColor(Color.parseColor("#FFFA5456"));
                break;
        }
    }

    public static void makeAskAnsDetailStatus(TextView textView, int status) {
        switch (status) {
            case 0:
                textView.setVisibility(View.GONE);
                break;
            case 1:
                textView.setText("*发布成功，等待管理员审核！");
                textView.setTextColor(Color.parseColor("#FF07B6C4"));
                break;
            case 2:
                textView.setText("*发布失败！");
                textView.setTextColor(Color.parseColor("#FFFA5456"));
                break;
        }
    }

    public static String listToString(List<String> stringList, String dividerStr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : stringList) {
            stringBuilder.append(string + dividerStr);
        }
        String string = stringBuilder.toString();
        return string.substring(0, string.lastIndexOf(dividerStr));
    }

    public static List<String> stringToList(String string, String splitStr) {
        try {
            String[] strings = string.split(splitStr);
            return Arrays.asList(strings);
        } catch (Exception e) {
            Logger.e(e.toString());
            return new ArrayList<>();
        }
    }

    public static void loadImage(Context mActivity, String imgUrl, ImageView imageView) {
        Glide.with(mActivity).load(imgUrl).into(imageView);
    }

    public static String getUserName(String name) {
        String author = name;
        if (StringUtils.isAsNull(author))
            author = "匿名用户";
        if (StringUtils.isMobile(author))
            author = author.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return author;
    }

    public static SpannableStringBuilder getMoreStringBuilder(int comCount, String name, String endStr) {
        zSpannableStringBuilder style1 = new zSpannableStringBuilder(String.valueOf(comCount));
        zSpannableStringBuilder style2 = new zSpannableStringBuilder("条和");
        zSpannableStringBuilder style3 = new zSpannableStringBuilder("“" + name + "”");
        zSpannableStringBuilder style4 = new zSpannableStringBuilder(endStr);
        style1.setTextColor(Color.parseColor("#FF5C5C5E")).setTextStyleBold();
        style2.setTextColor(Color.parseColor("#FF878789")).setTextStyleNormal();
        style3.setTextColor(Color.parseColor("#FF5C5C5E")).setTextStyleBold();
        style4.setTextColor(Color.parseColor("#FF878789")).setTextStyleNormal();
        return style1.append(style2).append(style3).append(style4);
    }


}
