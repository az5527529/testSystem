package com.controller.common;

import com.entity.LoginUser;
import com.service.common.LoginService;
import com.util.WebUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by victor on 2018/3/27.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    private LoginService loginService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        String randCheckCode = (String)request.getSession().getAttribute("randCheckCode");
        LoginUser loginUser = null;
        if(!randCheckCode.equalsIgnoreCase(checkCode)){
            loginUser = new LoginUser();
            loginUser.setErrorMsg("验证码错误");
        }else{
            loginUser = this.loginService.login(userName,password);
        }
        request.getSession().removeAttribute("randCheckCode");
        JSONObject o = JSONObject.fromObject(loginUser);
        WebUtil.outputPage(request, response, o.toString());
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("loginUser");
        WebUtil.outputPage(request, response, "");
    }
}
