package com.service.impl.system;

import com.entity.TestInfo;
import com.entity.UserInfo;
import com.exception.MessageException;
import com.service.impl.common.BaseServiceImpl;
import com.service.system.UserInfoService;
import com.util.CollectionUtil;
import com.util.StringUtil;
import com.vo.Page;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by victor on 2018/3/2.
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {

    @Override
    public UserInfo saveOrUpdateEntity(UserInfo entity) {
        if(entity.getUserInfoId() > 0){
            return this.update(entity);
        }else{
            return this.save(entity);
        }
    }

    @Override
    public void deleteUserInfoById(long id) {
        //删除用户信息
        String deleteUserInfo = "delete from user_info where user_info_id=:id ";
        //删除作答详细信息
        String deleteDetail = "delete from answer_detail where user_info_id=:id";
        int result = getSession().createSQLQuery(deleteDetail).setLong("id",id).executeUpdate();
        if(result > 0){
            getSession().createSQLQuery(deleteUserInfo).setLong("id",id).executeUpdate();
        }
    }

    /**
     * 获取试题
     * @return
     */
    @Override
    public List loadTest() {
        List list = new ArrayList();//结果集
        List choiceList = null;//选择题
        List judgeList = null;//判断题
        StringBuffer sb = new StringBuffer();

        int choiceNum = 0;//选择题数目
        int judgeNum = 0;//判断题数目
        //获取试卷信息
        List<TestInfo> testInfoList = getSession().createQuery("from TestInfo").list();
        TestInfo testInfo = null;
        if(!CollectionUtil.isEmptyCollection(testInfoList)){
            testInfo = testInfoList.get(0);
            choiceNum = testInfo.getNumberOfSelect();
            judgeNum = testInfo.getNumberOfJudge();
        }

        //获取选择题
        sb.append("select t1.choice_question_id questionId,1 type,t1.content content \n");
        sb.append(",t1.keya keya,t1.keyb keyb,t1.keyc keyc,t1.keyd keyd\n");
        sb.append("from choice_question t1\n");
        sb.append("order by rand() \n");
        choiceList = getSession().createSQLQuery(sb.toString()).setMaxResults(choiceNum).setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();

        //获取判断题
        sb = new StringBuffer();
        sb.append("select t1.judge_question_id questionId,2 type,t1.content content \n");
        sb.append("from judge_question t1\n");
        sb.append("order by rand() \n");
        judgeList = getSession().createSQLQuery(sb.toString()).setMaxResults(judgeNum).setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();

        list.addAll(choiceList);
        list.addAll(judgeList);
        return list;
    }

    @Override
    public UserInfo loadUserInfoByOpenid(String openid) throws MessageException {
        if(StringUtil.isEmptyString(openid)){
            throw new MessageException().setErrorMsg("请进行微信认证");
        }
        String hql = "from UserInfo where openid=:id";
        List<UserInfo> list = getSession().createQuery(hql).setString("id",openid).list();
        if(CollectionUtil.isEmptyCollection(list)){
            throw new MessageException().setErrorMsg("该用户还没考试");
        }

        return list.get(0);
    }

    /**
     * 判断是否已考试
     * @param openid
     * @return
     */
    @Override
    public boolean isHaveTest(String openid,String activityId) throws MessageException {

        if(StringUtil.isEmptyString(openid)){
            throw new MessageException().setErrorMsg("请进行微信认证");
        }
        String isExist = "select 1 from user_info where openid=:id and activity_id=:activityId";
        List list = getSession().createSQLQuery(isExist).setString("id",openid).setString("activityId",activityId).list();
        if(CollectionUtil.isEmptyCollection(list)){
            return false;
        }
        return true;
    }

    @Override
    public Map<String, Object> getParamMap() {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("xlsName", "测试情况表.xls");
        paramMap.put("queryHeaders", "姓名,电话,分数,提交时间");
        paramMap.put("queryKeys", "userName,telephone,score,testTime");
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
        //姓名
        String userName = condition.get("userName");
        if(!StringUtil.isEmptyString(userName)){
            conditionSql.append(" and user_Name like '%" + userName + "%'");
        }
        //电话
        String telephone = condition.get("telephone");
        if(!StringUtil.isEmptyString(telephone)){
            conditionSql.append(" and telephone like '%" + telephone + "%' ");
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select user_info_id userInfoId,user_name userName,telephone telephone,cast(score as char) score,test_time testTime \n");
        sql.append("from user_info where 1=1 ");
        sql.append(conditionSql);

        int pageSize = StringUtil.isEmptyString(condition.get("pageSize"))? 0 : Integer.parseInt(condition.get("pageSize"));
        int pageNum = StringUtil.isEmptyString(condition.get("pageNum"))? 0 : Integer.parseInt(condition.get("pageNum"));
        String sort = condition.get("sort");
        String order = condition.get("order");
        if(!StringUtil.isEmptyString(sort)){
            sql.append(" order by "+sort+" "+order);
        }

        List list = getSession().createSQLQuery(sql.toString()).setFirstResult((pageNum-1)*pageSize).setMaxResults(pageSize).setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
        Page page = new Page();
        page.setRows(list);
        page.setTotal(super.getCount(conditionSql.toString()));
        return page;
    }
}
