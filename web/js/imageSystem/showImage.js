var page = 1;//页码
function searchImage(isClickSearch){
    //如果是点击查询按钮则重置页码
    if(isClickSearch){
        page = 1;
    }
    $("#content").html("");//清空图片显示区域
    $.ajax({
        type : "post",
        data : {userName:$("#userName").val()
                ,telephone:$("#telephone").val()
                ,uploadTimeBegin:$("#uploadTimeBegin").datebox('getValue')
                ,uploadTimeEnd:$("#uploadTimeEnd").datebox('getValue')
                ,"activityId" : $('#activityId').combobox('getValue')
                ,page:page},
        dataType : 'json',
        url : ctx+"/userImg/searchImage.action?ids=" + Math.random(),

        success : function(data) {
            var total = data.total;//总记录数
            var rows = data.rows;//记录
            var isHasPre = data.isHasPre;//是否显示上一页按钮
            var isHasNext = data.isHasNext;//是否显示下一页按钮

            $("#total").html(total);
            if(isHasPre){
                $("#lastPage").show();
            }else{
                $("#lastPage").hide();
            }
            if(isHasNext){
                $("#nextPage").show();
            }else{
                $("#nextPage").hide();
            }

            var $content = $("#content");//图片显示区域
            for(var i = 0; i < rows.length; i++){
                var image = rows[i];
                var $div = $("<div class='picture'></div>");//图片div
                $content.append($div);

                var $picDiv = $("<div class='picDiv'></div>");
                var $img = $("<img src='"+ctx + "/userImg/getImgByUrl.action?imgUrl=" +image.imgUrl+"' onclick='detail(\""+image.imgUrl+"\")'>");//图片
                $picDiv.append($img);
                $div.append($picDiv);
                var $userName = $("<p>姓名："+image.userName+"</p>");//姓名
                $div.append($userName);
                var $telephone = $("<p>电话："+image.telephone+"</p>");//电话
                $div.append($telephone);
                var $uploadTime = $("<p>"+image.uploadTime+"</p>");//上传时间
                $div.append($uploadTime);

                var $deleteBtn = $("<a href='#' style='color:red' onclick='deleteImge("+image.userImgId+")'>删除</a>");
                $div.append($deleteBtn);

                var $downLoadBtn = $("<a href='"+ctx + "/userImg/getImgByUrl.action?imgUrl=" +image.imgUrl+"' style='color:red' download='"+image.imgName+"'>下载</a>");
                $div.append($downLoadBtn);
            }
        },
        async : true
    });
}

//上一页
function lastPage(){
    page--;
    searchImage(false);
}
//下一页
function nextPage(){
    page++;
    searchImage(false);
}

function deleteImge(userImgId){
    $.messager.confirm('Confirm', '您确定要删除该图片吗?', function (r) {
        if (r) {
            $.post(ctx + '/userImg/deleteById.action', { id: userImgId }, function (result) {
                if (result.success) {
                    newShow("删除成功");
                    searchImage(true);
                } else {
                    newAlert(result.errorMsg);
                }
            }, 'json');
        }
    });
}

//查看大图
function detail(imgUrl){
    $("#dlg").dialog("open").dialog("setTitle","查看图片");
    $("#detailImg").attr("src",ctx + "/userImg/getImgByUrl.action?imgUrl=" +imgUrl);
}