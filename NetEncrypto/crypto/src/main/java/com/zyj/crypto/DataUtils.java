package com.zyj.crypto;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Base64;

import java.nio.ByteBuffer;

public class DataUtils {
    /**
     * base64加密
     */
    @TargetApi(Build.VERSION_CODES.O)
    public static String base64Encode(byte[] bytes) {
        try {
            return Base64.encodeToString(bytes, Base64.NO_WRAP);
        } catch (Exception e) {
            return java.util.Base64.getEncoder().encodeToString(bytes);
        }
    }

    /**
     * base64解码
     */
    @TargetApi(Build.VERSION_CODES.O)
    public static byte[] base64Decode(String data) {
        try {
            return Base64.decode(data, Base64.NO_WRAP);
        } catch (Exception e) {
            return java.util.Base64.getMimeDecoder().decode(data);
        }
    }

    public static byte[] int2Byte(int data) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(data);
        return buffer.array();
    }
}
