package com.weidingqiang.skyworthui.tianjin.mainsoucesetting;

/**
 * Created by Administrator on 2017/10/17.
 */

public class SourceBean {

    private int iconid;
    private int focusIconid;
    private String key;
    private String source;
    private boolean isChoosed;
    private boolean isCurrentChoos;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getFocusIconid() {
        return focusIconid;
    }

    public void setFocusIconid(int focusIconid) {
        this.focusIconid = focusIconid;
    }

    public boolean getCurrentChoos() {
        return isCurrentChoos;
    }

    public void setCurrentChoos(boolean currentChoos) {
        isCurrentChoos = currentChoos;
    }

    public int getIconid() {
        return iconid;
    }

    public void setIconid(int iconid) {
        this.iconid = iconid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean getChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    @Override
    public String toString() {
        return "SourceBean{" +
                "iconid=" + iconid +
                ", source='" + source + '\'' +
                ", isChoosed=" + isChoosed +
                '}';
    }
}
