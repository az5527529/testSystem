package com.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.entity.UserInfo;
import com.exception.MessageException;
import com.service.system.UserInfoService;
import com.util.StringUtil;
import com.util.WebUtil;
import com.vo.Page;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by victor on 2018/3/2.
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;
    @RequestMapping(value = "/searchUserInfo", method = RequestMethod.POST)
    public void searchUserInfo(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        Map<String,String> condition = new HashMap<String, String>();
        condition.put("testTimeBegin",request.getParameter("testTimeBegin"));
        condition.put("testTimeEnd",request.getParameter("testTimeEnd"));
        condition.put("userName",request.getParameter("userName"));
        condition.put("telephone",request.getParameter("telephone"));
        condition.put("activityId",request.getParameter("activityId"));
        String rowsStr = request.getParameter("rows");
        String pageStr=request.getParameter("page");
        int pageSize = 10;
        int pageNum = 1;
        if(!StringUtil.isEmptyString(rowsStr)&&!StringUtil.isEmptyString(pageStr)){
            pageSize = Integer.parseInt(rowsStr);
            pageNum = Integer.parseInt(pageStr);
        }
        condition.put("pageSize",pageSize + "");
        condition.put("pageNum",pageNum + "");
        condition.put("sort",request.getParameter("sort"));
        condition.put("order",request.getParameter("order"));

        Page page = this.userInfoService.getData(condition);
        WebUtil.outputPage(request, response, JSONObject.toJSONString(page));
    }

    /**
     * 新增编辑保存方法
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public void saveOrUpdate(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        net.sf.json.JSONObject o = new net.sf.json.JSONObject();
        UserInfo entity = null;
        String id = request.getParameter("userInfoId");
        if(id != null && !id.equals("")){
            entity = userInfoService.loadById(Long.parseLong(id));
        }else{
            entity = new UserInfo();
        }
        try {
            BeanUtils.populate(entity, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
            o.put("errorMessage", e.getMessage());
        }
        entity = userInfoService.saveOrUpdateEntity(entity);
        String result = "1";
        WebUtil.outputPage(request, response, result);
    }
    @RequestMapping(value = "/deleteUserInfoById", method = RequestMethod.POST)
    public void deleteUserInfoById(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        this.userInfoService.deleteUserInfoById(Long.parseLong(id));
        net.sf.json.JSONObject o = new net.sf.json.JSONObject();
        o.put("success", "1");
        WebUtil.outputPage(request, response, o.toString());
    }

    /**
     * 获取试题
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/loadTest", method = RequestMethod.POST)
    public void loadTest(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        List list = this.userInfoService.loadTest();
        net.sf.json.JSONObject o = new net.sf.json.JSONObject();
        o.put("list", list);
        WebUtil.outputPage(request, response, o.toString());
    }

    /**
     * 通过openid获取用户信息
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/loadUserInfoByOpenid", method = RequestMethod.POST)
    public void loadUserInfoByOpenid(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        String openid = request.getParameter("openid");
        String activityId = request.getParameter("activityId");
        net.sf.json.JSONObject o = new net.sf.json.JSONObject();

        try {
            UserInfo entity = this.userInfoService.loadUserInfoByOpenid(openid,activityId);
            o.put("success",1);
            o.put("userInfo",entity);
        } catch (MessageException e) {
            e.printStackTrace();
            o.put("errorMsg",e.getErrorMsg());
            o.put("success",0);
        }

        WebUtil.outputPage(request, response, o.toString());
    }

    /**
     * 通过openid判断用户是否已作答
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/isHaveTest", method = RequestMethod.POST)
    public void isHaveTest(HttpServletRequest request,
                                     HttpServletResponse response) throws IOException {
        String openid = request.getParameter("openid");
        String activityId = request.getParameter("activityId");
        net.sf.json.JSONObject o = new net.sf.json.JSONObject();

        try {
            boolean isExist = this.userInfoService.isHaveTest(openid,activityId);
            o.put("success",1);
            o.put("isExist",isExist);
        } catch (MessageException e) {
            e.printStackTrace();
            o.put("errorMsg",e.getErrorMsg());
            o.put("success",0);
        }

        WebUtil.outputPage(request, response, o.toString());
    }
}
