package com.charlie.mpandroidcharttest;

import android.app.Activity;

public class MainBean {
    private String name;
    private Class<? extends Activity> activityClass;

    public MainBean(String name, Class<? extends Activity> activityClass) {
        this.name = name;
        this.activityClass = activityClass;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends Activity> getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Class<? extends Activity> activityClass) {
        this.activityClass = activityClass;
    }
}
