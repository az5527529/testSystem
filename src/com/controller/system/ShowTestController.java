package com.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.service.system.ShowTestService;
import com.util.StringUtil;
import com.util.WebUtil;
import com.vo.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by victor on 2018/3/2.
 */
@Controller
@RequestMapping("/showTest")
public class ShowTestController {
    @Resource
    private ShowTestService showTestService;

    /**
     * 获取测试总体情况
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/showGeneralTest", method = RequestMethod.POST)
    public void showGeneralTest(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        Map<String,String> condition = new  HashMap<String, String>();
        condition.put("testTimeBegin",request.getParameter("testTimeBegin"));
        condition.put("testTimeEnd",request.getParameter("testTimeEnd"));
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

        Page page = this.showTestService.getData(condition);
        WebUtil.outputPage(request, response, JSONObject.toJSONString(page));
    }
}
