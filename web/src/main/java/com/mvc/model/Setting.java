package com.mvc.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Setting {
    private String key;

    @javax.persistence.Column(name = "key", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
    @Id
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    private String value;

    @javax.persistence.Column(name = "value", nullable = false, insertable = true, updatable = true, length = 65535, precision = 0)
    @Basic
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
