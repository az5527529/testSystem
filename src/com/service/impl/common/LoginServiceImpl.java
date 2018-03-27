package com.service.impl.common;

import com.entity.LoginUser;
import com.service.common.LoginService;
import com.util.CollectionUtil;
import com.util.SessionManager;
import com.util.WebUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by victor on 2018/3/27.
 */
@Service("loginService")
public class LoginServiceImpl extends BaseServiceImpl<LoginUser> implements LoginService {
    @Override
    public LoginUser login(String userName, String password) {
        LoginUser user = new LoginUser();
        String hql = "from LoginUser where userName=:userName";
        List<LoginUser> list = super.getSession().createQuery(hql).setString("userName",userName).list();
        if(CollectionUtil.isEmptyCollection(list)){
            user.setErrorMsg("该用户不存在!");
            return user;
        }
        LoginUser sysUser = list.get(0);
        if(!sysUser.getPassword().equals(WebUtil.md5(password))){
            user.setErrorMsg("密码错误!");
            return user;
        }
        sysUser.setErrorMsg("");
        SessionManager.setLoginUser(sysUser);
        return sysUser;
    }
}
