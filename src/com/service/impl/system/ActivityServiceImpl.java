package com.service.impl.system;

import com.entity.Activity;
import com.service.impl.common.BaseServiceImpl;
import com.service.system.ActivityService;
import com.util.CollectionUtil;
import com.util.SessionManager;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by victor on 2018/3/23.
 */
@Service("activityService")
public class ActivityServiceImpl extends BaseServiceImpl<Activity> implements ActivityService {
    @Override
    public Activity saveOrUpdateActivity(Activity entity) {
        //如果该活动是启用状态，则将同一阶段属于启用状态的同一类型活动停用
        /*if(entity.getIsActive()){
            String sql = "update activity set is_active=0 where 1=1 and activity_type=:type and activity_id<>:id and is_active=1" +
                    " and start_time<=:endTime and end_time>=:startTime";
            super.getSession().createSQLQuery(sql).setLong("id",entity.getActivityId()).setString("endTime",entity.getEndTime())
                    .setString("startTime",entity.getStartTime()).setInteger("type",entity.getActivityType()).executeUpdate();
        }*/
        if(entity.getActivityId() != null && entity.getActivityId() > 0){
            return this.update(entity);
        }else{
            entity.setCreatedUser(SessionManager.getUserName());
            return this.save(entity);
        }
    }

    /**
     * 通过id获取活动 背景图片url
     * @param id
     * @return
     */
    @Override
    public String getImgeUrlById(long id) {
        String sql = "select background_Url from activity where 1=1 and activity_id=:id";
        List<String> list = this.getSession().createSQLQuery(sql).setLong("id",id).list();
        if(!CollectionUtil.isEmptyCollection(list)){
            return list.get(0);
        }
        return "";
    }

    /**
     * 根据活动类型获取当前已启用的活动
     * @return
     */
    @Override
    public Activity getActivity(int activityType) {
        String hql = "from Activity where 1=1 and activityType=:type and startTime<=:currentTime and endTime>=:currentTime and isActive=true";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new Date());
        List<Activity> list = super.getSession().createQuery(hql).setString("currentTime",currentTime)
                            .setInteger("type",activityType).list();
        if(!CollectionUtil.isEmptyCollection(list)){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Activity> getAllActivityByType(int activityType) {
        String userName = SessionManager.getUserName();
        String hql = "from Activity where activityType=:type and createdUser=:userName";
        List<Activity> list = super.getSession().createQuery(hql).setInteger("type",activityType).setString("userName",userName).list();
        return list;
    }
}
