<%--
  Created by IntelliJ IDEA.
  User: victor
  Date: 2018/2/28
  Time: 9:49
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="/WEB-INF/pages/common/head.jsp"%>
<html>
<head>
    <title>判断题题管理</title>
    <link rel="stylesheet" href="${ctx}/css/common/editForm.css" type="text/css">
</head>
<script src="${ctx}/js/system/judgeQuestionManage.js" type="text/javascript"></script>
<body>
<div id="cc" class="easyui-layout" style="width: 100%; height: 460px">

    <div data-options="region:'north',border:false" id="search"
         style="padding: 5px; height: 70px">
        <form id="searchForm" style="margin-top: 10px">
            <ul>
                <li><label for="questionNo">题号:</label> <input id="questionNo"
                                                               class="easyui-validatebox" /></li>
                <li><label for="content">题目内容:</label> <input
                        id="content" class="easyui-validatebox" /></li>
            </ul>
        </form>
    </div>
    <div data-options="region:'center',split:true" style="height: 390px"
         id="list">
        <table id="question"></table>
    </div>
</div>
<div id="btn">
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-search'" onclick="searchQuestion()"
       plain="true"></a>
    <a href="#" class="easyui-linkbutton"
       iconCls="icon-add" plain="true" id="new" onclick="newQuestion()"></a>
    <a href="#"
       class="easyui-linkbutton" iconCls="icon-edit" id="edit" plain="true" onclick="editQuestion()"></a>
    <a
            href="#" class="easyui-linkbutton" iconCls="icon-remove" id="remove" plain="true" onclick="deleteQuestion()"></a>
</div>

<div id="dlg" class="easyui-dialog" style="width: 400px; height: 400px; padding: 10px 20px;"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">
        信息编辑
    </div>
    <form id="fm" method="post">
        <ul>
            <li class="labelLi"><label>题号<span class="redStar">*</span></label></li>
            <li class="inputLi"><input name="questionNo" class="easyui-validatebox" required="true" /></li>
        </ul>
        <ul>
            <li class="labelLi"><label>问题<span class="redStar">*</span></label></li>
            <li class="inputLi"><input name="content" class="easyui-textbox" style="width:200px;height:100px" data-options="multiline:true" required="true" /></li>
        </ul>

        <ul>
            <li class="labelLi"><label>正确答案<span class="redStar">*</span></label></li>
            <li class="inputLi"><select id="answer" name='answer' class="easyui-combobox" data-options="editable:false" >
                <option value="1">正确</option>
                <option value="0">错误</option>
            </select></li>
        </ul>

        <input type="hidden" name="action" id="hidtype" />
        <input type="hidden" name="judgeQuestionId" id="judgeQuestionId" />
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveQuestion()" iconcls="icon-save">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')"
       iconcls="icon-cancel">取消</a>
</div>

</body>
</html>
