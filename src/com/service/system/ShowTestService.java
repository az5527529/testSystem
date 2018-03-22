package com.service.system;

import com.entity.UserInfo;
import com.service.common.BaseService;
import com.vo.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by victor on 2018/3/2.
 */
public interface ShowTestService extends BaseService<UserInfo> {
    //获取测试总体情况数据
    public Page showGeneralTest(Map<String,String> condition);
}
