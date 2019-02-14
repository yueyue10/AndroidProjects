package com.passion.zyj.knowall.core.bean;

/**
 * @author quchao
 * @date 2018/2/12
 */

public class BaseResponse<T> {

    public static final int SUCCESS = 0;
    public static final int FAIL = 1;

    /**
     * 200：成功，1：失败
     */
    private String resultcode;

    private String reason;

    private int error_code;

    private T result;

    public String getResultcode() {
        return resultcode == null ? "" : resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason == null ? "" : reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
