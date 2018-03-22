package com.service.imageSystem;

import com.entity.UserImg;
import com.exception.MessageException;
import com.service.common.BaseService;

/**
 * Created by victor on 2018/3/13.
 */
public interface UserImgService extends BaseService<UserImg> {
    public UserImg saveOrUpdateUserImg(UserImg entity);

    public UserImg loadUserImgByOpenid(String openid) throws MessageException;

    public boolean isExist(String openid) throws MessageException;
}
