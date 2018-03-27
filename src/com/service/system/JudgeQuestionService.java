package com.service.system;

import com.entity.JudgeQuestion;
import com.service.common.BaseService;
import com.service.common.Reportable;

/**
 * Created by victor on 2018/3/1.
 */
public interface JudgeQuestionService extends BaseService<JudgeQuestion>,Reportable {
    public JudgeQuestion saveOrUpdateEntity(JudgeQuestion entity);
}
