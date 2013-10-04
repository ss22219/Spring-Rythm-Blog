package com.mvc.model;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-4
 * Time: 上午7:23
 * To change this template use File | Settings | File Templates.
 */
public class Setting {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Setting setting = (Setting) o;

        if (key != null ? !key.equals(setting.key) : setting.key != null) return false;
        if (value != null ? !value.equals(setting.value) : setting.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
