package com.service.system;

import com.entity.UserInfo;
import com.exception.MessageException;
import com.service.common.BaseService;
import com.service.common.Reportable;
import com.vo.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by victor on 2018/3/2.
 */
public interface UserInfoService extends BaseService<UserInfo>,Reportable{

    public UserInfo saveOrUpdateEntity(UserInfo entity);

    public void deleteUserInfoById(long id);

    public List loadTest();

    public UserInfo loadUserInfoByOpenid(String openid,String activityid) throws MessageException;

    public boolean isHaveTest(String openid,String activityId) throws MessageException;
}
