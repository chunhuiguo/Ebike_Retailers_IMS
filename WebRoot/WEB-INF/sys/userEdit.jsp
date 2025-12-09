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
		
		<c:if test="${user.userType eq '管理员'}" var="admin"/>
		
		<form name="addUserForm" action="/sys/editUserInfo.html" method="post" >
			<s:token/>
			<div class="chithead"> 
				<p align="center" class="title">修改用户信息</p>
			</div>
				<p align="center" > 
					<font color="blue">
						<c:if test="${admin}">提示:管理员账号只能修改使用员工。</c:if>
						<c:if test="${! admin}">提示:您可以修改用户姓名、使用员工，或将用户置为无效用户。</c:if>
					</font>
				</p>
				</br>
				
			
			<table cellspacing="8" align="center" style="width:500px;TABLE-LAYOUT:fixed">
	            <tr style="height:40px">
	                <td align="right"  style="width:46%">用户账号：</td>
	                <td>${user.userCode}</td>
	            </tr>
	            <tr style="height:40px">
	                <td align="right">用户姓名：</td>
	                <td><input name="user.userName" value="${user.userName}" maxlength="20" <c:if test="${admin}">disabled</c:if> /></td>
	            </tr>
	            <tr style="height:40px">
	                <td align="right">用户类别：</td>
	                <td>${user.userType}</td>
	            </tr>
	            <tr style="height:40px">
	                <td align="right">是否有效：</td>
	                <td >
	                    <input type="checkbox" name="isEnabled" value="yes" checked="${user.enabled}" <c:if test="${admin}">disabled</c:if> />用户有效
	                </td >
	            </tr>
	            <tr style="height:40px">
	                <td align="right">所属公司：</td >
	                <td>${user.companyCode}</td>
	            </tr>
	            
	            <tr style="height:40px">
                <td align="right"  style="width:230px">使用员工：</td>
                <td>
                    <input type="hidden" name="employee.id" value="${employee.id}" />
                    <input name="employee.name" value="${employee.name}" maxlength="20" readonly style="width:100px;" />
                </td>
                <td>
                    <input type="submit" name="submit" value="选择员工"  />  
                    <span class="errors">${errors['employee'][0]}</span>
                </td>
            </tr>
        	</table>
        	
        	<table cellspacing="8" align="center" style="width:500px;TABLE-LAYOUT:fixed" >
	        	<tr>
	            	<td style="width:70%">
	            		<font color="blue">${msg}</font>
	            	</td>
	            	<td align="left">
	            		<input type="submit" name="submit" value="保存" /> &nbsp;&nbsp;
						<input type="submit" name="submit" value="取消"/>
	            	</td>
	            </tr>
	        </table>
	    	
	    </form>
        </div>
	</body>
</html>
