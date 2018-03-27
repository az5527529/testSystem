<%@ page contentType="text/html;charset=utf-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/WEB-INF/pages/common/head.jsp"%>
<html>

<script type="text/javascript">

  $(function(){
    $("a[title]").click(function(){
      var text = $(this).text();
      var href = $(this).attr("title");
      addTab(text,href);
    });
  });
  function logout(){
    $.ajax({
      type : "post",
      url : "${ctx}/login/logout.action?ids=" + Math.random(),
      success : function(data) {
        window.location = 'login.jsp';;
      },
      async : true
    });
  }
</script>
<style type="text/css">
  #menu {
    width: 200px;
  }

  #menu ul {
    list-style: none;
    padding: 0;
    margin: 0;

  }

  #menu ul li {

    padding-left:20px;
    float:none;
  }
  #menu ul li:hover{
    background-color: #00a6ac;
  }
  #menu ul li a {
    display: block;
    color: #030303;
    padding: 5px;
    text-decoration: none;
  }

  #menu ul li a:hover {
    cursor: pointer;
  }
</style>
<body class="easyui-layout">
<div data-options="region:'north',title:'欢迎来到后台管理系统',split:true"
     style="height: 70px;">
  <b>欢迎您,${loginUser.userName}</b><br />登录时间:<%=new java.text.SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new java.util.Date()) %>
  <a href="#" onclick="logout()">退出
  </a>
</div>
<div data-options="region:'west',title:'系统菜单',split:true"
     style="width: 200px;">
  <div id="menu" class="easyui-accordion" data-options="fit:true">
    <div title="题目管理">
      <ul>
        <li><a href="#" title="${ctx}/pages/system/choiceQuestionManage.action">选择题管理</a></li>
        <li><a href="#" title="${ctx}/pages/system/judgeQuestionManage.action">判断题管理</a></li>
      </ul>
    </div>
    <div title="分数管理">
      <ul>
        <li><a href="#" title="${ctx}/pages/system/activityManage.action">活动管理</a></li>
        <li><a href="#" title="${ctx}/pages/system/testInfo.action">试题调整</a></li>
        <li><a href="#" title="${ctx}/pages/system/showTest.action">查看测试总体情况</a></li>
        <li><a href="#" title="${ctx}/pages/system/showTestDetail.action">查看测试情况</a></li>

      </ul>
    </div>
    <div title="图片管理">
      <ul>
        <li><a href="#" title="${ctx}/pages/imageSystem/showImage.action">查看图片</a></li>
      </ul>
    </div>
  </div>
</div>
<div data-options="region:'center',title:'操作页面'"
     style="padding: 1px; background: #fff;">
  <div id="tt" class="easyui-tabs" data-options="fit:true">
    <div title="系统首页" style="padding: 5px;">系统首页</div>
  </div>
</div>
</body>

</html>