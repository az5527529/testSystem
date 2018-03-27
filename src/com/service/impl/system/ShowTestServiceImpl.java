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

import java.math.BigDecimal;
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
    public Map<String, Object> getParamMap() {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("xlsName", "测试总体情况表.xls");
        paramMap.put("queryHeaders", "答题总人数,最高分,平均分");
        paramMap.put("queryKeys", "totalNum,maxScore,average");
        return paramMap;
    }

    @Override
    public Page getData(Map<String, String> condition) {
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
        sql.append("select cast(count(1) as char) totalNum,cast(ifnull(max(score),0) as char) maxScore, cast(round(ifnull(avg(score),0),2) as char) average \n");
        sql.append("from user_info where 1=1 ");
        sql.append(conditionSql);
        List<Map<String,String>> list = getSession().createSQLQuery(sql.toString()).setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
        Page page = new Page();
        page.setRows(list);
        page.setTotal(CollectionUtil.isEmptyCollection(list) ? 0 : list.size());
        //分数去除多余的0
        for(Map<String,String> record : list){
            String average = record.get("average");
            BigDecimal averageDecimal = new BigDecimal(average).stripTrailingZeros();
            record.put("average",averageDecimal.doubleValue() == 0  ? "0" : averageDecimal.toPlainString());
        }
        return page;
    }
}
