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

			
			<form action=<c:if test="${type eq '整车'}">"/stock/inventorySubmit.html"</c:if><c:if test="${type eq '配件'}">"/stock/pinventorySubmit.html"</c:if> method="post">
				<s:token/>
				<div class="chithead"> 
					<p class="title">当前${type}库存</p>	
				</div>
				<div class="left">	
						<c:if test="${! dealerUser}">
						<p>经销商																	
							<input type="text" value="${dealerShortName}" class="short_input" readonly />
							<input type="submit" name="method:unitList" value="选择经销商" />							
						</p>
						</c:if>
						<c:if test="${dealerUser}">
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
						</c:if>
						
						<p>显示零库存													
							<select name="viewZero" class="short_input">
								<option value="false" <c:if test="${! viewZero}">selected</c:if> >不显示</option>
								<option value="true" <c:if test="${viewZero}">selected</c:if> >显示</option>																
							</select>							
						</p>
						&nbsp;&nbsp;&nbsp;&nbsp;
						编码：
						<input name="page.propsNameList" value="productCode" type="hidden" />
						<input name="page.propsValueList" value="${page.propsValueList[0]}" type="text" class="short_input"/>			
						&nbsp;&nbsp;&nbsp;&nbsp;
						名称：
						<input name="page.propsNameList" value="productName" type="hidden" />
						<input name="page.propsValueList" value="${page.propsValueList[1]}" type="text" class="short_input"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" name="submit" value="筛选">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" name="method:exportExcel" value="导出Excel">
					</div>
				<div class="chithead">	
					<p>
						<font size="3">
						${type}库存数量:${totalQty}								
						&nbsp;&nbsp;&nbsp;&nbsp;
						<c:if test="${viewPrice}">${type}库存金额:<fmt:formatNumber value="${total}" pattern='#,##0.00'/></c:if>							
						</font>											
					</p>
				</div>
				
				<table class="chitdetail" >					
					<thead>
						<tr>
							<th width="3%" nowrap>选项</th>
							<th width="3%" nowrap>行号</th>
							<th  nowrap>门店</th>
							<th  nowrap>仓库</th>
							<th  nowrap>商品编码</th>
							<th  nowrap>商品名称</th>
							<th  nowrap>商品颜色</th>
							<th  nowrap>库存数量</th>
							<c:if test="${viewPrice}"><th  nowrap>平均单价</th></c:if>
							<c:if test="${viewPrice}"><th  nowrap>库存金额</th></c:if>							
						</tr>
					</thead>
					
					
					<tbody>
						<c:forEach var="inventory" items="${inventoryList}" varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >
								<td nowrap >
									<input type="radio" name="select" value="${num.index}">
								</td>
								<td nowrap class="sequence" >
									${num.count}
								</td>
								<td nowrap>
									${inventory.shopShortName}
								</td>
								<td nowrap>
									${inventory.warehouseName}
								</td>
								<td nowrap>
									${inventory.productCode}
								</td>
								<td nowrap>
									${inventory.productName}
								</td>
								<td nowrap>
									${inventory.productColor}
								</td>
								<td nowrap>
									${inventory.qty}
								</td>
								<c:if test="${viewPrice}">
								<td nowrap>
									<fmt:formatNumber value='${inventory.averagePrice}' pattern='#,##0.00'/>									
								</td>
								</c:if>
								<c:if test="${viewPrice}">
								<td nowrap>
									<fmt:formatNumber value='${inventory.total}' pattern='#,##0.00'/>									
								</td>
								</c:if>
							</tr>
						</c:forEach>						
					</tbody>
				</table>
				
				<table class="operate">

					<tr>
						<td align="right">
							<input type="submit" name="submit" value="查看明细"/>
						</td>
					</tr>

				</table>
							
				<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
			</form>
				
		</div>	


	</body>
</html>