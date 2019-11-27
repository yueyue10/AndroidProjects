package com.charlie.mpandroidcharttest.bean;

public class RevenuePieBean {
    private String colorStr;
    private String legendName;
    private String legendPrice;
    private String legendPercent;

    public RevenuePieBean(String colorStr, String legendName, int legendPrice, String legendPercent) {
        this.colorStr = colorStr;
        this.legendName = legendName;
        this.legendPrice = String.valueOf(legendPrice);
        this.legendPercent = legendPercent;
    }

    public RevenuePieBean(String colorStr, String legendName, String legendPrice, String legendPercent) {
        this.colorStr = colorStr;
        this.legendName = legendName;
        this.legendPrice = legendPrice;
        this.legendPercent = legendPercent;
    }

    public String getColorStr() {
        return colorStr == null ? "" : colorStr;
    }

    public void setColorStr(String colorStr) {
        this.colorStr = colorStr;
    }

    public String getLegendName() {
        return legendName == null ? "" : legendName;
    }

    public void setLegendName(String legendName) {
        this.legendName = legendName;
    }

    public String getLegendPrice() {
        return legendPrice == null ? "" : legendPrice;
    }

    public void setLegendPrice(String legendPrice) {
        this.legendPrice = legendPrice;
    }

    public String getLegendPercent() {
        return legendPercent == null ? "" : legendPercent;
    }

    public void setLegendPercent(String legendPercent) {
        this.legendPercent = legendPercent;
    }
}
