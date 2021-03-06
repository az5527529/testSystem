package com.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by victor on 2018/2/28.
 */
@Entity
@Table(name = "user_info", schema = "test", catalog = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserInfo implements Serializable{
    private Long userInfoId;
    private String userName;
    private String telephone;
    private Double score;
    private String openid;
    private String testTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_info_id", nullable = false)
    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 64)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "telephone", nullable = false, length = 16)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "score", nullable = false, precision = 0)
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Basic
    @Column(name = "openid", nullable = false, length = 64)
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (userInfoId != userInfo.userInfoId) return false;
        if (Double.compare(userInfo.score, score) != 0) return false;
        if (userName != null ? !userName.equals(userInfo.userName) : userInfo.userName != null) return false;
        if (telephone != null ? !telephone.equals(userInfo.telephone) : userInfo.telephone != null) return false;
        if (openid != null ? !openid.equals(userInfo.openid) : userInfo.openid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (userInfoId ^ (userInfoId >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        temp = Double.doubleToLongBits(score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (openid != null ? openid.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "test_time", nullable = false, length = 20)
    public String getTestTime() {
        return testTime;
    }


    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }    private long activityId;

    @Basic
    @Column(name = "activity_id", nullable = false)
    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }
}
