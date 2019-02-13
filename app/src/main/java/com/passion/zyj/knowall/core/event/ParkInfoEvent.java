package com.passion.zyj.knowall.core.event;

/**
 * Created by zhaoyuejun on 2018/10/25.
 */

public class ParkInfoEvent {
    private boolean isUpdated;

    public ParkInfoEvent(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }
}
