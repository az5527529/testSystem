$(function(){
    $('#activity').datagrid({
        pageList: [10,20,50,100],
        pagination:true,
        rownumbers:true,
        pagePosition:"bottom",
        idField: 'activityId',
        width: ($("#list").width()),
        height:$("#list").height(),
        fitColumns:true,
        toolbar:"#btn",
        singleSelect:true,

        url:ctx+"/activity/searchActivity.action?ids=" + Math.random(),
        columns:[[
            {field:'activityId',title:'',hidden:true},
            {field:'content',title:'',hidden:true},
            {field:'backgroundUrl',title:'',hidden:true},
            {field:'activityName',title:'活动名称',width:'200',align:'center'},
            {field:'activityType',title:'活动类型',width:'200',align:'center',formatter:activityTypeFormatter},
            {field:'startTime',title:'开始时间',width:'200',align:'center'},
            {field:'endTime',title:'结束时间',width:'200',align:'center'},
            {field:'isActive',title:'是否启用',width:'80',align:'center',formatter:isActiveFormatter}
        ]]
    });


});

//活动类型
function activityTypeFormatter(value,rowData,index){
    if(value == 1){
        return "答题活动";
    }else if(value == 2){
        return "图片上传活动";
    }
    return "";
}

function isActiveFormatter(value,rowData,index){
    if(value){
        return "是";
    }else{
        return "否";
    }
}


//查询方法
function searchActivity(){
    $('#activity').datagrid("options").queryParams={
        "startTimeBegin": $("#startTimeBegin").datebox('getValue'),
        "startTimeEnd": $("#startTimeEnd").datebox('getValue'),
        "endTimeBegin": $("#endTimeBegin").datebox('getValue'),
        "endTimeEnd": $("#endTimeEnd").datebox('getValue'),
        "activityName": $("#activityName").val()

    };
    // $('#activity').datagrid("options").url=ctx+"/choiceactivity/searchactivity.action?ids=" + Math.random();
    $('#activity').datagrid("load");
}

var url = ctx + "/activity/saveOrUpdate.action";;
//新增方法
function newActivity(){
    $("#dlg").dialog("open").dialog('setTitle', '新增活动'); ;
    $("#fm").form("clear");
    $("#backgroundImg").attr("src","");
    $("#uploadFile").val("");
    $("#hidtype").value="submit";
}

function editActivity(){
    var row = $("#activity").datagrid("getSelected");
    if (row) {
        $("#dlg").dialog("open").dialog('setTitle', '编辑活动');
        $("#fm").form("load", row);
        $("#uploadFile").val("");
        $("#backgroundImg").attr("src",ctx + "/userImg/getImgByUrl.action?imgUrl=" +row.backgroundUrl);
    }
}

//保存方法
function saveActivity(){
    if(!$("#uploadFile").val() && !$("#backgroundUrl").val()){
        newAlert("请选择需要上传的图片");
        return;
    }
    if($("#endTime").datetimebox('getValue')&&$("#startTime").datetimebox('getValue')
        && $("#endTime").datetimebox('getValue')<$("#startTime").datetimebox('getValue')){
        newAlert("结束时间不能小于开始时间");
        return;
    }
    $("#fm").form("submit", {
        url: url,
        onsubmit: function () {
            return $(this).form("validate");
        },
        success: function (result) {
            result = JSON.parse(result);
            if (result.success) {
                newShow("操作成功");
                $("#dlg").dialog("close");
                $("#activity").datagrid("load");
            }
            else {
                $.messager.alert("提示信息", "操作失败");
            }
        }
    });
}

function deleteActivity(){
    var row = $('#activity').datagrid('getSelected');
    if (row) {
        $.messager.confirm('Confirm', '您确定要删除该活动吗?', function (r) {
            if (r) {
                $.post(ctx + '/activity/deleteById.action', { id: row.activityId }, function (result) {
                    if (result.success) {
                        newShow("删除成功");
                        $('#activity').datagrid('reload');    // reload the user data
                    } else {
                        newAlert(result.errorMsg);
                    }
                }, 'json');
            }
        });
    }
}

//图片预览
function preview(file){

    if (file.files && file.files[0]) {
        var max_size = 3;// 10M
        if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(file.value)) {
            newAlert("图片类型必须是[.gif,jpeg,jpg,png]中的一种");
            file.value = "";
            $("#backgroundImg").attr("src","");
            return false;
        } else {
            var fileData = file.files[0];
            var size = fileData.size;
            if (size > max_size * 1024 * 1024) {
                newAlert("图片大小不能超过" + max_size + "M");
                file.value = "";
                $("#backgroundImg").attr("src","");
            } else {
                //实例化一个FileReader
                var reader = new FileReader();
                reader.onload = function (e) {
                    //当reader加载时，把图片的内容赋值给
                    $("#backgroundImg").attr("src",e.target.result);
                };
                //读取选中的图片，并转换成dataURL格式
                reader.readAsDataURL(file.files[0]);
            }
        }

    }
}