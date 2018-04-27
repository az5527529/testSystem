package com.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by victor on 2018/3/22.
 */
@Entity
@javax.persistence.Table(name = "login_user", schema = "test", catalog = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class LoginUser implements Serializable{
    private Long loginUserId;

    @Id
    @javax.persistence.Column(name = "login_user_id", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    public Long getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(Long loginUserId) {
        this.loginUserId = loginUserId;
    }

    private String userName;

    @Basic
    @javax.persistence.Column(name = "user_name", nullable = false, length = 64)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String password;

    @Basic
    @javax.persistence.Column(name = "password", nullable = false, length = 64)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginUser loginUser = (LoginUser) o;

        if (loginUserId != loginUser.loginUserId) return false;
        if (userName != null ? !userName.equals(loginUser.userName) : loginUser.userName != null) return false;
        if (password != null ? !password.equals(loginUser.password) : loginUser.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    private String errorMsg;
    @Transient
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
