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
		<form name="addUserForm" action="/sys/addNew.html" method="post" >
			<s:token/>
			<div class="chithead"> 
				<p align="center" class="title">新建经销商用户</p>
				
			</div>
				<p align="center" > <font color="blue">提示:您可以建立经销商或下属门店的操作员用户。</font></p></br>
				
			
			<table cellspacing="8" align="center" style="width:700px;TABLE-LAYOUT:fixed">

            <tr style="height:40px">
                <td align="right"  style="width:230px">用户账号：</td>
                <td>
                    <input id="txtUserCode" name="user.userCode" value="${user.userCode}" maxlength="20" title="账号不要使用中文" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" style="width:200px;" />
                </td>
                <td>
                    <input type="submit" name="submit" value="检查" title="检查该账户是否已存在" />  
                </td>
            </tr>

            <tr style="height:40px">
                <td align="right">用户姓名：</td>
                <td>
                    <input name="user.userName" value="${user.userName}" maxlength="20" style="width:200px;" />  
                </td><td>&nbsp;</td>
            </tr>
            
            <tr style="height:40px">
                <td align="right">用户密码：</td>
                <td>
                    <input name="user.password" value="${user.password}" readonly="readonly" style="width:200px;" />
                </td><td> &nbsp; </td>
            </tr>
            <tr style="height:40px">
                <td align="right">用户类别：</td>
                <td>
                    <input name="user.userType" value="${user.userType}" readonly="readonly" style="width:200px;" />
                </td><td> &nbsp; </td>
            </tr>
            <tr style="height:40px">
                <td align="right">是否有效：</td>
                <td align="center">
                    <input type="checkbox" name="isEnabled" value="yes" checked="${user.enabled}"/>用户有效
                </td >
                <td>  &nbsp; </td>
            </tr>
            <tr style="height:40px">
                <td align="right">所属公司：</td >
                <td>
                     <select name="select" style="width:185px;">
						<c:forEach var="unitSelect" items="${unitList}"> 
           					<option value="${unitSelect.shortName}" <c:if test="${select == unitSelect.shortName}">selected</c:if> > 
            					${unitSelect.shortName} 
           					</option> 
          				</c:forEach>
					</select>
                </td>
                <td>&nbsp; </td>
            </tr>
            
             <tr style="height:40px">
                <td align="right"  style="width:230px">使用员工：</td>
                <td>
                    <input type="hidden" name="employee.id" value="${employee.id}" />
                    <input name="employee.name" value="${employee.name}" maxlength="20" readonly style="width:200px;" />
                </td>
                <td>
                    <input type="submit" name="submit" value="选择员工"  />  
                    <span class="errors">${errors['employee'][0]}</span>
                </td>
            </tr>
            
        	</table>
        	
        	<table cellspacing="8" align="center" style="width:700px;TABLE-LAYOUT:fixed" >
	        	<tr>
	            	<td style="width:65%">
	            		<font color="blue">${msg}</font>
	            	</td>
	            	<td align="left">
	            		<input type="submit" name="submit" value="保存" style="display:${disSave}}"/> &nbsp;&nbsp;
						<input type="submit" name="submit" value="取消"/>
	            	</td>
	            </tr>
	        </table>
	    	
	    </form>
		</div>
	</body>
</html>

