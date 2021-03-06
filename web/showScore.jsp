<%--
  Created by IntelliJ IDEA.
  User: victor
  Date: 2018/2/27
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="/WEB-INF/pages/common/head.jsp"%>

<html>
<head>
    <title>${activity.activityName}</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style,content=black"/>
    <link href="${ctx}/css/ignorePath/common/editForm.css" type="text/css" rel="stylesheet">
    <style>
        #detail  ul{
            width: 100%;
        }
        #content{
            width: 80%;
            margin: 0 auto;
        }
        .infoUl li{
            float: left;
        }
        .infoUl{
            width: 100%;
            margin-top: 1rem;
        }
    </style>
</head>

<body>
    <div class="header">
        <img class="backgroundImg2" id="backgroundImg" src="${ctx}/userImg/getImgByUrl.action?imgUrl=${activity.backgroundUrl}">
    </div>
    <div id="content">
        <input type="hidden" id="openid" value="${sessionScope.openid}">
        <input type="hidden" id="activityId" value="${sessionScope.activityId}">
        <input type="hidden" id="userInfoId" >
        <ul class="infoUl">
            <li><label>姓名:</label></li>
            <li><span id="userName"></span></li>
        </ul>
        <ul class="infoUl">
            <li><label>电话:</label></li>
            <li><span id="telephone"></span></li>
        </ul>
        <ul class="infoUl">
            <li><label>得分:</label></li>
            <li><span id="score"></span></li>
        </ul>
        <ul class="infoUl">
            <li><label>考试时间:</label></li>
            <li><span id="testTime"></span></li>
        </ul>
        <ul class="infoUl">
            <li>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="showDetail()" >查看详情</a>
            </li>
        </ul>

    </div>
    <div id="detailDlg" class="easyui-dialog" style="width: 350rem; height: 350rem; padding: 10px 20px;"
         closed="true" buttons="#detailDlg-buttons">
        <div class="ftitle">
            详情
        </div>
        <div id="detail"  class="wrap">

        </div>
    </div>
    <div id="detailDlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#detailDlg').dialog('close')"
           iconcls="icon-cancel">关闭</a>
    </div>
</body>
<script src="${ctx}/js/ignorePath/showScore.js"></script>
</html>
