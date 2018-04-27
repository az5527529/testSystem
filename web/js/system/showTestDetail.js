$(function(){
    $('#showTestTable').datagrid({
        pageList: [10,20,50,100],
        pagination:true,
        rownumbers:true,
        pagePosition:"bottom",
        width: ($("#list").width()),
        height:$("#list").height(),
        fitColumns:true,
        toolbar:"#btn",
        singleSelect:true,
        sortName:"testTime",
        sortOrder:"desc",
        idField: 'userInfoId',
        // url:ctx+"/showTest/showGeneralTest.action?ids=" + Math.random(),
        columns:[[
            {field:'userInfoId',title:'',hidden:true},
            {field:'userName',title:'姓名',width:50,align:'center'},
            {field:'telephone',title:'电话',width:120,align:'center'},
            {field:'score',title:'分数',width:80,align:'center'},
            {field:'testTime',title:'提交时间',width:80,align:'center'},
            {field:'detail',title:'详情',width:80,align:'center',formatter:detailFormatter},

        ]]
    });


});

//查询方法
function searchTest(){
    $('#showTestTable').datagrid("options").queryParams={
        "testTimeBegin": $("#testTimeBegin").datebox('getValue'),"testTimeEnd": $("#testTimeEnd").datebox('getValue')
        ,"userName":$("#userName").val(),"telephone":$("#telephone").val()
        ,"activityId" : $('#activityId').combobox('getValue')

    };
    $('#showTestTable').datagrid("options").url=ctx+"/userInfo/searchUserInfo.action?ids=" + Math.random();
    $('#showTestTable').datagrid("load");
}

function editUserInfo(){
    var row = $("#showTestTable").datagrid("getSelected");
    if (row) {
        $("#dlg").dialog("open").dialog('setTitle', '编辑');
        $("#fm").form("load", row);
    }
}

var url=ctx + "/userInfo/saveOrUpdate.action";
//保存方法
function saveUserInfo(){
    $("#fm").form("submit", {
        url: url,
        onsubmit: function () {
            return $(this).form("validate");
        },
        success: function (result) {
            if (result == "1") {
                newShow("操作成功");
                $("#dlg").dialog("close");
                $("#showTestTable").datagrid("load");
            }
            else {
                $.messager.alert("提示信息", "操作失败");
            }
        }
    });
}

//删除用户信息
function deleteUserInfo(){
    var row = $('#showTestTable').datagrid('getSelected');
    if (row) {
        $.messager.confirm('Confirm', '您确定要删除该数据吗?', function (r) {
            if (r) {
                $.post(ctx + '/userInfo/deleteUserInfoById.action', { id: row.userInfoId }, function (result) {
                    if (result.success) {
                        newShow("删除成功");
                        $('#showTestTable').datagrid('reload');    // reload the user data
                    } else {
                        newAlert("删除失败");
                    }
                }, 'json');
            }
        });
    }
}

function detailFormatter(value,row,index){
    return "<a style='color:blue' href='#' onclick='showDetail("+row.userInfoId+")'>详情</a>";
}

function showDetail(userInfoId){
    $("#detailDlg").dialog("open").dialog('setTitle', '详情');
    $.ajax({
        type : "post",
        dataType : 'json',
        data:{id:userInfoId},
        url : ctx+"/answerDetail/loadDetailByUserId.action?ids=" + Math.random(),

        success : function(result) {
            var i = 0;//选择题下标
            var j = 0;//判断题下标
            $("#detail").html("");
            var choiceDiv = $("<div>");//选择题区域
            choiceDiv.html("<ul style='width: 100%'><li>一、选择题</li></ul>");
            var judgeDiv = $("<div>");//判断题区域
            judgeDiv.html("<ul style='width: 100%'><li>二、判断题</li></ul>");
            $("#detail").append(choiceDiv).append(judgeDiv);
            var list = result.list;

            for(var index = 0; index < list.length;index++){
                var data = list[index];
                var type = data.type;//问题类型
                if(type == 1){//选择题
                    i++;
                    //第一行
                    var firstUl = $("<ul>");//问题
                    // firstUl.append($("<li></li>").html(i +"."+ data.content));
                    var answerLi = $("<li>");
                    var result = data.result;//结果
                    if(result){//正确，直接显示答案，绿色
                        answerLi.html(i +"."+ data.content +"&nbsp;<span style='color:green'>" + data.userAnswer + "</span>");
                    }else{//错误，显示用户答案，红色，再提示正确答案
                        answerLi.html(i +"."+ data.content +"&nbsp;<span style='color:red'>" + data.userAnswer + "&nbsp;正确答案为:" + data.answer + "</span>");
                    }
                    firstUl.append(answerLi);
                    choiceDiv.append(firstUl);
                    //第二行
                    var seccondUl = $("<ul>");//选项
                    seccondUl.append($("<li></li>").html("&nbsp;A&nbsp;" + data.keya));
                    seccondUl.append($("<li></li>").html("&nbsp;B&nbsp;" + data.keyb));
                    seccondUl.append($("<li></li>").html("&nbsp;C&nbsp;" + data.keyc));
                    seccondUl.append($("<li></li>").html("&nbsp;D&nbsp;" + data.keyd));
                    choiceDiv.append(seccondUl);
                }else if(type == 2){//判断题
                    j++;
                    var ul = $("<ul>");//问题
                    // ul.append($("<li></li>").html(j +"."+ data.content));
                    var answerLi = $("<li>");
                    var result = data.result;//结果
                    if(result){//正确，直接显示答案，绿色
                        answerLi.html(j +"."+ data.content +"&nbsp;<span style='color:green'>" + data.userAnswer + "</span>");
                    }else{//错误，显示用户答案，红色，再提示正确答案
                        answerLi.html(j +"."+ data.content +"&nbsp;<span style='color:red'>" + data.userAnswer + "&nbsp;正确答案为:" + data.answer + "</span>");
                    }
                    ul.append(answerLi);
                    judgeDiv.append(ul);
                }
            }
        },
        async : true
    });
}

//导出
function exportAll(){
    var params =[
        {name: 'testTimeBegin', value: $("#testTimeBegin").datebox('getValue')}
        ,{name: 'testTimeEnd', value: $("#testTimeEnd").datebox('getValue')}
        ,{name: 'userName', value:$("#userName").val()}
        ,{name: 'telephone', value:$("#telephone").val()}
        ,{name: 'activityId',value : $('#activityId').combobox('getValue')}
        ,{name: 'serviceName',value : "userInfoService"}
    ];

    exportToExcel(params);
}