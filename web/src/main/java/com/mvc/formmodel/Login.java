package com.mvc.formmodel;


import javax.validation.constraints.Size;

public class Login {
    @Size(min=1,max = 100,message = "用户名不能为空。")
    private String userName;

    @Size(min = 1,message = "密码不能为空。")
    private String password;

    private String rememberMe;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getUserName() {
        return userName;

    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
