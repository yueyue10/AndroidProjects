package com.passion.zyj.knowall.core.bean.tools;

import java.util.List;

public class MenuBean {

    /**
     * parentId : 10001
     * name : 菜式菜品
     * list : [{"id":"1","name":"家常菜","parentId":"10001"},{"id":"2","name":"快手菜","parentId":"10001"},{"id":"3","name":"创意菜","parentId":"10001"},{"id":"4","name":"素菜","parentId":"10001"},{"id":"5","name":"凉菜","parentId":"10001"},{"id":"6","name":"烘焙","parentId":"10001"},{"id":"7","name":"面食","parentId":"10001"},{"id":"8","name":"汤","parentId":"10001"},{"id":"9","name":"自制调味料","parentId":"10001"}]
     */

    private String parentId;
    private String name;
    private List<FoodBean> list;
    private String zyj_status;//selected(选中)/unselected(未选中)

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FoodBean> getList() {
        return list;
    }

    public void setList(List<FoodBean> list) {
        this.list = list;
    }

    public String getZyj_status() {
        return zyj_status == null ? "" : zyj_status;
    }

    public void setZyj_status(String zyj_status) {
        this.zyj_status = zyj_status;
    }

}
