package com.service.impl.system;

import com.entity.ChoiceQuestion;
import com.service.impl.common.BaseServiceImpl;
import com.service.system.ChoiceQuestionService;
import org.springframework.stereotype.Service;

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
}
