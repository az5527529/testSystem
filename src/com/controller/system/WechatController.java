package com.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.util.WebUtil;
import com.util.WechatUtil;
import com.vo.WechatVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by victor on 2018/3/9.
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
    @RequestMapping(value = "/getWechatVo", method = RequestMethod.POST)
    public void getWechatVo(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        WechatVo vo = WechatUtil.getWechatVo(code);
        WebUtil.outputPage(request, response, JSONObject.toJSONString(vo));
    }
}
