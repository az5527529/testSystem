package com.service.system;

import com.entity.ChoiceQuestion;
import com.service.common.BaseService;
import com.service.common.Reportable;

/**
 * Created by victor on 2018/2/28.
 */
public interface ChoiceQuestionService extends BaseService<ChoiceQuestion>,Reportable{
    //新增编辑方法
    public ChoiceQuestion saveOrUpdateEntity(ChoiceQuestion entity);
}
