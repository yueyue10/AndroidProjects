package com.passion.zyj.knowall.mvp.presenter;

import com.passion.zyj.knowall.mvp.view.AbstractView;

import io.reactivex.disposables.Disposable;

/**
 * Presenter 基类
 *
 * @author quchao
 * @date 2017/11/27
 */

public interface AbstractPresenter<T extends AbstractView> {

    /**
     * 注入View
     *
     * @param view view
     */
    void attachView(T view);

    /**
     * 回收View
     */
    void detachView();

    /**
     * Add rxBing subscribe manager
     *
     * @param disposable Disposable
     */
    void addRxBindingSubscribe(Disposable disposable);

    /**
     * Clear AppData
     */
    void clearAppData();

    /**
     * Set cookie
     *
     * @param domain Domain
     * @param cookie Cookie
     */
    void setCookie(String domain, String cookie);

    /**
     * Get cookie
     *
     * @param domain Domain
     * @return cookie
     */
    String getCookie(String domain);

    /**
     * Get night mode state
     *
     * @return if is night mode
     */
    boolean getNightModeState();

    /**
     * Set night mode state
     *
     * @param b current night mode state
     */
    void setNightModeState(boolean b);


}
