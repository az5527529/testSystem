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
    <title>活动管理</title>

    <link rel="stylesheet" href="${ctx}/css/user/activityManage.css" type="text/css">
</head>
<script src="${ctx}/js/system/activityManage.js" type="text/javascript"></script>
<body>
<div id="cc" class="easyui-layout" style="width: 100%; height: 460px">

    <div data-options="region:'north',border:false" id="search"
         style="padding: 5px; ">
        <form id="searchForm" style="margin-top: 10px">
            <ul>
                <li><label>开始日期:</label>
                    <input id="startTimeBegin" class="easyui-datebox" data-options="editable:false"></input>
                    -
                    <input id="startTimeEnd" class="easyui-datebox" data-options="editable:false"></input>

                </li>
                <li><label>结束日期:</label>
                    <input id="endTimeBegin" class="easyui-datebox" data-options="editable:false"></input>
                    -
                    <input id="endTimeEnd" class="easyui-datebox" data-options="editable:false"></input>

                </li>
            </ul>
            <ul>
                <li>
                    <label>活动名称:</label>
                    <input name="activityName" id="activityName" class="easyui-validatebox"  />
                </li>
            </ul>
        </form>
    </div>
    <div data-options="region:'center',split:true" style="height: 390px"
         id="list">
        <table id="activity"></table>
    </div>
</div>
<div id="btn">
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-search'" onclick="searchActivity()"
       plain="true"></a>
    <a href="#" class="easyui-linkbutton"
       iconCls="icon-add" plain="true" id="new" onclick="newActivity()"></a>
    <a href="#"
       class="easyui-linkbutton" iconCls="icon-edit" id="edit" plain="true" onclick="editActivity()"></a>
    <a
            href="#" class="easyui-linkbutton" iconCls="icon-remove" id="remove" plain="true" onclick="deleteActivity()"></a>
</div>

<div id="dlg" class="easyui-dialog" style="width: 80%; height: 90%; padding: 10px 20px;"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">
        信息编辑
    </div>
    <form id="fm" method="post" action="${ctx}/activity/saveOrUpdate.action" enctype="multipart/form-data">
        <ul>
            <li class="labelLi"><label>活动名称<span class="redStar">*</span>:</label></li>
            <li class="inputLi"><input name="activityName" class="easyui-validatebox" required="true" /></li>
        </ul>
        <ul>
            <li class="labelLi"><label>活动类型<span class="redStar">*</span>:</label></li>
            <li class="inputLi"><select id="activityType" name='activityType' class="easyui-combobox" data-options="editable:false" required="true">
                <option value="1">答题活动</option>
                <option value="2">图片上传活动</option>
            </select></li>
        </ul>
        <ul>
            <li class="labelLi"><label>开始时间<span class="redStar">*</span>:</label></li>
            <li class="inputLi"><input name="startTime"  id="startTime" class="easyui-datetimebox" data-options="editable:false" required="true"></input></li>
        </ul>
        <ul>
            <li class="labelLi"><label>结束时间<span class="redStar">*</span>:</label></li>
            <li class="inputLi"><input name="endTime"  id="endTime" class="easyui-datetimebox" data-options="editable:false" required="true"></input></li>
        </ul>
        <ul>
            <li class="labelLi"><label>主题图片<span class="redStar">*</span>:</label></li>

            <li  class="inputLi"><input type="file" id="uploadFile"  name="uploadFile" onchange="preview(this)">
            <li class="labelLi"><label>活动内容<span class="redStar">*</span>:</label></li>
            <li class="inputLi"><input name="content" class="easyui-textbox" style="height:100px" data-options="multiline:true" required="true" /></li>
            <li class="labelLi"><label>是否启用<span class="redStar">*</span>:</label></li>
            <li class="inputLi"><select id="isActive" name='isActive' class="easyui-combobox" data-options="editable:false" required="true">
                <option value="1">是</option>
                <option value="0">否</option>
            </select></li>

            </li>
        </ul>
        <ul>
            <li style="height: auto;">
                <div class="imageDiv">
                    <img id="backgroundImg" src="">
                </div>

            </li>
        </ul>


        <input type="hidden" name="action" id="hidtype" />
        <input type="hidden" name="backgroundUrl" id="backgroundUrl" />
        <input type="hidden" name="activityId" id="activityId" />
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveActivity()" iconcls="icon-save">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')"
       iconcls="icon-cancel">取消</a>
</div>

</body>
</html>
