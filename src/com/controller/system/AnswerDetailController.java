package com.controller.system;

import com.entity.AnswerDetail;
import com.entity.UserInfo;
import com.exception.MessageException;
import com.service.system.AnswerDetailService;
import com.util.WebUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by victor on 2018/3/5.
 */
@Controller
@RequestMapping("/answerDetail")
public class AnswerDetailController {
    @Resource
    private AnswerDetailService answerDetailService;

    /**
     * 通过用户id获取答题详情
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/loadDetailByUserId", method = RequestMethod.POST)
    public void loadDetailByUserId(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        List list = this.answerDetailService.loadDetailByUserId(id);
        JSONObject json = new JSONObject();
        json.put("list",list);
        WebUtil.outputPage(request, response, json.toString());
    }

    /**
     * 保存用户答案
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/saveTestDetail", method = RequestMethod.POST)
    public void saveTestDetail(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String telephone = request.getParameter("telephone");
        String openid = request.getParameter("openid");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setTelephone(telephone);
        userInfo.setOpenid(openid);
        userInfo.setTestTime(sdf.format(new Date()));

        String list = request.getParameter("list");
        JSONArray array = JSONArray.fromObject(list);
        List<AnswerDetail> detailList = new ArrayList<AnswerDetail>();
        for(int i = 0; i < array.size(); i++){
            JSONObject obj = array.getJSONObject(i);
            AnswerDetail entity = new AnswerDetail();
            entity.setQuestionId(obj.getLong("questionId"));
            entity.setQuestionType(obj.getInt("questionType"));
            entity.setUserAnswer(obj.getString("userAnswer"));
            detailList.add(entity);
        }
        JSONObject result = new JSONObject();
        try {
            this.answerDetailService.saveTestDetail(userInfo,detailList);
            result.put("result",1);
        } catch (MessageException e) {
            e.printStackTrace();
            result.put("result",0);
            result.put("errorMsg",e.getErrorMsg());
        }
        WebUtil.outputPage(request, response, result.toString());
    }
}
