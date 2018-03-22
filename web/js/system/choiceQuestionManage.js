$(function(){
    $('#question').datagrid({
        pageList: [10,20,50,100],
        pagination:true,
        rownumbers:true,
        pagePosition:"bottom",
        idField: 'choiceQuestionId',
        width: ($("#list").width()),
        height:$("#list").height(),
        fitColumns:true,
        toolbar:"#btn",
        singleSelect:true,
        sortName:"questionNo",
        sortOrder:"asc",
        url:ctx+"/choiceQuestion/searchQuestion.action?ids=" + Math.random(),
        columns:[[
            {field:'choiceQuestionId',title:'',hidden:true},
            {field:'questionNo',title:'题号',width:50,align:'center'},
            {field:'content',title:'问题',width:120,align:'center'},
            {field:'keya',title:'A选项',width:80,align:'center'},
            {field:'keyb',title:'B选项',width:80,align:'center'},
            {field:'keyc',title:'C选项',width:80,align:'center'},
            {field:'keyd',title:'D选项',width:80,align:'center'},
            {field:'answer',title:'正确答案',width:80,align:'center'}
        ]]
    });


});

//查询方法
function searchQuestion(){
    $('#question').datagrid("options").queryParams={
        "questionNo": $("#questionNo").val(),"content": $("#content").val()
        // fields:JSON.stringify({"questionNo": $("#questionNo").val(),"content": $("#content").val()}),

    };
    // $('#question').datagrid("options").url=ctx+"/choiceQuestion/searchQuestion.action?ids=" + Math.random();
    $('#question').datagrid("load");
}

var url = ctx + "/choiceQuestion/saveOrUpdate.action";;
//新增方法
function newQuestion(){
    $("#dlg").dialog("open").dialog('setTitle', '新增题目'); ;
    $("#fm").form("clear");
    $("#hidtype").value="submit";
}

function editQuestion(){
    var row = $("#question").datagrid("getSelected");
    if (row) {
        $("#dlg").dialog("open").dialog('setTitle', '编辑题目');
        $("#fm").form("load", row);
    }
}

//保存方法
function saveQuestion(){
    $("#fm").form("submit", {
        url: url,
        onsubmit: function () {
            return $(this).form("validate");
        },
        success: function (result) {
            if (result == "1") {
                newShow("操作成功");
                $("#dlg").dialog("close");
                $("#question").datagrid("load");
            }
            else {
                $.messager.alert("提示信息", "操作失败");
            }
        }
    });
}

function deleteQuestion(){
    var row = $('#question').datagrid('getSelected');
    if (row) {
        $.messager.confirm('Confirm', '您确定要删除该题目吗?', function (r) {
            if (r) {
                $.post(ctx + '/choiceQuestion/deleteById.action', { id: row.choiceQuestionId }, function (result) {
                    if (result.success) {
                        newShow("删除成功");
                        $('#question').datagrid('reload');    // reload the user data
                    } else {
                        newAlert(result.errorMsg);
                    }
                }, 'json');
            }
        });
    }
}