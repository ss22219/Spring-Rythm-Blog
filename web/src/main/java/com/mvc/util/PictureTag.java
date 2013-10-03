package com.mvc.util;

import org.rythmengine.template.ITag;
import org.rythmengine.template.JavaTagBase;

public class PictureTag extends JavaTagBase {
    @Override
    protected void call(__ParameterList parameters, __Body body) {
        Object parameter = parameters.getDefault();
        if (parameter == null) {
            this.p("/Content/Include/images/picture.png");
        }else{
            if(parameter.toString().trim().length() != 0){
                this.p(parameter.toString());
            }
            else{
                this.p("/Content/Include/images/picture.png");
            }
        }
    }

    @Override
    public String __getName() {
        return "picture";
    }
}
