<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
<head>
	<meta name="decorator" content="${ajax}pop-up" />
	<!--  <script type="text/javascript" src="/js/sys/unitList.js"></script>-->
</head>
<body>
	<div class="container">
	<form>
	<s:token/>
	<div class="chithead"> 
		<p class="title">配件发货通知单信息</p>	
		<div class="right">
				<p><a href="${page.listAct}?select=-1">返回</a></p>
			</div>			
	</div>
	<table class="chitdetail" align="center">
		<thead>
			<tr>
				<th width="3%" nowrap>选择</th>
				<th width="3%" nowrap>行号</th>
				<th  nowrap>发货通知单编码</th>
				<th  nowrap>经销商</th>
				<th  nowrap>发货仓库</th>
				<th  nowrap>订购日期</th>				
			</tr>
		</thead>
	
		<c:forEach items="${partShipOrderList}" var="partShipOrder"  varStatus="num">
			<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >
				<td nowrap>
					<a href="${page.listAct }?select=${num.index}">pick</a>
				</td>
				<td nowrap class="sequence">
					${num.count}
				</td>
				<td nowrap>
					${partShipOrder.shipOrderCode}
				</td>
				<td nowrap>
					${partShipOrder.dealerCode}
				</td>
				<td nowrap>
					${spartShipOrder.warehouseCode}
				</td>
				<td nowrap>
					${partShipOrder.orderDate}
				</td>				
			</tr>
		</c:forEach>
	
	</table>	
	<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
	</form>	
	</div>
</body>
</html>