package com.service.impl.system;

import com.entity.JudgeQuestion;
import com.service.impl.common.BaseServiceImpl;
import com.service.system.JudgeQuestionService;
import com.util.StringUtil;
import com.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by victor on 2018/3/1.
 */
@Service("judgeQuestionService")
public class JudgeQuestionServiceImpl extends BaseServiceImpl<JudgeQuestion> implements JudgeQuestionService {
    @Override
    public JudgeQuestion saveOrUpdateEntity(JudgeQuestion entity) {
        if(entity.getJudgeQuestionId() > 0){
            return this.update(entity);
        }else{
            return this.save(entity);
        }
    }

    @Override
    public Map<String, Object> getParamMap() {
        return null;
    }

    @Override
    public Page getData(Map<String,String> paramMap) {
        StringBuffer whereCondition = new StringBuffer();
        String questionNo = paramMap.get("questionNo");
        String content = paramMap.get("content");
        if(!StringUtil.isEmptyString(questionNo)){
            whereCondition.append(" and questionNo='" + questionNo + "'");
        }
        if(!StringUtil.isEmptyString(content)){
            whereCondition.append(" and content like '%" + content + "%'");
        }
        String rowsStr = paramMap.get("rows");
        String pageStr=paramMap.get("page");
        int pageSize = 10;
        int page = 1;
        if(!StringUtil.isEmptyString(rowsStr)&&!StringUtil.isEmptyString(pageStr)){
            pageSize = Integer.parseInt(rowsStr);
            page = Integer.parseInt(pageStr);
        }
        String sort = paramMap.get("sort");

        String order = paramMap.get("order");
        List<JudgeQuestion> list = super.query(whereCondition.toString(),(page-1)*pageSize,pageSize,sort,order);
        Page o = new Page();
        o.setRows(list);
        o.setTotal(super.getCount(whereCondition.toString()));
        return o;
    }
}
