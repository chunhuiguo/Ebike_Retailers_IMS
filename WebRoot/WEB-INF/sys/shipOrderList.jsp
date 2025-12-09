<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
<head>
	<c:if test="${redirect==null}" var="view"></c:if>
	<meta name="decorator" content="${ajax}<c:if test='${! view}'>pop-up</c:if>" />
	<script type="text/javascript" src="/js/sys/shipOrderList.js"></script>
</head>
<body>
	<div class="container">
	<form action="/sys/shipOrder.html" method="post">	
	<s:token/>	
	<div class="chithead"> 
		<p class="title">整车发货通知单信息</p>	
		<br/>
		[订购日期：${orderStartDate}至${orderEndDate}]
		<div class="right">
			<c:if test="${! view}">
				<p><a href="${redirect}">返回</a></p>
			</c:if>
		</div>				
	</div>
	
	<div class="chithead">	
		<p>
			经销商编码：			
			<input value="${dealerShortName}" type="text" class="short_input" disabled/>			
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			发货单编码：
			<input name="page.propsNameList" value="shipOrderCode" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[0]}" type="text" class="short_input"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			发货仓库：			
			<c:set var="array" value="所有仓库,浙江成品仓库,山东成品仓库" />						
			<select name="warehouseCode" class="short_input">			
				<c:forTokens items="${array}" delims="," var="warehouse"> 												
					<option value="${warehouse}" <c:if test="${warehouseCode eq warehouse}">selected</c:if> >${warehouse}</option>						
				</c:forTokens>
			</select>			
		</p>		
		<p>
			订&nbsp;购&nbsp;日&nbsp;期：			
			<input type="text" name="orderStartDate" value="${orderStartDate}" class="short_input" readonly id="date1" />	
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
			<input type="text" name="orderEndDate" value="${orderEndDate}" class="short_input" readonly id="date2" />							
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
			<input type="submit" name="method:select" value="筛选" />			
		</p>
	</div>
	
	<table class="chitdetail" align="center">
		<thead>
			<tr>
				<c:if test="${view}"><th width="3%" nowrap>明细</th></c:if>
				<c:if test="${! view}"><th width="3%" nowrap>选择</th></c:if>
				<th width="3%" nowrap>行号</th>
				<th  nowrap>发货通知单编码</th>
				<th  nowrap>经销商</th>
				<th  nowrap>发货仓库</th>
				<th  nowrap>订购日期</th>				
			</tr>
		</thead>
	
		<c:forEach items="${shipOrderList}" var="shipOrder"  varStatus="num">
			<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >
				<td nowrap>
					<c:if test="${view}"><a href="/sys/shipOrderDetail.html?select=${num.index}">明细</a></c:if>
					<c:if test="${! view}"><a href="${redirect}?select=${num.index}">选择</a></c:if>
				</td>
				<td nowrap class="sequence">
					${num.count}
				</td>
				<td nowrap>
					${shipOrder.shipOrderCode}
				</td>
				<td nowrap>
					${shipOrder.dealerCode}
				</td>
				<td nowrap>
					${shipOrder.warehouseCode}
				</td>
				<td nowrap>
					${shipOrder.orderDate}
				</td>				
			</tr>
		</c:forEach>
	
	</table>	
	<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
	</form>	
	</div>
</body>
</html>