<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
<head>
	<meta name="decorator" content="pop-up" />
</head>
<body>
	<div class="container">
	<form>
	<s:token/>
	<div class="chithead"> 
		<p class="title">仓库信息</p>
		<div class="right">
			<p><a href="${page.listAct}?select=-1">返回</a></p>
		</div>			
	</div>
	<table class="chitdetail" align="center">
		<thead>
			<tr>
				<th width="3%" nowrap>选择</th>
				<th width="3%" nowrap>行号</th>
				<th  nowrap>仓库名称</th>
				<th  nowrap>仓库地址</th>
				<th  nowrap>备注</th>								
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${warehouseList}" var="warehouse"  varStatus="num">
			<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >
				<td nowrap>
					<a href="${page.listAct }?select=${num.index}">选择</a>
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
	
	<!--  
	<table>
	<tr>
		<td align="right">
			<input type="submit" name="submit" value="确定"/>
			<input type="submit" name="submit" value="退出"/>
		</td>
		</tr>
	</table>
	-->
	<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
	</form>	
	</div>
</body>
</html>