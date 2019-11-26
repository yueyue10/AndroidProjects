package com.passion.zyj.knowall.core.bean.main;

/**
 * Created by zhaoyuejun on 2018/1/9.
 */

public class MainTabBean {

    private String name;
    private int normalIcon;
    private int selectedIcon;
    private String zyj_status;//selected(选中)/unselected(未选中)

    public MainTabBean(String name, int normalIcon, int selectedIcon) {
        this.name = name;
        this.normalIcon = normalIcon;
        this.selectedIcon = selectedIcon;
        this.zyj_status = "unselected";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZyj_status() {
        return zyj_status;
    }

    public void setZyj_status(String zyj_status) {
        this.zyj_status = zyj_status;
    }

    public int getNormalIcon() {
        return normalIcon;
    }

    public void setNormalIcon(int normalIcon) {
        this.normalIcon = normalIcon;
    }

    public int getSelectedIcon() {
        return selectedIcon;
    }

    public void setSelectedIcon(int selectedIcon) {
        this.selectedIcon = selectedIcon;
    }
}
