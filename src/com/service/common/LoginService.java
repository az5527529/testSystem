package com.service.common;

import com.entity.LoginUser;

/**
 * Created by victor on 2018/3/27.
 */
public interface LoginService extends BaseService<LoginUser> {
    public LoginUser login(String userName,String password);
}
