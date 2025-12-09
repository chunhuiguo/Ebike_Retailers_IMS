<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
<head>	
	<meta name="decorator" content="${ajax}pop-up" />	
</head>
<body>
	<div class="container">
	<form method="post">	
	<s:token/>	
	<div class="chithead"> 
		<p class="title">整车发货通知单明细信息</p>
		<div class="right">			
			<p><a href="/sys/shipOrder!select.html">返回</a></p>
		</div>				
	</div>
	
	<div class="chithead">	
		<p>
			经销商编码：			
			<input value="${dealerShortName}" type="text" class="short_input" disabled/>			
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			发货单编码：			
			<input value="${shipOrderCode}" type="text" class="short_input" disabled/>					
		</p>		
	</div>
	
	<table class="chitdetail" align="center">
		<thead>
			<tr>				
				<th width="3%" nowrap>行号</th>
				<th  nowrap>整车条码</th>
				<th  nowrap>整车编码</th>
				<th  nowrap>整车名称</th>
				<th  nowrap>整车颜色</th>				
			</tr>
		</thead>
	
		<c:forEach items="${productList}" var="product"  varStatus="num">
			<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >				
				<td nowrap class="sequence">
					${num.count}
				</td>
				<td nowrap>
					${productBarcodes[num.index]}
				</td>
				<td nowrap>
					${product.code}
				</td>
				<td nowrap>
					${product.prefixName}&nbsp;${product.suffixName}
				</td>
				<td nowrap>
					${product.colorName}
				</td>				
			</tr>
		</c:forEach>
	
	</table>	
	</form>	
	</div>
</body>
</html>