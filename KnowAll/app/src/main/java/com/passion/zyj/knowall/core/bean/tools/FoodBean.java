package com.passion.zyj.knowall.core.bean.tools;

public class FoodBean {
    /**
     * id : 1
     * name : 家常菜
     * parentId : 10001
     */

    private String id;
    private String name;
    private String parentId;
    private String zyj_status;//selected(选中)/unselected(未选中)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getZyj_status() {
        return zyj_status == null ? "" : zyj_status;
    }

    public void setZyj_status(String zyj_status) {
        this.zyj_status = zyj_status;
    }
}