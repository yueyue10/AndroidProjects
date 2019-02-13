package com.passion.zyj.knowall.core.bean;

/**
 * Created by zhaoyuejun on 2018/12/22.
 */

public class CreateNoteResponse {

    /**
     * id : 66
     * status : 1
     */

    private int id;
    private int status;//状态 (0 已发布,1审核中 2驳回)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
