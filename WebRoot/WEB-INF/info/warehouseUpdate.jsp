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

			
			<form action="/info/warehouseUpdate.html" method="post" >
				<s:token/>
				<div class="chithead"> 
					<p class="title">仓库信息管理--修改</p>
				</div>
				<table class="chitbody" cellspacing="4">
					<tr><td>						
						<p>仓库名称</p>												
						<input type="text" name="warehouse.name" value="${warehouse.name}" />*		
						<span class="errors">${errors['warehouse.name'][0]}</span>					
					</td></tr>						
					<tr><td>						
						<p>所属门店</p>
						<input type="hidden" name="warehouse.shopId" value="${warehouse.shopId}" />												
						<input type="text" name="warehouse.shopShortName" value="${warehouse.shopShortName}" readonly />*	
						<input type="submit" name="submit" value="选择门店" />	
						<span class="errors">${errors['warehouse.unit'][0]}</span>					
					</td></tr>						
					<tr><td>						
						<p>地址</p>												
						<input type="text" name="warehouse.address" value="${warehouse.address}" />						
					</td></tr>	
					<tr><td>						
						<p>备注</p>												
						<input type="text" name="warehouse.remark" value="${warehouse.remark}" />					
					</td></tr>						
					<tr><td>						
						<p>状态</p>												
						<input type="radio" name="warehouse.disable" value="true" <c:if test="${warehouse.disable}">checked</c:if> />有效<br/>
				        <input type="radio" name="warehouse.disable" value="false" <c:if test="${! warehouse.disable}">checked</c:if> />无效			
					</td></tr>			
				</table>					
				
				<table class="operate">
					
					<tr >
						<td>		
						</td>
						
						<td align="right">
							
							<input type="submit" name="submit" value="保存"/>
							<input type="submit" name="submit" value="退出"/>

						</td>
					</tr>	
					
				</table>
				
				
			</form>
		</div>
		

	</body>
</html>


