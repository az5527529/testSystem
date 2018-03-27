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
    <style>
        .wrap ul{
            width: 100%;
        }
    </style>
</head>

<body>
    <div id="cc" class="easyui-layout" style="width: 100%; height: 460px">

        <div data-options="region:'north',border:false" id="search"
             style="padding: 5px; height: 70px">
            <form id="searchForm" style="margin-top: 10px">
                <ul>
                    <li><label for="userName">姓名:</label> <input id="userName" class="easyui-validatebox" /></li>
                    <li><label for="telephone">电话:</label> <input id="telephone" class="easyui-validatebox" /></li>
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
        <a href="#"
           class="easyui-linkbutton" iconCls="icon-edit" id="edit" plain="true" onclick="editUserInfo()"></a>
        <a
            href="#" class="easyui-linkbutton" iconCls="icon-remove" id="remove" plain="true" onclick="deleteUserInfo()"></a>
        <a
                href="#" class="easyui-linkbutton" id="export" plain="true" onclick="exportAll()">导出</a>
    </div>
    <div id="dlg" class="easyui-dialog" style="width: 420px; height: 400px; padding: 10px 20px;"
         closed="true" buttons="#dlg-buttons">
        <div class="ftitle">
            信息编辑
        </div>
        <form id="fm" method="post">
            <ul>
                <li class="labelLi"><label>姓名<span class="redStar">*</span></label></li>
                <li class="inputLi"><input name="userName" class="easyui-validatebox" required="true" /></li>
            </ul>

            <ul>
                <li class="labelLi"><label>电话<span class="redStar">*</span></label></li>
                <li class="inputLi"><input name="telephone" class="easyui-validatebox" required="true" /></li>
            </ul>
            <ul>
                <li class="labelLi"><label>分数<span class="redStar">*</span></label></li>
                <li class="inputLi"><input name="score" class="easyui-numberbox" style="width: 200px" data-options="min:0,precision:1" required="true" /></li>
            </ul>

            <input type="hidden" name="action" id="hidtype" />
            <input type="hidden" name="testTime" id="testTime" />
            <input type="hidden" name="userInfoId" id="userInfoId" />
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveUserInfo()" iconcls="icon-save">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')"
           iconcls="icon-cancel">取消</a>
    </div>

    <div id="detailDlg" class="easyui-dialog" style="width: 420px; height: 400px; padding: 10px 20px;"
         closed="true" buttons="#detailDlg-buttons">
        <div class="ftitle">
            详情
        </div>
        <div id="detail" class="wrap">

        </div>
    </div>
    <div id="detailDlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#detailDlg').dialog('close')"
           iconcls="icon-cancel">关闭</a>
    </div>
</body>
<script src="${ctx}/js/system/showTestDetail.js"></script>
</html>
