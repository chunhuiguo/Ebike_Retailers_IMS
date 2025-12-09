<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
	<head>
		<title><decorator:title default="错误" /></title>
		<decorator:head />
		
	</head>

	<body>

		<div class="screen">
			
			
			<decorator:body />
			
			
		</div>
		
	</body>
</html>