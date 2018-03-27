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
</head>

<body>
    <div id="cc" class="easyui-layout" style="width: 100%; height: 460px">

        <div data-options="region:'north',border:false" id="search"
             style="padding: 5px; height: 70px">
            <form id="searchForm" style="margin-top: 10px">
                <ul>
                    <li><label>日期:</label>
                        <input id="testTimeBegin" class="easyui-datebox" data-options="editable:false"></input>
                        -
                        <input id="testTimeEnd" class="easyui-datebox" data-options="editable:false"></input>

                    </li>

                </ul>
            </form>
        </div>
        <div data-options="region:'center',split:true" style="height: 390px"
             id="list">
            <table id="showTestTable"></table>
        </div>
    </div>
    <div id="btn">
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-search'" onclick="searchTest()"
           plain="true"></a>
        <a href="#" class="easyui-linkbutton"
           onclick="exportAll()"
           plain="true">导出</a>
    </div>
</body>
<script src="${ctx}/js/system/showTest.js"></script>
</html>
