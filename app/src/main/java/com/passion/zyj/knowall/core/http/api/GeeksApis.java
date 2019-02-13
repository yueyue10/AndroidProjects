package com.passion.zyj.knowall.core.http.api;

import com.passion.zyj.knowall.core.bean.BaseResponse;
import com.passion.zyj.knowall.core.bean.CreateNoteResponse;
import com.passion.zyj.knowall.core.bean.UserInfoBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * @author quchao
 * @date 2018/2/12
 */

public interface GeeksApis {

    String HOST = "http://travel.enn.cn/";//正式环境

    /**
     * 获取验证码
     */
    @GET("account/sendVerificationCode")
    Observable<BaseResponse<String>> getYzmInfo(@Query("account") String phoneNum);

    /**
     * 获取用户信息接口
     */
    @POST("encdata-pandaro/user/getUserInfor")
    @FormUrlEncoded
    Observable<BaseResponse<UserInfoBean>> getUserInfo(@Field("accountNum") String accountNum);

    /**
     * 增加游记
     */
    @POST("encdata-pandaro/travelNote/addNote")
    @Multipart
    Observable<BaseResponse<CreateNoteResponse>> addNote(@Query("userId") String userId, @Query("scenicId") int scenicId, @Part MultipartBody.Part files);

}
