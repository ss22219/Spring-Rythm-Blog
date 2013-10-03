package com.mvc.util;

import org.rythmengine.template.JavaTagBase;
import org.springframework.stereotype.Component;

public class SubStringTag extends JavaTagBase {
    @Override
    protected void call(__ParameterList parameters, __Body body) {
        if (parameters.size() == 1) {
            String str = parameters.getDefault().toString();
            if (str.length() > 50) {
                str = str.substring(50) + "...";
            }
            this.p(str);
        } else if (parameters.size() == 2) {
            String str = parameters.get(0).value.toString();
            int length = Integer.parseInt(parameters.get(1).value.toString());

            if (str.length() > length) {
                str = str.substring(length) + "...";
            }
            this.p(str);
        } else if (parameters.size() == 3) {
            String str = parameters.get(0).value.toString();
            int length = Integer.parseInt(parameters.get(1).value.toString());
            String words = parameters.get(2).value.toString();

            if (str.length() > length) {
                str = str.substring(length) + words;
            }
            this.p(str);
        }
    }

    @Override
    public String __getName() {
        return "subString";
    }
}
