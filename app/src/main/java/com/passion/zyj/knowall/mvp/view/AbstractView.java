package com.passion.zyj.knowall.mvp.view;


/**
 * View 基类
 *
 * @author quchao
 * @date 2017/11/27
 */

public interface AbstractView {

    /**
     * Use night mode
     *
     * @param isNightMode if is night mode
     */
    void useNightMode(boolean isNightMode);


    /**
     * Show error message
     *
     * @param errorMsg error message
     */
    void showErrorMsg(String errorMsg);

    void showTokenError();

    /**
     * showNormal
     */
    void showNormal();

    /**
     * Show error
     */
    void showError();

    /**
     * Show loading
     */
    void showLoading();

    /**
     * Reload
     */
    void reload();

    /**
     * 设置标题
     */
    void setTitleBack(String text);

    /**
     * Show toast
     *
     * @param message Message
     */
    void showToast(String message);

}
