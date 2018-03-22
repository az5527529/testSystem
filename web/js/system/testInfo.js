$(function(){
    $.ajax({
        type : "post",
        dataType : 'json',
        url : ctx+"/testInfo/loadTestInfo.action?ids=" + Math.random(),

        success : function(data) {
            $("#editForm").form("load", data);
        },
        async : true
    });

});



var url=ctx + "/testInfo/saveOrUpdate.action";
//保存方法
function saveTestInfo(){
    $("#editForm").form("submit", {
        url: url,
        onsubmit: function () {
            return $(this).form("validate");
        },
        success: function (result) {
            if (result == "1") {
                newShow("操作成功");
            }
            else {
                $.messager.alert("提示信息", "操作失败");
            }
        }
    });
}