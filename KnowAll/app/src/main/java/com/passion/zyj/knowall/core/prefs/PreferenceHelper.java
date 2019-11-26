package com.passion.zyj.knowall.core.prefs;


/**
 * @author quchao
 * @date 2017/11/27
 */

public interface PreferenceHelper {

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
     * Get auto cache state
     *
     * @return if auto cache state
     */
    boolean getAutoCacheState();

    /**
     * Get no image state
     *
     * @return if has image state
     */
    boolean getNoImageState();

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

    /**
     * Set no image state
     *
     * @param b current no image state
     */
    void setNoImageState(boolean b);

    /**
     * Set auto cache state
     *
     * @param b current auto cache state
     */
    void setAutoCacheState(boolean b);

    /**
     * 清除APP数据
     */
    void clearAppData();

}
