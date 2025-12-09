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

			
			<form action="/info/employeeUpdate.html" method="post" >
				<s:token/>
				<div class="chithead"> 
					<p class="title">员工信息管理--修改</p>
				</div>
				<table class="chitbody" cellspacing="4">
					<tr><td>						
						<p>员工姓名</p>												
						<input type="text" name="employee.name" value="${employee.name}" />*	
						<span class="errors">${errors['employee.name'][0]}</span>					
					</td></tr>					
					<tr><td>						
						<p>员工头衔</p>												
						<select name="employee.title" >*
				            <option value="会计" <c:if test="${employee.title == '会计'}">selected</c:if> >会计</option>
				            <option value="录单员" <c:if test="${employee.title == '录单员'}">selected</c:if> >录单员</option>
				            <option value="销售员" <c:if test="${employee.title == '销售员'}">selected</c:if> >销售员</option>
				            <option value="其他" <c:if test="${employee.title == '其他'}">selected</c:if> >其他</option>
				        </select>					
					</td></tr>	
					<tr><td>						
						<p>所属门店</p>
						<input type="hidden" name="employee.shopId" value="${employee.shopId}" />												
						<input type="text" name="employee.shopShortName" value="${employee.shopShortName}" readonly />*	
						<input type="submit" name="submit" value="选择门店" />	
						<span class="errors">${errors['employee.unit'][0]}</span>						
					</td></tr>	
					<tr><td>						
						<p>电话</p>
						<input type="text" name="employee.phone" value="${employee.phone}" />					
					</td></tr>	
					<tr><td>						
						<p>地址</p>												
						<input type="text" name="employee.address" value="${employee.address}" />						
					</td></tr>	
					<tr><td>						
						<p>备注</p>												
						<input type="text" name="employee.remark" value="${employee.remark}" />					
					</td></tr>						
					<tr><td>						
						<p>状态</p>												
						<input type="radio" name="employee.disable" value="true" <c:if test="${employee.disable}">checked</c:if> />有效<br/>
				        <input type="radio" name="employee.disable" value="false" <c:if test="${! employee.disable}">checked</c:if> />无效			
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


