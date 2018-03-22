<%--
  Created by IntelliJ IDEA.
  User: victor
  Date: 2018/2/27
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="/WEB-INF/pages/common/head.jsp"%>
<%
    String openid = request.getParameter("openid");
%>
<html>
<head>
    <title>图片上传系统</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style,content=black"/>
    <link href="${ctx}/css/imageSystem/showImage.css" type="text/css" rel="stylesheet">

</head>

<body>
    <div class="container" id="container">
        <header class="header" id="header">
            <ul>
                <li><label for="userName">姓名:</label> <input id="userName" class="easyui-validatebox" /></li>
                <li><label for="telephone">电话:</label> <input id="telephone" class="easyui-validatebox" /></li>
                <li><label>日期:</label>
                    <input id="uploadTimeBegin" class="easyui-datebox" data-options="editable:false"></input>
                    -
                    <input id="uploadTimeEnd" class="easyui-datebox" data-options="editable:false"></input>
                </li>
                <li>
                    <a href="#" class="easyui-linkbutton"
                       data-options="iconCls:'icon-search'" onclick="searchImage(true)"
                       plain="true">查询</a>
                </li>
            </ul>

        </header>
        <div class="content" id="content">

        </div>
        <footer class="footer" id="footer">
            <li id="lastPage" style="display: none">
                <a href="#"
                   onclick="lastPage()"
                   plain="true">上一页</a>
            </li>
            <li id="nextPage" style="display: none">
                <a href="#"
                    onclick="nextPage()"
                   plain="true">下一页</a>
            </li>
            <li>
                共<span id="total">0</span>条记录
            </li>
        </footer>
    </div>
    <div id="dlg" class="easyui-dialog" style="width: 500px;height: 100%;" closed="true" >
        <div class="detailDiv">
            <img id="detailImg" src="" class="detailImg">
        </div>
    </div>

</body>
<script src="${ctx}/js/imageSystem/showImage.js"></script>

</html>
