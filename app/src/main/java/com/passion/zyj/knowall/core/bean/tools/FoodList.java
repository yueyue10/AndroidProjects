package com.passion.zyj.knowall.core.bean.tools;

import java.util.ArrayList;
import java.util.List;

public class FoodList {

    private List<FoodDetailBean> data;
    private String totalNum;
    private String pn;
    private String rn;

    public List<FoodDetailBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<FoodDetailBean> data) {
        this.data = data;
    }

    public String getTotalNum() {
        return totalNum == null ? "" : totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getPn() {
        return pn == null ? "" : pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getRn() {
        return rn == null ? "" : rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }
}
