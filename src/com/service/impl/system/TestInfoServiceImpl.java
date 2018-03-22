package com.service.impl.system;

import com.entity.TestInfo;
import com.service.impl.common.BaseServiceImpl;
import com.service.system.TestInfoService;
import com.util.CollectionUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by victor on 2018/3/2.
 */
@Service("testInfoService")
public class TestInfoServiceImpl extends BaseServiceImpl<TestInfo> implements TestInfoService{
    @Override
    public TestInfo saveOrUpdateEntity(TestInfo entity) {
        if(entity.getTestInfoId() > 0){
            return this.update(entity);
        }else{
            return this.save(entity);
        }
    }

    @Override
    public TestInfo loadTestInfo() {
        String hql = "from TestInfo";
        List<TestInfo> list = getSession().createQuery(hql).list();
        if(!CollectionUtil.isEmptyCollection(list)){
            return list.get(0);
        }
        return null;
    }
}
