var i = 0;//选择题下标
var j = 0;//判断题下标
var activity =[];//活动
$(function(){
    if(!code){
        window.location.href = ctx + "/weixin.jsp?url=" + ctx+"/test.jsp";//跳到授权页面
    }else{
        //获取微信id
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

        //加载当前启用的活动
        $.ajax({
            type : "post",
            dataType : 'json',
            data : {activityType:1},
            url : ctx+"/activity/getActivity.action?ids=" + Math.random(),

            success : function(data) {
                if(data.success){
                    activity = data.activity;
                    $("#activityId").val(activity.activityId);
                }else{
                    window.location.href = encodeURI(ctx + "/error.jsp?errorMsg=" + data.errorMsg);//跳到错误页面
                }

            },
            async : false
        });


        //判断是否已经考过试
        $.ajax({
            type : "post",
            data : {openid:$("#openid").val(),activityId:activity.activityId},
            dataType : 'json',
            url : ctx+"/userInfo/isHaveTest.action?ids=" + Math.random(),

            success : function(data) {
                if(data.success){
                    if(data.isExist){
                        window.location.href=ctx + "/showScore.jsp?openid=" + $("#openid").val();
                    }
                }else{
                    alert(data.errorMsg);
                    window.location.href = ctx + "/error.jsp";//跳到错误页面
                }
            },
            async : false
        });

        //加载试题
        $.ajax({
            type : "post",
            dataType : 'json',
            url : ctx+"/userInfo/loadTest.action?ids=" + Math.random(),

            success : function(data) {
                var list = data.list;
                i = 0;//初始化下标
                j = 0;
                $("#question").html("");
                var choiceDiv = $("<div></div>");//选择题区域
                choiceDiv.html("<ul><li class='questionLi'>一、选择题</li></ul>");
                var judgeDiv = $("<div>");//判断题区域
                judgeDiv.html("<ul><li class='questionLi'>二、判断题</li></ul>");
                $("#question").append(choiceDiv).append(judgeDiv);
                for(var index = 0;index < list.length;index++){
                    var data = list[index];
                    var type = data.type;//问题类型
                    if(type == 1){//选择题
                        i++;
                        //第一行
                        var firstUl = $("<ul>");//问题
                        firstUl.append($("<li class='questionLi'></li>").html(i +"."+ data.content));

                        choiceDiv.append(firstUl);
                        //第二行
                        var seccondUl = $("<ul>");//选项
                        seccondUl.append($("<input type='hidden'id='choice_id_"+i+"' value='"+data.questionId+"'>"));
                        seccondUl.append($("<li></li>").html("&nbsp;<input type='radio' name='choice_"+i+"'value='A' checked='checked'>A&nbsp;" + data.keya));
                        seccondUl.append($("<li></li>").html("&nbsp;<input type='radio' name='choice_"+i+"'value='B'>B&nbsp;" + data.keyb));
                        seccondUl.append($("<li></li>").html("&nbsp;<input type='radio' name='choice_"+i+"'value='C'>C&nbsp;" + data.keyc));
                        seccondUl.append($("<li></li>").html("&nbsp;<input type='radio' name='choice_"+i+"'value='D'>D&nbsp;" + data.keyd));
                        choiceDiv.append(seccondUl);
                    }else if(type == 2){//判断题
                        j++;
                        var ul = $("<ul>");//问题
                        ul.append($("<input type='hidden'id='judge_id_"+j+"' value='"+data.questionId+"'>"));
                        ul.append($("<li class='questionLi'></li>").html(j +"."+ data.content));
                        ul.append($("<li></li>").html("&nbsp;<input type='radio' name='judge_"+j+"'value='1' checked='checked'>正确"));
                        ul.append($("<li></li>").html("&nbsp;<input type='radio' name='judge_"+j+"'value='0'>错误"));
                        judgeDiv.append(ul);
                    }
                }
                $("#content").show();
            },
            async : true
        });
    }


});



var url=ctx + "/userInfo/saveTest.action";
//保存方法
function saveTest(){
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
            var data = new Object();//提交到后台的数据
            data.userName = $("#userName").val();
            data.telephone = $("#telephone").val();
            data.openid = $("#openid").val();
            data.activityId = $("#activityId").val();
            var list = new Array();//各个题目的答案
            //选择题信息
            for(var index = 1;index <=i;index++){
                var obj = new Object();
                obj.questionId = $("#choice_id_"+index).val();//问题id
                obj.questionType = 1;//问题类型1
                obj.userAnswer = $('input[name="choice_'+index+'"]:checked').val();//用户答案
                list.push(obj);
            }
            //判断题信息
            for(var index = 1;index <=j;index++){
                var obj = new Object();
                obj.questionId = $("#judge_id_"+index).val();//问题id
                obj.questionType = 2;//问题类型2
                obj.userAnswer = $('input[name="judge_'+index+'"]:checked').val();//用户答案
                list.push(obj);
            }
            data.list = JSON.stringify(list);

            $.ajax({
                type : "post",
                dataType : 'json',
                data:data,
                url : ctx+"/answerDetail/saveTestDetail.action?ids=" + Math.random(),

                success : function(result) {
                    if(result.result){
                        newShow("提交成功");
                        window.location.href=ctx + "/showScore.jsp?openid=" + $("#openid").val();
                    }else{
                        alert(result.errorMsg);
                        window.location.href = ctx + "/error.jsp";//跳到错误页面
                    }
                },
                async : false
            });
        }
    });
}