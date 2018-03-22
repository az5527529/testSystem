package com.service.system;

import com.entity.ChoiceQuestion;
import com.service.common.BaseService;

/**
 * Created by victor on 2018/2/28.
 */
public interface ChoiceQuestionService extends BaseService<ChoiceQuestion>{
    //新增编辑方法
    public ChoiceQuestion saveOrUpdateEntity(ChoiceQuestion entity);
}
