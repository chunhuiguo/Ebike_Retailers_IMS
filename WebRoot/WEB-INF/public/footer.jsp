<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>

	<body>


		<div id="footer">
			<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
			<p><fmt:formatDate value="${now}" type="date" dateStyle="full"/></p>
			<p>当前操作员：${sessionScope.userName }</p>
			
		</div>


	</body>

</html>