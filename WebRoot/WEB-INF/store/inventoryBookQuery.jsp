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
		<div class="container">
			<form action=<c:if test="${type eq '整车'}">"/stock/inventoryBookQuery.html"</c:if><c:if test="${type eq '配件'}">"/stock/pinventoryBookQuery.html"</c:if> method="post">
				<s:token/>
				<div class="chithead"> 
					<p class="title">查询条件</p>
				</div>				
				<table class="chitbody" cellspacing="4">					
					<tr><td>						
						<p>门店</p>		
						<select name="shopId" class="short_input">
							<option value="0" <c:if test="${shopId==0}">selected</c:if> >所有门店</option>
							<c:forEach var="shop" items="${shopList}"> 
           						<option value="${shop.id}" <c:if test="${shopId==shop.id}">selected</c:if> >${shop.shortName} </option> 
          					</c:forEach>	
						</select>	
					</td></tr>					
					<tr><td>	
						<p>仓库</p>
						<select name="warehouseId" class="short_input">
							<option value="0" <c:if test="${warehouseId==0}">selected</c:if> >所有仓库</option>
							<c:forEach var="warehouse" items="${warehouseList}"> 
           						<option value="${warehouse.id}" <c:if test="${warehouseId==warehouse.id}">selected</c:if> >${warehouse.name} </option> 
          					</c:forEach>								
						</select>				
					</td></tr>
					<tr><td>						
						<p>开始日期</p>												
						<input name="startDate" value="${startDate}" id="startDate" class="short_input" readonly />
					</td></tr>
					<tr><td>						
						<p>结束日期</p>												
						<input name="endDate" value="${endDate}" id="endDate" class="short_input" readonly />
					</td></tr>
				</table>				
				<table>					
					<tr >						
						<td align="right">
							<input type="submit" name="submit" value="确定" />
							<input type="submit" name="submit" value="重置" />
							<input type="submit" name="submit" value="退出" />							
						</td>
					</tr>						
				</table>				
			</form>
				
		</div>	
		
	<script type="text/javascript">
	function startDateInput(){
		var element = document.getElementById("startDate");
		element.onfocus = function(){
			WdatePicker();
		}
	}	

	function endDateInput(){
		var element = document.getElementById("endDate");
		element.onfocus = function(){
			WdatePicker();
		}
	}

	window.onload = function(){
		startDateInput();
		endDateInput();		
	}
	</script>
			
	</body>
</html>

