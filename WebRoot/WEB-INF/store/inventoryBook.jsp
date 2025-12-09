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

			
			<form action=<c:if test="${type eq '整车'}">"/stock/inventoryBook.html"</c:if><c:if test="${type eq '配件'}">"/stock/pinventoryBook.html"</c:if> method="post">
				<s:token/>
				<div class="chithead"> 
					<p class="title">${type}库存台账</p>	
					<br/>
					[日期：${startDate}至${endDate}]		
				</div>
				<div class="left">
						<p>门店														
							<select name="shopId" class="short_input">
								<option value="0" <c:if test="${shopId==0}">selected</c:if> >所有门店</option>
								<c:forEach var="shop" items="${shopList}"> 
           							<option value="${shop.id}" <c:if test="${shopId==shop.id}">selected</c:if> >${shop.shortName} </option> 
          						</c:forEach>	
							</select>							
						</p>
						
						<p>仓库													
							<select name="warehouseId" class="short_input">
								<option value="0" <c:if test="${warehouseId==0}">selected</c:if> >所有仓库</option>
								<c:forEach var="warehouse" items="${warehouseList}"> 
           							<option value="${warehouse.id}" <c:if test="${warehouseId==warehouse.id}">selected</c:if> >${warehouse.name} </option> 
          						</c:forEach>								
							</select>							
						</p>
						<p>
							编码：
							<input name="page.propsNameList" value="productCode" type="hidden" />
							<input name="page.propsValueList" value="${page.propsValueList[0]}" type="text" class="short_input"/>	
							&nbsp;&nbsp;&nbsp;&nbsp;
							名称：
							<input name="page.propsNameList" value="productName" type="hidden" />
							<input name="page.propsValueList" value="${page.propsValueList[1]}" type="text" class="short_input"/>		
							&nbsp;&nbsp;&nbsp;&nbsp;
							颜色：
							<input name="page.propsNameList" value="productColor" type="hidden" />
							<input name="page.propsValueList" value="${page.propsValueList[2]}" type="text" class="short_input"/>							
						</p>
						<input type="submit" name="submit" value="筛选">	
						&nbsp;&nbsp;&nbsp;&nbsp;	
						<input type="submit" name="submit" value="设置查询条件"/>
						&nbsp;&nbsp;&nbsp;&nbsp;	
						<input type="submit" name="method:exportExcel" value="导出Excel"/>					
				</div>
					
					
	
				<table class="chitdetail" >					
					<thead>
						<tr>
							<th width="3%" nowrap>选项</th>
							<th width="3%" nowrap>行号</th>
							<th  nowrap>门店</th>
							<th  nowrap>仓库</th>
							<th  nowrap>商品编码</th>
							<th width="10%" nowrap>商品名称</th>
							<th  nowrap>商品颜色</th>
							<th width="10%" nowrap>单据编码</th>
							<th  nowrap>单据类型</th>
							<th  nowrap>单据日期</th>							
							<th  nowrap>入库数量</th>
							<c:if test="${viewPrice}"><th  nowrap>入库金额</th></c:if>
							<th  nowrap>出库数量</th>
							<c:if test="${viewPrice}"><th  nowrap>出库金额</th></c:if>	
							<th  nowrap>库存数量</th>
							<c:if test="${viewPrice}"><th  nowrap>库存金额</th></c:if>							
						</tr>
					</thead>
					
					
					<tbody>
						<c:forEach var="inventoryBookView" items="${inventoryBookViewList}" varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >
								<td nowrap >
									<input type="radio" name="select" value="${num.index}">
								</td>
								<td nowrap class="sequence" >
									${num.count}
								</td>
								<td nowrap>
									${inventoryBookView.shopShortName}
								</td>
								<td nowrap>
									${inventoryBookView.warehouseName}
								</td>
								<td nowrap>
									${inventoryBookView.productCode}
								</td>
								<td nowrap>
									${inventoryBookView.productName}
								</td>
								<td nowrap>
									${inventoryBookView.productColor}
								</td>
								<td nowrap>
									${inventoryBookView.voucherCode}
								</td>
								<td nowrap>
									${inventoryBookView.voucherType}
								</td>
								<td nowrap>
									${inventoryBookView.voucherDate}
								</td>								
								<td nowrap>
									${inventoryBookView.inQty}
								</td>
								<c:if test="${viewPrice}">
								<td nowrap>
									<fmt:formatNumber value='${inventoryBookView.inTotal}' pattern='#,##0.00'/>									
								</td>
								</c:if>
								<td nowrap>
									${inventoryBookView.outQty}
								</td>
								<c:if test="${viewPrice}">
								<td nowrap>
									<fmt:formatNumber value='${inventoryBookView.outTotal}' pattern='#,##0.00'/>	
								</td>
								</c:if>
								<td nowrap>
									${inventoryBookView.qty}
								</td>
								<c:if test="${viewPrice}">
								<td nowrap>
									<fmt:formatNumber value='${inventoryBookView.total}' pattern='#,##0.00'/>	
								</td>
								</c:if>
							</tr>
						</c:forEach>						
					</tbody>
				</table>
				
				<table class="operate">

					<tr>
						<td align="right">
							<input type="submit" name="submit" value="查看单据"/>
													
						</td>
					</tr>

				</table>							
				<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
			</form>
				
		</div>	


	</body>
</html>