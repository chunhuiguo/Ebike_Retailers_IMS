<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>

	<body>
		<div class="page">
			
			${page.numbers}
			<c:if test="${page.tail gt 6 }">	
				<span class="ellipsis">转</span><input type="text" name="page.curStr" value="${page.curStr}"/>
				<a href="${page.listAct}?page.curStr=${page.curStr}">kkdkk</a>
				<!--  
				<input type="submit" value="GO" class="gobtn"/>--><span class="errors">${errors['page.curStr'][0]}</span>
			</c:if>
			
		</div>
	</body>

</html>