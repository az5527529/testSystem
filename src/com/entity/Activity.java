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
@Table(name = "activity", schema = "test", catalog = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Activity implements Serializable{
    private Long activityId;
    private String content;
    private String startTime;
    private String endTime;
    private String backgroundUrl;
    private Boolean isActive;
    private Integer activityType;
    private String activityName;
    private String createdUser;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "activity_id", nullable = false)
    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 256)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "start_time", nullable = false, length = 20)
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time", nullable = false, length = 20)
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "background_url", nullable = false, length = 128)
    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    @Basic
    @Column(name = "is_active", nullable = false)
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    @Basic
    @Column(name = "activity_type", nullable = false)
    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        if (activityId != activity.activityId) return false;
        if (isActive != activity.isActive) return false;
        if (activityType != activity.activityType) return false;
        if (content != null ? !content.equals(activity.content) : activity.content != null) return false;
        if (startTime != null ? !startTime.equals(activity.startTime) : activity.startTime != null) return false;
        if (endTime != null ? !endTime.equals(activity.endTime) : activity.endTime != null) return false;
        if (backgroundUrl != null ? !backgroundUrl.equals(activity.backgroundUrl) : activity.backgroundUrl != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (activityId ^ (activityId >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (backgroundUrl != null ? backgroundUrl.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + activityType;
        return result;
    }

    @Basic
    @Column(name = "activity_name", nullable = false, length = 64)
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    @Basic
    @Column(name = "created_user", nullable = false, length = 64)
    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
}
