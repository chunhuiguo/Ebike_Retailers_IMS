<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
	<head>
		<meta name="decorator" content="${ajax}pop-up" />
	</head>
	
	<body>
		<div class="container">
		<form action="/sys/userManage.html" method="post" >
			<s:token/>
			<div class="chithead"> 
				<p align="center" class="title">密码重置</p>
				
			</div>
				<p align="center" > <font color="blue">重要提示:本操作是把所选择用户的密码重置为系统默认密码(NYYBDWDX)，重置后原密码失效，请谨慎使用！</font></p></br>
				
			
			<table cellspacing="8" align="center" style="width:700px;TABLE-LAYOUT:fixed">

            <tr style="height:40px">
                <td align="right"  style="width:230px">用户账号：</td>
                <td>
                    <input value="${user.userCode}" maxlength="20" style="width:200px;" disabled />
                </td>
            </tr>

            <tr style="height:40px">
                <td align="right">用户姓名：</td>
                <td>
                    <input value="${user.userName}" maxlength="20" style="width:200px;" disabled />  
                </td><td>&nbsp;</td>
            </tr>
            
            <tr style="height:40px">
                <td align="right">重置密码：</td>
                <td>
                    <input value="NYYBDWDX" style="width:200px;" disabled />
                </td><td> &nbsp; </td>
            </tr>
            
        	</table>
        	
        	<table cellspacing="8" align="center" style="width:700px;TABLE-LAYOUT:fixed" >
	        	<tr>
	            	<td style="width:65%">
	            		<font color="blue">${msg}</font>
	            	</td>
	            	<td align="left">
	            		<input type="submit" name="method:resetPasswordOK" value="确定" /> &nbsp;&nbsp;
						<input type="submit" name="" value="取消"/>
	            	</td>
	            </tr>
	        </table>
	    	
	    </form>
		</div>
	</body>
</html>

