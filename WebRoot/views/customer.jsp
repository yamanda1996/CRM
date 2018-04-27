<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户信息管理</title>
<link href="${pageContext.request.contextPath }/css/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.easyui.1.2.6.min.js"></script>
<script type="text/javascript" src='${pageContext.request.contextPath }/js/index.js'> </script>

<script type="text/javascript">
	 $(function(){
		$("#customer").datagrid({
			url:"${pageContext.request.contextPath }/customer_customerPageJson.action",  //返回json数据action的路径
			columns:[[
						{field:"cid",title: "客户编号"},
						{field:"cust_level",title: "用户等级"},
						{field:"cust_mobile",title: "电话"},
						{field: "cust_name",title: "姓名"}
					]] ,
			pagination: true,  //显示分页
			singleSelect: true  //可选择
		
		}) ;
	 });
</script>

</head>
<body>
	<table id="customer"></table>
</body>
</html>