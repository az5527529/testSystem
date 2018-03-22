package com.service.impl.system;

import com.entity.JudgeQuestion;
import com.service.impl.common.BaseServiceImpl;
import com.service.system.JudgeQuestionService;
import org.springframework.stereotype.Service;

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
}
