package com.passion.zyj.knowall.core.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.passion.zyj.knowall.app.Constants;
import com.passion.zyj.knowall.app.MyApp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.inject.Inject;


/**
 * @author quchao
 * @date 2017/11/27
 */

public class PreferenceHelperImpl implements PreferenceHelper {

    private final SharedPreferences mPreferences;

    @Inject
    PreferenceHelperImpl() {
        mPreferences = MyApp.getInstance().getSharedPreferences(Constants.MY_SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    @Override
    public void setCookie(String domain, String cookie) {
        mPreferences.edit().putString(domain, cookie).apply();
    }

    @Override
    public String getCookie(String domain) {
        return mPreferences.getString(Constants.COOKIE, "");
    }

    @Override
    public boolean getAutoCacheState() {
        return mPreferences.getBoolean(Constants.AUTO_CACHE_STATE, true);
    }

    @Override
    public boolean getNoImageState() {
        return mPreferences.getBoolean(Constants.NO_IMAGE_STATE, false);
    }

    @Override
    public boolean getNightModeState() {
        return mPreferences.getBoolean(Constants.NIGHT_MODE_STATE, false);
    }

    @Override
    public void setNightModeState(boolean b) {
        mPreferences.edit().putBoolean(Constants.NIGHT_MODE_STATE, b).apply();
    }

    @Override
    public void setNoImageState(boolean b) {
        mPreferences.edit().putBoolean(Constants.NO_IMAGE_STATE, b).apply();
    }

    @Override
    public void setAutoCacheState(boolean b) {
        mPreferences.edit().putBoolean(Constants.AUTO_CACHE_STATE, b).apply();
    }

    @Override
    public void clearAppData() {
        mPreferences.edit().clear().apply();
    }

    /**
     * 存放实体类以及任意类型
     */
    public void setBean(String key, Object obj) {
        if (obj instanceof Serializable) {// obj必须实现Serializable接口，否则会出问题
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                String string64 = new String(Base64.encode(baos.toByteArray(), 0));
                mPreferences.edit().putString(key, string64).commit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("the obj must implement Serializable");
        }

    }

    /**
     * 获取实体类以及任意类型
     */
    public Object getBean(String key) {
        Object obj = null;
        try {
            String base64 = mPreferences.getString(key, "");
            if (base64.equals("")) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
