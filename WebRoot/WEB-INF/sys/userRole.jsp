<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
		
			<form action="/sys/role.html" method="post" name="userRole">
				<s:token/>
				<div class="chithead"> 
					<p class="title">用户授权</p>
				</div>
			
			<p><font size="2" color="blue">待授权用户信息：</font></p>
				<table class="chitdetail" >
					<thead>
						<tr align="center" >
							<th  nowrap>用户账号</th>
							<th  nowrap>用户姓名</th>
							<th  nowrap>用户类型</th>
							<th  nowrap>所属单位</th>
						</tr>
					</thead>
					
					<tbody>
						<tr align="center">
							<td nowrap >${user.userCode}</td>
							<td nowrap  >${user.userName}</td>
							<td nowrap>${user.userType}</td>
							<td nowrap>${user.companyCode}</td>
					</tbody>
				</table>
				
			<hr/>
			<p><font size="2" color="blue">请选择为用户授权的角色：</font></p>
			
				<table class="chitdetail" >
					<thead>
						<tr align="center" >
							<th  width="35px" nowrap>选择</th>
							<th  nowrap>岗位名称</th>
							<th  nowrap>岗位类型</th>
							<th  width="45%" nowrap>岗位说明</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="role" items="${roleList}" varStatus="num">
							<tr align="center"<c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>   >
								<td nowrap>
									<input type="checkbox" name="selectList" value="${role.roleId}">
								</td>
								<td nowrap>${role.roleName}</td>
								<td nowrap>${role.roleType}</td>
								<td nowrap>${role.info}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<table cellspacing="8" align="center" style="width:800px;TABLE-LAYOUT:fixed" >
	        	<tr>
	            	<td style="width:70%">
	            		<font size="2" color="blue">${msg}</font>
	            	</td>
	            	<td align="left">
	            		<input type="submit" name="submit" value="保存" />
	            	</td>
	            	<td align="left">
						<input type="submit" name="submit" value="取消"/>
	            	</td>
	            </tr>
	        </table>

			</form>
				
		</div>	

	</body>
</html>