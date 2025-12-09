<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
<head>
	<meta name="decorator" content="pop-up" />
	<script type="text/javascript" src="/js/sys/userList.js"></script>
</head>
<body>
	<form action="/sys/userSelect.html" method="post">
	<s:token/>
	<div class="chithead"> 
		<p class="title">用户列表</p>				
	</div>
	<table class="chitdetail" cellspacing="2"  align="center">
		<thead>
			<tr>
				<th width="3%" nowrap>选项</th>
				<th width="3%" nowrap>行号</th>
				<th  nowrap>用户账号</th>
				<th  nowrap>用户姓名</th>				
			</tr>
		</thead>
	
		<c:forEach items="${userList}" var="unit"  varStatus="num">
			<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >
				<td nowrap>
					<input type="radio" name="select" value="${num.index}">					
				</td>
				<td nowrap class="sequence">
					${num.count}
				</td>
				<td nowrap>
					${user.userCode}
				</td>
				<td nowrap>
					${user.username}
				</td>				
			</tr>
		</c:forEach>
	
	</table>
	
	<table>
		<tr align="right"><td>
			<input type="submit" name="submit" value="确定"/>
			<input type="submit" name="submit" value="退出"/>
		</td></tr>
	</table>
	<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
	</form>	

</body>
</html>