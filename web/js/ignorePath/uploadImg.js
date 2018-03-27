
$(function(){
    if(!code){
        window.location.href = ctx + "/weixin.jsp?url=" + ctx+"/uploadImg.jsp";//跳到授权页面
    }else{
        displayImg();
        $.ajax({
            type : "post",
            dataType : 'json',
            data : {code:code},
            url : ctx+"/wechat/getWechatVo.action?ids=" + Math.random(),

            success : function(data) {
                $("#openid").val(data.openid);
            },
            error : function(xhr, type, msg) {
                var prompt = xhr.status + ': ' + msg;
                if (xhr.status == 500) {
                    var error = jQuery.parseJSON(xhr.responseText);
                    prompt = error.errorMsg;
                    if (error.exceptionCode)
                        prompt = prompt + ' (' + error.exceptionCode + ')';
                } else if (xhr.status == 0) {
                    prompt = "服务器无响应";
                }
                alert(prompt);
                window.location.href = ctx + "/error.jsp";//跳到错误页面
            },
            async : false
        });

        //判断是否已经上传过图片

        $.ajax({
            type : "post",
            data : {openid:$("#openid").val()},
            dataType : 'json',
            url : ctx+"/userImg/loadUserImgByOpenid.action?ids=" + Math.random(),

            success : function(data) {
                if(data.success){
                    //如果已上传过图片，则加载图片信息，并只展示，不可修改
                    if(data.userImg){
                        var userImg = data.userImg;
                        $("#userImg").attr("src",ctx + "/userImg/getImgByUrl.action?imgUrl=" +userImg.imgUrl);
                        $("#userForm").form("load",userImg);
                    }else{
                        showImg();
                    }
                }else{
                    newAlert(data.errorMsg);
                }
            },
            async : false
        });

    }


});

//图片预览
function preview(file){

    if (file.files && file.files[0]) {
        var max_size = 3;// 10M
        if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(file.value)) {
            newAlert("图片类型必须是[.gif,jpeg,jpg,png]中的一种");
            file.value = "";
            $("#userImg").attr("src","");
            return false;
        } else {
            var fileData = file.files[0];
            var size = fileData.size;
            if (size > max_size * 1024 * 1024) {
                newAlert("图片大小不能超过" + max_size + "M");
                file.value = "";
                $("#userImg").attr("src","");
            } else {
                //实例化一个FileReader
                var reader = new FileReader();
                reader.onload = function (e) {
                    //当reader加载时，把图片的内容赋值给
                    $("#userImg").attr("src",e.target.result);
                };
                //读取选中的图片，并转换成dataURL格式
                reader.readAsDataURL(file.files[0]);
            }
        }

    }
}



var url=ctx + "/userInfo/saveTest.action";
//保存方法
function upload(){
    if(!$("#uploadFile").val()){
        newAlert("请选择需要上传的图片");
        return;
    }
    if(!$("#userName").val()){
        newAlert("请输入姓名");
        return;
    }
    if(!$("#telephone").val()){
        newAlert("请输入电话");
        return;
    }
    $.messager.confirm('确认', '您确定要提交?', function (r) {
        if (r) {
            //判断是否已经上传过图片
            $.ajax({
                type : "post",
                data : {openid:$("#openid").val()},
                dataType : 'json',
                url : ctx+"/userImg/isExist.action?ids=" + Math.random(),

                success : function(data) {
                    if(data.success){
                        //如果已上传过图片，则加载图片信息，并只展示，不可修改
                        if(data.isExist){
                            alert("您已上传过图片，请勿重复提交");
                        }else{

                            $("#userForm").form("submit", {
                                url: ctx+"/userImg/uploadImg.action",
                                onsubmit: function () {
                                    return false;
                                    //return $(this).form("validate");
                                },
                                success: function (result) {
                                    result = JSON.parse(result);
                                    if(result.success){
                                        alert("上传成功");
                                        displayImg();
                                    }else {
                                        newAlert("上传失败");
                                    }

                                },

                            });
                        }
                    }else{
                        newAlert(data.errorMsg);
                    }
                },
                async : false
            });

        }
    });

}

//展示上传的图片，不让编辑
function displayImg() {
    $("#uploadBtnUl").hide();
    $("#uploadFile").hide();
    $("#userName").prop("disabled",true);
    $("#telephone").prop("disabled",true);
}

function showImg(){
    $("#uploadBtnUl").show();
    $("#uploadFile").show();
    $("#userName").prop("disabled",false);
    $("#telephone").prop("disabled",false);
}