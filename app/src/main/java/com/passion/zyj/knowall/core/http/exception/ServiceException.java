package com.passion.zyj.knowall.core.http.exception;

/**
 * @author quchao
 * @date 2017/11/27
 */

public class ServiceException extends Exception {

    private String result;
    private String message;

    public ServiceException(String message, String result) {
        super(message);
        this.message = message;
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message == null ? "" : message;
    }
}
