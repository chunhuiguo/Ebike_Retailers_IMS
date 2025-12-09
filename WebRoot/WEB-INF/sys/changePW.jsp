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
		<form name="changePW" action="/sys/changePW.html" method="post" >
			<s:token/>
			<div class="chithead"> 
				<p align="center" class="title">修改用户密码</p>
			</div>
			
			<table cellspacing="8" align="center" style="width:500px;TABLE-LAYOUT:fixed">
	            <tr style="height:40px">
	                <td align="right"  style="width:50%">请输入旧密码：</td>
	                <td><input type="password" name="oldPW" value="${oldPW}" maxlength="20" /></td>
	            </tr>
	            <tr style="height:40px">
	                <td align="right" >请输入新密码：</td>
	                <td><input type="password" name="new1" value="${new1}" maxlength="20" /></td>
	            </tr>
	            <tr style="height:40px">
	                <td align="right" >再次输入新密码：</td>
	                <td><input type="password" name="new2" value="${new2}" maxlength="20" /></td>
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
