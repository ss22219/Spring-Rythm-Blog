package com.mvc.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-5
 * Time: 上午7:56
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class User {
    private int userId;

    @javax.persistence.Column(name = "user_id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private String userName;

    @javax.persistence.Column(name = "user_name", nullable = false, insertable = true, updatable = true, length = 50, precision = 0)
    @Basic
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String niceName;

    @javax.persistence.Column(name = "nice_name", nullable = false, insertable = true, updatable = true, length = 50, precision = 0)
    @Basic
    public String getNiceName() {
        return niceName;
    }

    public void setNiceName(String niceName) {
        this.niceName = niceName;
    }

    private String password;

    @javax.persistence.Column(name = "password", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String picture;

    @javax.persistence.Column(name = "picture", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    private Date registerDate;

    @javax.persistence.Column(name = "register_date", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    private int status;

    @javax.persistence.Column(name = "status", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private String email;

    @javax.persistence.Column(name = "email", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private int role;

    @javax.persistence.Column(name = "role", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (role != user.role) return false;
        if (status != user.status) return false;
        if (userId != user.userId) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (niceName != null ? !niceName.equals(user.niceName) : user.niceName != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (picture != null ? !picture.equals(user.picture) : user.picture != null) return false;
        if (registerDate != null ? !registerDate.equals(user.registerDate) : user.registerDate != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (niceName != null ? niceName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (registerDate != null ? registerDate.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + role;
        return result;
    }
}
