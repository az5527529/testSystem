<%@ page import="com.util.StringUtil" %>
<%@ page import="com.entity.Activity" %>
<%@ page import="com.service.system.ActivityService" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="/WEB-INF/pages/common/head.jsp"%>
<%
    String code = request.getParameter("code");
    if("null".equals(code) || code == null){
        code = "";
    }
    Activity headActivity = null;
//    String openid = SessionManager.getAttribute("openid") == null ? "" : SessionManager.getAttribute("openid").toString();
    String openid = "1";
    String activityId = request.getParameter("activityId");
    //未进行微信认证
    /*if(StringUtil.isEmptyString(code) && StringUtil.isEmptyString(openid)){
        response.sendRedirect(request.getContextPath() + "/weixin.jsp?url=" + request.getContextPath() + "/test.jsp?activityId=" + activityId);
        return;
    }
    //获取微信id
    if(!StringUtil.isEmptyString(code)&&StringUtil.isEmptyString(openid)){
        WechatUtil.getWechatVo(code);
    }*/
    SessionManager.setAttribute("openid","1");
    if(!StringUtil.isEmptyString(activityId)){
        SessionManager.setAttribute("activityId",activityId);
        WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        ActivityService service = (ActivityService)wc.getBean("activityService");
        headActivity = service.loadById(Long.parseLong(activityId));
        SessionManager.setAttribute("activity",headActivity);
        //判断活动是否已过期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new Date());
        if(headActivity == null || currentTime.compareTo(headActivity.getStartTime()) < 0
                || currentTime.compareTo(headActivity.getEndTime()) > 0){
            response.sendRedirect(request.getContextPath() + "/expire.jsp");
        }else if(headActivity.getActivityType() != 2){
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }else{
        if(SessionManager.getAttribute("activityId") == null ){
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }
    }
%>
<c:set value="<%=headActivity%>" var="activity" />
<script type="text/javascript">
    var activityId= "<%=activityId%>";
    var openid= "<%=openid%>";
</script>
<html>
<head>
    <title>${activity.activityName}</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style,content=black"/>
    <link rel="stylesheet" href="${ctx}/css/ignorePath/uploadImg.css">
</head>

<body>
    <div class="header">
        <img class="backgroundImg2" id="backgroundImg" src="${ctx}/userImg/getImgByUrl.action?imgUrl=${activity.backgroundUrl}">
    </div>
    <div id="content" >
        <form id="userForm" action="${ctx}/userImg/uploadImg.action" method="post" enctype="multipart/form-data">
            <input type="hidden" id="openid" name="openid" value="<%=openid%>">
            <input type="hidden" id="activityId" name="activityId" value="<%=activityId%>">
            <input type="hidden" id="imgUrl">
            <input type="hidden" id="userImgId" name="userImgId">
            <ul>
                <li style="height: auto;">
                    <div class="imageDiv">
                        <img id="userImg" src="">
                    </div>

                </li>
                <li style="height: auto;"><input type="file" id="uploadFile"  name="uploadFile" onchange="preview(this)">
                </li>
                <li class="bigFont"><label>姓名<span class="redStar">*</span>:</label><input id="userName" name="userName" type="text" placeholder="请输入姓名" required="required" /></li>
                <li class="bigFont"><label>电话<span class="redStar">*</span>:</label><input name="telephone" id="telephone" type="text" placeholder="请输入电话" required="required" /></li>
            </ul>

        </form>
        <ul id="uploadBtnUl">
            <li class="btnLi" id="uploadBtn">
                <a href="javascript:void(0)" onclick="upload()" >提交</a>
            </li>
            <li class="btnLi" id="modifyBtn">
                <a href="javascript:void(0)" onclick="modify()" >修改</a>
            </li>
        </ul>

    </div>

</body>
<script src="${ctx}/js/ignorePath/uploadImg.js"></script>
<script type="text/javascript">

</script>
</html>
