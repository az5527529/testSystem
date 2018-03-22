package com.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.entity.TestInfo;
import com.service.system.TestInfoService;
import com.util.WebUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by victor on 2018/3/2.
 */
@Controller
@RequestMapping("testInfo")
public class TestInfoController {
    @Resource
    private TestInfoService testInfoService;
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
        TestInfo entity = null;
        String id = request.getParameter("testInfoId");
        if(id != null && !id.equals("")){
            entity = testInfoService.loadById(Long.parseLong(id));
        }else{
            entity = new TestInfo();
        }
        try {
            BeanUtils.populate(entity, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
            o.put("errorMessage", e.getMessage());
        }
        entity = testInfoService.saveOrUpdateEntity(entity);
        String result = "1";
        WebUtil.outputPage(request, response, result);
    }

    /**
     * 获取
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/loadTestInfo", method = RequestMethod.POST)
    public void loadTestInfo(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        TestInfo entity = this.testInfoService.loadTestInfo();
        WebUtil.outputPage(request, response, JSONObject.toJSONString(entity));
    }
}
