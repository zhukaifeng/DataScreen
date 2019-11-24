package com.zkf.datascreen.model;

public class LineChartBaseBean {
    private String key;
    private float value;

    public LineChartBaseBean(String key, float value) {
        this.key = key;
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
