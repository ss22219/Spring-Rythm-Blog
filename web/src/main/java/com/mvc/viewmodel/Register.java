package com.mvc.viewmodel;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class Register {
    @Length(min=4,max = 15,message = "用户名最少4个字符，最多15个字符。")
    private String userName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min=6,max = 20,message = "密码最少6个字符，最多20个字符。")
    private String password;

    private String rePassword;
    @Length(min = 4,max = 100,message = "邮箱不能为空。")
    @Email(message = "邮箱格式不正确。")
    private String email;
}
