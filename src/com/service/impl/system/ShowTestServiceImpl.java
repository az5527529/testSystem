package com.service.impl.system;

import com.entity.UserInfo;
import com.service.impl.common.BaseServiceImpl;
import com.service.system.ShowTestService;
import com.util.CollectionUtil;
import com.util.StringUtil;
import com.vo.Page;

import org.hibernate.criterion.CriteriaSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by victor on 2018/3/2.
 */
@Service("showTestService")
public class ShowTestServiceImpl extends BaseServiceImpl<UserInfo> implements ShowTestService {
    private Logger logger = LoggerFactory.getLogger("ShowTestServiceImpl");
    @Override
    public Page showGeneralTest(Map<String, String> condition) {
        //条件
        StringBuffer conditionSql = new StringBuffer();
        //测试开始日期
        String testTimeBegin = condition.get("testTimeBegin");
        if(!StringUtil.isEmptyString(testTimeBegin)){
            conditionSql.append(" and test_time>='" + testTimeBegin + " 00:00:00'");
        }
        //测试结束日期
        String testTimeEnd = condition.get("testTimeEnd");
        if(!StringUtil.isEmptyString(testTimeEnd)){
            conditionSql.append(" and test_time<='" + testTimeEnd + " 23:59:59'");
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select count(1) totalNum,ifnull(max(score),0) maxScore, round(ifnull(avg(score),0),2) average \n");
        sql.append("from user_info where 1=1 ");
        sql.append(conditionSql);
        List list = getSession().createSQLQuery(sql.toString()).setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
        Page page = new Page();
        page.setRows(list);
        page.setTotal(CollectionUtil.isEmptyCollection(list) ? 0 : list.size());
        return page;
    }
}
