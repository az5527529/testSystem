package com.service.impl.system;

import com.entity.ChoiceQuestion;
import com.service.impl.common.BaseServiceImpl;
import com.service.system.ChoiceQuestionService;
import com.util.StringUtil;
import com.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by victor on 2018/3/1.
 */
@Service("choiceQuestionService")
public class ChoiceQuestionServiceImpl extends BaseServiceImpl<ChoiceQuestion> implements ChoiceQuestionService {
    @Override
    public ChoiceQuestion saveOrUpdateEntity(ChoiceQuestion entity) {
        if(entity.getChoiceQuestionId() > 0){
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
        String rowsStr = paramMap.get("rowsStr");
        String pageStr= paramMap.get("pageStr");
        int pageSize = 10;
        int page = 1;
        if(!StringUtil.isEmptyString(rowsStr)&&!StringUtil.isEmptyString(pageStr)){
            pageSize = Integer.parseInt(rowsStr);
            page = Integer.parseInt(pageStr);
        }
        String sort = paramMap.get("sort");
        String order = paramMap.get("order");
        List<ChoiceQuestion> list = super.query(whereCondition.toString(),(page-1)*pageSize,pageSize,sort,order);
        Page pageVo = new Page();
        pageVo.setRows(list);
        pageVo.setTotal(super.getCount(whereCondition.toString()));
        return pageVo;
    }
}
