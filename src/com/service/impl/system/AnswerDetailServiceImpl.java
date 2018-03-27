package com.service.impl.system;

import com.entity.Activity;
import com.entity.AnswerDetail;
import com.entity.TestInfo;
import com.entity.UserInfo;
import com.exception.MessageException;
import com.service.impl.common.BaseServiceImpl;
import com.service.system.AnswerDetailService;
import com.util.CollectionUtil;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by victor on 2018/3/5.
 */
@Service("answerDetailService")
public class AnswerDetailServiceImpl extends BaseServiceImpl<AnswerDetail> implements AnswerDetailService {
    @Override
    public List loadDetailByUserId(String userId) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ad.answer_detail_id answerDetailId,\n");
        sb.append("case when ad.question_type=1 then cq.content else jq.content end content, \n");
        sb.append("case when ad.question_type=1 then cq.keya else '' end keya, \n");
        sb.append("case when ad.question_type=1 then cq.keyb else '' end keyb, \n");
        sb.append("case when ad.question_type=1 then cq.keyc else '' end keyc, \n");
        sb.append("case when ad.question_type=1 then cq.keyd else '' end keyd,\n");
        sb.append("case when ad.question_type=2 and jq.answer=1 then '正确' when ad.question_type=2 and jq.answer=0 then '错误' else cq.answer  end answer,\n");
        sb.append("case when ad.question_type=2 and ad.user_answer=1 then '正确' when ad.question_type=2 and ad.user_answer=0 then '错误' else ad.user_answer end userAnswer,ad.result result\n");
        sb.append(",ad.question_type type \n");
        sb.append("from answer_detail ad\n");
        sb.append("left join choice_question cq on cq.choice_question_id=ad.question_id and ad.question_type=1\n");
        sb.append("left  join judge_question jq on jq.judge_question_id=ad.question_id and ad.question_type=2\n");
        sb.append("where 1=1 and ad.user_info_id=" + userId);
        List list = getSession().createSQLQuery(sb.toString()).setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
        return list;
    }

    @Override
    public void saveTestDetail(UserInfo userInfo, List<AnswerDetail> list) throws MessageException {
        //校验活动当前状态
        String searchActivity = "from Activity where activityId=:id";
        List<Activity> activityList = super.getSession().createQuery(searchActivity).setLong("id",userInfo.getActivityId()).list();
        if(CollectionUtil.isEmptyCollection(activityList)){
            throw new MessageException().setErrorMsg("活动不存在");
        }else {
            Activity activity = activityList.get(0);
            if(!activity.getIsActive()){
                throw new MessageException().setErrorMsg("活动已停止");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(new Date());
            if(currentTime.compareTo(activity.getStartTime()) < 0){
                throw new MessageException().setErrorMsg("活动还没开始");
            }
            if(currentTime.compareTo(activity.getEndTime()) > 0){
                throw new MessageException().setErrorMsg("活动已结束");
            }
        }

        //校验用户是否已作答
        String searchUser = "select 1 from user_info where openid=:id and activity_id=:activityId";
        List userList = getSession().createSQLQuery(searchUser).setString("id",userInfo.getOpenid()).setLong("activityId",userInfo.getActivityId()).list();
        if(!CollectionUtil.isEmptyCollection(userList)){
            throw new MessageException().setErrorMsg("您已作答过,请勿重复提交");
        }
        getSession().save(userInfo);

        long userInfoId = userInfo.getUserInfoId();//用户id
        //获取试卷信息
        List<TestInfo> testInfoList = getSession().createQuery("from TestInfo").list();
        TestInfo testInfo = null;
        double valueOfJudge = 0;//判断题分值
        double valueOfSelect = 0;//选择题分值
        if(!CollectionUtil.isEmptyCollection(testInfoList)){
            testInfo = testInfoList.get(0);
            valueOfSelect = testInfo.getValueOfSelect();
            valueOfJudge = testInfo.getValueOfJudge();
        }
        double allScore = 0;//总分
        String searchChoice = "select answer from choice_question where choice_question_id=:id";
        String searchJudge = "select answer from judge_question where judge_question_id=:id";
        List resultList = null;

        //用来保存答题详情
        String inserDetail = "insert into answer_detail(question_id,question_type,user_answer,result,user_info_id)" +
                " values(:questionId,:questionType,:userAnswer,:result,:userInfoId)";
        Map paramMap = new HashMap();
        paramMap.put("userInfoId",userInfoId);
        for(AnswerDetail entity : list){
//            entity.setUserInfoId(userInfoId);
            int type = entity.getQuestionType();//问题类型1代表选择题，2代表判断题
            boolean result = false;//是否正确
            String answer = "";
            double score = 0;//分值
            if(type == 1){
                resultList = getSession().createSQLQuery(searchChoice).setLong("id",entity.getQuestionId()).list();
                score = valueOfSelect;
            }else if(type == 2){
                resultList = getSession().createSQLQuery(searchJudge).setLong("id",entity.getQuestionId()).list();
                score = valueOfJudge;
            }
            if(!CollectionUtil.isEmptyCollection(resultList)){
                answer = resultList.get(0).toString();
                if(answer.equals(entity.getUserAnswer())){
                    result = true;
                    allScore+=score;//回答正确则加分
                }
            }
//            entity.setResult(result);
            paramMap.put("questionType",type);
            paramMap.put("result",result);
            paramMap.put("userAnswer",entity.getUserAnswer());
            paramMap.put("questionId",entity.getQuestionId());
            getSession().createSQLQuery(inserDetail).setProperties(paramMap).executeUpdate();
        }
        userInfo.setScore(allScore);//设置分数
        super.getSession().save(userInfo);
    }
}
