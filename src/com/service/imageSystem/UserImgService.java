package com.service.imageSystem;

import com.entity.UserImg;
import com.exception.MessageException;
import com.service.common.BaseService;

/**
 * Created by victor on 2018/3/13.
 */
public interface UserImgService extends BaseService<UserImg> {
    public UserImg saveOrUpdateUserImg(UserImg entity);

    public UserImg loadUserImgByOpenid(String openid,String activityId) throws MessageException;

    public boolean isExist(String openid,String activityId) throws MessageException;

    public String getImgeUrlById(long id);
}
