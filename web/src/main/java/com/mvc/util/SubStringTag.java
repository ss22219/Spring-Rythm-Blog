package com.mvc.util;

import org.rythmengine.template.JavaTagBase;
import org.springframework.stereotype.Component;

public class SubStringTag extends JavaTagBase {
    @Override
    protected void call(__ParameterList parameters, __Body body) {
        String str = parameters.getDefault().toString();
        if (str.length() > 50)
        {
            str = str.substring(50) + "...";
        }
        this.p(str);
    }

    @Override
    public String __getName() {
        return "subString";
    }
}
