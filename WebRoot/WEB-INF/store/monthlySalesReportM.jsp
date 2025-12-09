<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
	<head>
		<meta name="decorator" content="${ajax}" />
	</head>

	<body>
		<div class="container">
			<form action="/report/salesReport.html" method="post">
			<s:token/>
				<div class="chithead">
					<p class="title">
						整车销售月报
					</p>
				</div>
				<div class="chithead">
					<p>
						年份：
						<input name="reportYear"
							value="${reportYear}" type="text" class="short_input" />
						月份：
						<input name="reportMonth" value="${reportMonth}" type="text" class="short_input"/>
						
						<input type="submit" name="submit" value="生成报表" />
						<span class="errors">${errors['date'][0]}</span>
					</p>
				</div>
			</form>
		</div>
	</body>
</html>