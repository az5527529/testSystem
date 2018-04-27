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
    <div id="cc" class="easyui-layout" style="width: 100%; height: 440px">

        <div data-options="region:'north',border:false" id="search"
             style="padding: 5px; height: 70px">
            <form id="searchForm" style="margin-top: 10px">
                <ul>
                    <li><label>活动:</label>

                    <li style="width: 10rem;">
                        <select id="activityId"  style="width: 100%;" name='activityId' class="easyui-combobox"
                                data-options="editable:false" >
                        </select>
                    </li>
                </ul>
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
<script type="text/javascript">
    $(function () {
        $("#activityId").combobox({
            url:ctx + '/activity/getAllActivityByType.action?activityType=1',
            valueField:'activityId',//相当于option的value值
            textField:'activityName',//相当于<option></option>之间的显示值 value:1000    //默认显示值
            onLoadSuccess: function () { //加载完成后,设置选中第一项
                var val = $(this).combobox('getData');
                for (var item in val[0]) {
                    if (item == 'activityId') {
                        $(this).combobox('select', val[0][item]);
                    }
                }
            }
        });
    });

</script>
</html>
