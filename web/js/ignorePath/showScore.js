$(function(){
    $.ajax({
        type : "post",
        data : {openid:$("#openid").val()},
        dataType : 'json',
        url : ctx+"/userInfo/loadUserInfoByOpenid.action?ids=" + Math.random(),

        success : function(data) {
            if(data.success){
                var userInfo = data.userInfo;
                $("#userInfoId").val(userInfo.userInfoId);
                $("#userName").text(userInfo.userName);
                $("#telephone").text(userInfo.telephone);
                $("#score").text(userInfo.score);
                $("#testTime").text(userInfo.testTime);
            }else{
                newAlert(data.errorMsg);
            }
        },
        async : true
    });

});



function showDetail(){
    var userInfoId = $("#userInfoId").val();
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
            choiceDiv.html("<ul><li>一、选择题</li></ul>");
            var judgeDiv = $("<div>");//判断题区域
            judgeDiv.html("<ul><li>二、判断题</li></ul>");
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
                    seccondUl.append($("<li></li>").html("&nbsp;A" + data.keya));
                    seccondUl.append($("<li></li>").html("&nbsp;B" + data.keyb));
                    seccondUl.append($("<li></li>").html("&nbsp;C" + data.keyc));
                    seccondUl.append($("<li></li>").html("&nbsp;D" + data.keyd));
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