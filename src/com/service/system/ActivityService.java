package com.service.system;

import com.entity.Activity;
import com.service.common.BaseService;

import java.util.List;

/**
 * Created by victor on 2018/3/23.
 */
public interface ActivityService extends BaseService<Activity> {

    public Activity saveOrUpdateActivity(Activity entity);

    public String getImgeUrlById(long id);

    public Activity getActivity(int activityType);

    /**
     * 根据类型获取所有活动
     * @param activityType
     * @return
     */
    public List<Activity> getAllActivityByType(int activityType);
}
