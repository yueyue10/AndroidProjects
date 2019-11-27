package com.smartdot.mywebview.utils;

/**
 * Created by zhaoyuejun on 2017/6/15.
 */

public class AsyncTaskForWait extends BaseAsyncTask<String, String> {

    public AsyncTaskForWait(AsyncTaskCallBack<String> callBack) {
        super(callBack);
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Thread.sleep(2000);// 停留2秒
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
