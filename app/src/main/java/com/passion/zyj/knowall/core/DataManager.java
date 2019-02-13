package com.passion.zyj.knowall.core;


import com.passion.zyj.knowall.core.bean.BaseResponse;
import com.passion.zyj.knowall.core.bean.CreateNoteResponse;
import com.passion.zyj.knowall.core.bean.UserInfoBean;
import com.passion.zyj.knowall.core.http.api.GeeksApis;
import com.passion.zyj.knowall.core.prefs.PreferenceHelper;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * @author quchao
 * @date 2017/11/27
 */

public class DataManager implements GeeksApis, PreferenceHelper {

    private GeeksApis geeksApis;
    private PreferenceHelper mPreferenceHelper;

    public DataManager(GeeksApis geeksApis, PreferenceHelper preferencesHelper) {
        this.geeksApis = geeksApis;
        mPreferenceHelper = preferencesHelper;
    }


    //本地缓存
    @Override
    public void setCookie(String domain, String cookie) {
        mPreferenceHelper.setCookie(domain, cookie);
    }

    @Override
    public String getCookie(String domain) {
        return mPreferenceHelper.getCookie(domain);
    }

    @Override
    public boolean getAutoCacheState() {
        return mPreferenceHelper.getAutoCacheState();
    }

    @Override
    public boolean getNoImageState() {
        return mPreferenceHelper.getNoImageState();
    }

    @Override
    public boolean getNightModeState() {
        return mPreferenceHelper.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean b) {
        mPreferenceHelper.setNightModeState(b);
    }

    @Override
    public void setNoImageState(boolean b) {
        mPreferenceHelper.setNoImageState(b);
    }

    @Override
    public void setAutoCacheState(boolean b) {
        mPreferenceHelper.setAutoCacheState(b);
    }

    @Override
    public void clearAppData() {
        mPreferenceHelper.clearAppData();
    }

    @Override
    public Observable<BaseResponse<String>> getYzmInfo(String phoneNum) {
        return geeksApis.getYzmInfo(phoneNum);
    }

    @Override
    public Observable<BaseResponse<UserInfoBean>> getUserInfo(String accountNum) {
        return geeksApis.getUserInfo(accountNum);
    }

    @Override
    public Observable<BaseResponse<CreateNoteResponse>> addNote(String userId, int scenicId, MultipartBody.Part files) {
        return geeksApis.addNote(userId, scenicId, files);
    }
}
