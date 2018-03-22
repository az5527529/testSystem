<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set value="${pageContext.request.contextPath}" var="ctx" />

<title></title>

<!-- easyui环境 -->
<link rel="stylesheet" href="${ctx}/jquery-easyui-1.4.4/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="${ctx}/jquery-easyui-1.4.4/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="${ctx}/css/common/common.css" type="text/css"></link>
<script type="text/javascript">
<!--
//-->
	var ctx="${ctx}";//定义项目路径
</script>
<script type="text/javascript" src="${ctx}/jquery-easyui-1.4.4/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
