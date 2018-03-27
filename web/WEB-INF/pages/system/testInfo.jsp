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
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/css/ignorePath/common/editForm.css" type="text/css">
</head>

<body>
    <div id="cc" class="easyui-layout" style="width: 100%; height: 260px">

        <div data-options="region:'north',border:false" id="search"
             style="padding: 5px; ">
            <form id="editForm" method="post" style="margin-top: 10px">
                <input type="hidden" name="testInfoId" id="testInfoId" />
                <ul>
                    <li><label for="numberOfSelect">选择题数目<span class="redStar">*</span>:</label> <input id="numberOfSelect" name="numberOfSelect" class="easyui-numberbox" data-options="min:0,precision:0" required="true"/></li>
                    <li><label for="valueOfSelect">分值<span class="redStar">*</span>:</label> <input id="valueOfSelect" name="valueOfSelect" class="easyui-numberbox" data-options="min:0,precision:1" required="true"/></li>
                </ul>
                <ul>
                    <li><label for="numberOfJudge">判断题数目<span class="redStar">*</span>:</label> <input id="numberOfJudge" name="numberOfJudge" class="easyui-numberbox" data-options="min:0,precision:0" required="true"/></li>
                    <li><label for="valueOfJudge">分值<span class="redStar">*</span>:</label> <input id="valueOfJudge" name="valueOfJudge" class="easyui-numberbox" data-options="min:0,precision:1" required="true"/></li>
                </ul>
            </form>

            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveTestInfo()" iconcls="icon-save">保存</a>
        </div>
    </div>

</body>
<script src="${ctx}/js/system/testInfo.js"></script>
</html>
