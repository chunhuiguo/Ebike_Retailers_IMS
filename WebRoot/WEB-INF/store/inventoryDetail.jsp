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

			
			<form action=<c:if test="${type eq '整车'}">"/stock/inventoryDetail.html"</c:if><c:if test="${type eq '配件'}">"/stock/pinventoryDetail.html"</c:if> method="post">
				<s:token/>
				<div class="chithead"> 
					<p class="title">${type}库存明细</p>						
				</div>
				
				<div class="chithead">	
					<p>
						<font size="3">
						<c:if test="${type eq '整车'}">${product.prefixName}&nbsp;${product.suffixName}【${product.code}】&nbsp;&nbsp;&nbsp;&nbsp;${product.colorName}</c:if>
						<c:if test="${type eq '配件'}">${part.name}【${part.code}】&nbsp;&nbsp;&nbsp;&nbsp;${part.partColor}</c:if>											
						</font>											
					</p>
				</div>
	
				<table class="chitdetail" >					
					<thead>
						<tr>							
							<th width="3%" nowrap>行号</th>
							<th  nowrap>门店</th>
							<th  nowrap>仓库</th>							
							<th  nowrap>库存数量</th>
							<c:if test="${viewPrice}"><th  nowrap>单价</th></c:if>							
							<c:if test="${viewPrice}"><th  nowrap>库存金额</th></c:if>													
						</tr>
					</thead>
					
					
					<tbody>
						<c:forEach var="inventoryDetail" items="${inventoryDetailList}" varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >								
								<td nowrap class="sequence" >
									${num.count}
								</td>
								<td nowrap>
									${inventoryDetail.shopShortName}
								</td>
								<td nowrap>
									${inventoryDetail.warehouseName}
								</td>											
								<td nowrap>
									${inventoryDetail.qty}
								</td>
								<c:if test="${viewPrice}">
								<td nowrap>
									<fmt:formatNumber value='${inventoryDetail.price}' pattern='#,##0.00'/>									
								</td>
								<td nowrap>
									<fmt:formatNumber value='${inventoryDetail.total}' pattern='#,##0.00'/>									
								</td>
								</c:if>								
							</tr>
						</c:forEach>						
					</tbody>
				</table>
				
				<table class="operate">

					<tr>
						<td align="right">
							<input type="submit" name="method:exit" value="退出"/>							
						</td>
					</tr>

				</table>							
				<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
			</form>
				
		</div>	


	</body>
</html>