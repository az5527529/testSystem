package com.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by victor on 2018/3/22.
 */
@Entity
@javax.persistence.Table(name = "activity", schema = "test", catalog = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Activity {
    private long activityId;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @javax.persistence.Column(name = "activity_id", nullable = false)
    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    private String content;

    @Basic
    @javax.persistence.Column(name = "content", nullable = false, length = 256)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String startTime;

    @Basic
    @javax.persistence.Column(name = "start_time", nullable = false, length = 20)
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    private String endTime;

    @Basic
    @javax.persistence.Column(name = "end_time", nullable = false, length = 20)
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    private String backgroundUrl;

    @Basic
    @javax.persistence.Column(name = "background_url", nullable = false, length = 128)
    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    private boolean isActive;

    @Basic
    @javax.persistence.Column(name = "is_active", nullable = false)
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    private int activityType;

    @Basic
    @javax.persistence.Column(name = "activity_type", nullable = false)
    public int getActivityType() {
        return activityType;
    }

    public void setActivityType(int activityType) {
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
}
