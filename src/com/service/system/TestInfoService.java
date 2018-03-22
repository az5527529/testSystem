package com.service.system;

import com.entity.TestInfo;
import com.service.common.BaseService;

/**
 * Created by victor on 2018/3/2.
 */
public interface TestInfoService extends BaseService<TestInfo>{
    public TestInfo saveOrUpdateEntity(TestInfo entity);

    public TestInfo loadTestInfo();
}
