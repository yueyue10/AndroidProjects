package com.passion.zyj.knowall.core.bean;

/**
 * @author quchao
 * @date 2018/2/12
 */

public class BaseResponse<T> {

    public static final int SUCCESS = 1;
    public static final int FAIL = 0;

    /**
     * 0：成功，1：失败
     */
    private int result;

    private String message;

    private T value;
    private T object;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
