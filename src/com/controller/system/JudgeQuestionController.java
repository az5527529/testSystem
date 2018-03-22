package com.controller.system;

import com.entity.JudgeQuestion;
import com.service.system.JudgeQuestionService;
import com.util.StringUtil;
import com.util.WebUtil;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by victor on 2018/2/28.
 */
@Controller
@RequestMapping("/judgeQuestion")
public class JudgeQuestionController {
    @Resource
    private JudgeQuestionService judgeQuestionService;

    @RequestMapping(value = "/searchQuestion", method = RequestMethod.POST)
    public void searchQuestion(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        StringBuffer whereCondition = new StringBuffer();
        String questionNo = request.getParameter("questionNo");
        String content = request.getParameter("content");
        if(!StringUtil.isEmptyString(questionNo)){
            whereCondition.append(" and questionNo='" + questionNo + "'");
        }
        if(!StringUtil.isEmptyString(content)){
            whereCondition.append(" and content like '%" + content + "%'");
        }
        String rowsStr = request.getParameter("rows");
        String pageStr=request.getParameter("page");
        int pageSize = 10;
        int page = 1;
        if(!StringUtil.isEmptyString(rowsStr)&&!StringUtil.isEmptyString(pageStr)){
            pageSize = Integer.parseInt(rowsStr);
            page = Integer.parseInt(pageStr);
        }
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");
        List<JudgeQuestion> list = judgeQuestionService.query(whereCondition.toString(),(page-1)*pageSize,pageSize,sort,order);
        JSONObject o = new JSONObject();
        o.put("total", judgeQuestionService.getCount(whereCondition.toString()));
        o.put("rows", list);
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
        JudgeQuestion entity = null;
        String id = request.getParameter("judegeQuestionId");
        if(id != null && !id.equals("")){
            entity = judgeQuestionService.loadById(Long.parseLong(id));
        }else{
            entity = new JudgeQuestion();
        }
        try {
            BeanUtils.populate(entity, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
            o.put("errorMessage", e.getMessage());
        }
        entity = judgeQuestionService.saveOrUpdateEntity(entity);
        String result = "1";
        WebUtil.outputPage(request, response, result);
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public void deleteById(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        int n = judgeQuestionService.deleteById(Long.parseLong(id));
        JSONObject o = new JSONObject();
        if(n>0){
            o.put("success", "1");
        }else{
            o.put("errorMsg", "删除失败");
        }
        WebUtil.outputPage(request, response, o.toString());
    }
}
