package com.util;

import com.entity.LoginUser;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/****************************************
 * @name Session
 * @description 系统管理平台会话实体
 * @author hewc
 * @since 2016-08-15
 ***************************************/
public class SessionManager {
	private static Map<String,SessionManager> sessionMap = new HashMap<String,SessionManager>();
	private static final ThreadLocal httpSession = new ThreadLocal();

	public static void setSession(HttpSession paramHttpSession) {
		httpSession.set(paramHttpSession);
	}

	public static HttpSession getSession() {
		return (HttpSession) httpSession.get();
	}

	public static void setAttribute(String paramString, Object paramObject) {
		HttpSession localHttpSession = (HttpSession) httpSession.get();
		if (localHttpSession != null)
			localHttpSession.setAttribute(paramString, paramObject);
	}

	public static Object getAttribute(String paramString) {
		HttpSession localHttpSession = (HttpSession) httpSession.get();
		if (localHttpSession != null)
			return localHttpSession.getAttribute(paramString);
		return null;
	}

	public static LoginUser getLoginUser(){
		return (LoginUser)getAttribute("loginUser");
	}
	public static void setLoginUser(LoginUser loginUser){
		setAttribute("loginUser",loginUser);
	}
	public static String getUserName(){
		LoginUser loginUser = getLoginUser();
		if(loginUser != null){
			return loginUser.getUserName();
		}
		return "";
	}

	public static void clearLoginUser(){
		HttpSession localHttpSession = (HttpSession) httpSession.get();
		if (localHttpSession != null)
			localHttpSession.removeAttribute("loginUser");
	}
}
