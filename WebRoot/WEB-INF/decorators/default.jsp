<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
	<head>
		<title><decorator:title default="绿源在线进销存" /></title>
		
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
	
		<link rel="stylesheet" type="text/css" href="/css/menu/menu.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="/css/common.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="/css/print.css" media="print"/>
		<link rel="stylesheet" type="text/css" href="/js/lib/tabdlg.css" media="screen"/>

		<script type="text/javascript" src="/js/lib/jquery-1.5.min.js"></script>
		<script type="text/javascript" src="/js/lib/tabPanel.min.js"></script>
		<script type="text/javascript" src="/js/lib/jquery.validate.min.js"></script>
		<script type="text/javascript" src="/js/lib/jquery.blockUI.min.js"></script>
		<script type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>
		
		<script type="text/javascript" src="/js/lib/luyuan.js"></script><!-- 
		<script type="text/javascript" src="/js/public/header.js"></script>--> 
	
		<decorator:head />
	</head>

	<body>

		<div id="wrap">
			<page:apply-decorator page="/WEB-INF/public/header.jsp" name="panel" />
			
			
			<div id="main">
				<decorator:body/>
			</div>
			
			
			<page:apply-decorator page="/WEB-INF/public/footer.jsp" name="panel" />
			
			
		</div>

	</body>
</html>