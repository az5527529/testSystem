package com.service.system;

import com.entity.AnswerDetail;
import com.entity.UserInfo;
import com.exception.MessageException;
import com.service.common.BaseService;

import java.util.List;

/**
 * Created by victor on 2018/3/5.
 */
public interface AnswerDetailService extends BaseService<AnswerDetail> {
    public List loadDetailByUserId(String userId);

    public void saveTestDetail(UserInfo userInfo, List<AnswerDetail> list) throws MessageException;
}
