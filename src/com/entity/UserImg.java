package com.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by victor on 2018/3/13.
 */
@Entity
@Table(name = "user_img", schema = "test", catalog = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserImg implements Serializable{
    private Long userImgId;
    private String userName;
    private String telephone;
    private String imgUrl;
    private String imgName;
    private String openid;
    private String uploadTime;
    private Long activityId;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_img_id", nullable = false)
    public Long getUserImgId() {
        return userImgId;
    }

    public void setUserImgId(Long userImgId) {
        this.userImgId = userImgId;
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
    @Column(name = "img_url", nullable = false, length = 128)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Basic
    @Column(name = "img_name", nullable = false, length = 64)
    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserImg userImg = (UserImg) o;

        if (userImgId != userImg.userImgId) return false;
        if (userName != null ? !userName.equals(userImg.userName) : userImg.userName != null) return false;
        if (telephone != null ? !telephone.equals(userImg.telephone) : userImg.telephone != null) return false;
        if (imgUrl != null ? !imgUrl.equals(userImg.imgUrl) : userImg.imgUrl != null) return false;
        if (imgName != null ? !imgName.equals(userImg.imgName) : userImg.imgName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userImgId ^ (userImgId >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (imgUrl != null ? imgUrl.hashCode() : 0);
        result = 31 * result + (imgName != null ? imgName.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "openid", nullable = false, length = 64)
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Basic
    @Column(name = "upload_time", nullable = false, length = 64)
    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Basic
    @Column(name = "activity_id", nullable = false)
    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
