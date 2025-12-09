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
</head>
<body>
	<form action="/report/warehouseForDay.html" method="post">
	<s:token/>
	<div class="chithead"> 
		<p class="title">仓库信息</p>				
	</div>
	<table class="chitdetail" cellspacing="2"  align="center">
		<thead>
			<tr>
				<th width="8%" nowrap>选项</th>
				<th width="8%" nowrap>行号</th>
				<th  nowrap>仓库名称</th>
				<th  nowrap>仓库地址</th>
				<th  nowrap>备注</th>								
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${warehouseList}" var="warehouse"  varStatus="num">
			<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >
				<td nowrap>
					<input type="radio" name="selectId" value="${warehouse.id}">
				</td>
				<td nowrap class="sequence">
					${num.count}
				</td>
				<td nowrap>
					${warehouse.name}
				</td>
				<td nowrap>
					${warehouse.address}
				</td>
				<td nowrap>
					${warehouse.remark}
				</td>								
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<table>
	<tr>
		<td align="right">
			<input type="submit" name="submit" value="确定"/>
			<input type="submit" name="submit" value="退出"/>
		</td>
		</tr>
	</table>
	<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
	</form>	

</body>
</html>