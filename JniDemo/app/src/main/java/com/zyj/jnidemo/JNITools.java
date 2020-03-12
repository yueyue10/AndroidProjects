package com.zyj.jnidemo;

public class JNITools {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public static native String stringFromJNI();

    //加法
    public static native int add(int a, int b);

    //减法
    public static native int sub(int a, int b);

    //乘法
    public static native int mul(int a, int b);

    //除法
    public static native int div(int a, int b);
}
