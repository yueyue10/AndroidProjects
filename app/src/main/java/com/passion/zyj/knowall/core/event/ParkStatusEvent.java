package com.passion.zyj.knowall.core.event;

/**
 * @author quchao
 * @date 2018/3/16
 */

public class ParkStatusEvent {

    private boolean isUpdated;

    public ParkStatusEvent(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }
}
