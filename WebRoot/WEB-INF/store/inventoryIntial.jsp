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
			<form action=<c:if test="${type eq '整车'}">"/stock/inventoryIntial.html"</c:if><c:if test="${type eq '配件'}">"/stock/pinventoryIntial.html"</c:if> method="post">
				<s:token/>
				<div class="chithead"> 
					<p class="title">${type}库存初始化</p>
				</div>					
				<table class="chitbody" cellspacing="4">
					<tr><td>						
						<p>商品编码</p>		
						<input type="hidden" name="inventory.productId" value="${inventory.productId}" readonly />										
						<input name="inventory.productCode" value="${inventory.productCode}" class="short_input" readonly />
						<input type="submit" name="submit" value="选择商品" />	
						<span class="errors">${errors['inventory.product'][0]}</span>					
					</td></tr>
					<tr><td>						
						<p>商品名称</p>																
						<input type="text" name="inventory.productName" value="${inventory.productName}" class="short_input" readonly/>						
					</td></tr>	
					<tr><td>	
						<p>商品颜色</p>					
						<input type="text" name="inventory.productColor" value="${inventory.productColor}" class="short_input" readonly/>											
					</td></tr>
					<tr><td>	
						<p>计量单位</p>						
						<input type="text" name="inventory.productUnit" value="${inventory.productUnit}" class="short_input" readonly/>						
					</td></tr>					
				</table>	
					<input type="submit" name="submit" value="添加仓库"  />										
					<input type="submit" name="submit" value="删除仓库"  />
					<input type="submit" name="submit" value="计算金额"  />	
					<p><span class="errors">${errors['inventoryDetail'][0]}</span></p>	
					<p><span class="errors">${errors['inventory'][0]}</span></p>								
				<table class="chitdetail" >
					<thead>
						<tr>							
							<th width="3%" nowrap>选项</th>							
							<th width="3%" nowrap>行号</th>
							<th  nowrap>仓库名称</th>							
							<th  nowrap>数量<span class="errors">${errors['inventoryDetail.qty'][0]}</span></th>							
							<th  nowrap>单价<span class="errors">${errors['inventoryDetail.price'][0]}</span></th>							
							<th  nowrap>金额</th>
												
							
						</tr>
					</thead>
					
					
					<tbody>					
						<c:forEach var="inventoryDetail" items="${inventoryDetailList}" varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >
								<td nowrap class="sequence">								
								<input type="checkbox" name="selectList" value="${num.index}" >														
								</td>
								<td nowrap class="sequence">
									${num.count}									
								</td>								
								<td nowrap>	
									<input type="hidden" name="inventoryDetailList[${num.index}].warehouseId" value="${inventoryDetail.warehouseId}">									
									<input type="text" name="inventoryDetailList[${num.index}].warehouseName" value="${inventoryDetail.warehouseName}" readonly>									
								</td>
								<td nowrap align="left">								
									<input type="text" name="inventoryDetailList[${num.index}].qty" value="${inventoryDetail.qty}">
								</td>														
								<td nowrap>									
									<input type="text" name="inventoryDetailList[${num.index}].price" value="<fmt:formatNumber value='${inventoryDetail.price}' pattern='#,##0.00'/>" >
								</td>								
								<td nowrap>
									<input type="text" name="inventoryDetailList[${num.index}].total" value="<fmt:formatNumber value='${inventoryDetail.total}' pattern='#,##0.00'/>" readonly>
								</td>
							</tr>
						</c:forEach>						
						<tr>
							<td colspan="2"></td>
							<td align="right">合计数量</td>
							<td>${inventory.qty}</td>
							<td>合计金额</td>
							<td><fmt:formatNumber value="${inventory.total}" pattern='#,##0.00'/></td>
						</tr>						
					</tbody>
				</table>

				
				<table>					
					<tr >					
						<td align="right">
							<input type="submit" name="submit" value="确定" />							
							<input type="submit" name="submit" value="放弃" />								
						</td>
					</tr>
					<tr >					
						<td align="center">
							<span class="errors">${errors['inventory.success'][0]}</span>														
						</td>
					</tr>					
				</table>				
			</form>				
		</div>	
	</body>
</html>