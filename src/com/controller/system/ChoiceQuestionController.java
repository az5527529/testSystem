package com.controller.system;

import com.entity.ChoiceQuestion;
import com.service.system.ChoiceQuestionService;
import com.util.WebUtil;
import com.vo.Page;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by victor on 2018/2/28.
 */
@Controller
@RequestMapping("/choiceQuestion")
public class ChoiceQuestionController {
    @Resource
    private ChoiceQuestionService choiceQuestionService;

    @RequestMapping(value = "/searchQuestion", method = RequestMethod.POST)
    public void searchQuestion(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        Map<String,String[]> parameterMap = request.getParameterMap();
        Map paramMap = new HashMap();
        for(Iterator iter = parameterMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry element = (Map.Entry) iter.next();
            //key值
            Object strKey = element.getKey();
            //value,数组形式
            String[] value = (String[]) element.getValue();
            paramMap.put(strKey,value[0]);
        }
        Page page = choiceQuestionService.getData(paramMap);
        JSONObject o = JSONObject.fromObject(page);
        WebUtil.outputPage(request, response, o.toString());
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
        JSONObject o = new JSONObject();
        ChoiceQuestion entity = null;
        String id = request.getParameter("choiceQuestionId");
        if(id != null && !id.equals("")){
            entity = choiceQuestionService.loadById(Long.parseLong(id));
        }else{
            entity = new ChoiceQuestion();
        }
        try {
            BeanUtils.populate(entity, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
            o.put("errorMessage", e.getMessage());
        }
        entity = choiceQuestionService.saveOrUpdateEntity(entity);
        String result = "1";
        WebUtil.outputPage(request, response, result);
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public void deleteById(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        int n = choiceQuestionService.deleteById(Long.parseLong(id));
        JSONObject o = new JSONObject();
        if(n>0){
            o.put("success", "1");
        }else{
            o.put("errorMsg", "删除失败");
        }
        WebUtil.outputPage(request, response, o.toString());
    }
}
