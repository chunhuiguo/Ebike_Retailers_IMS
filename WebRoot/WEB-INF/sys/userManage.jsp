<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
	<head>
		<meta name="decorator" content="${ajax}" />
	</head>

	<body>
		<div class="container">
		
			<form method="post">
				<div class="chithead"> 
					<p class="title">用户管理</p><br/>&nbsp;&nbsp;<font color="blue">点击每行右侧的图标，可修改用户信息或为该用户进行授权，对管理员账号不能进行授权操作。<!--${msg}--></font>
					
				</div>
			
				<table class="chitdetail" >
					<thead>
						<tr align="center" >
							<th align="center" width="35px" nowrap>行号</th>
							<th  nowrap>用户账号</th>
							<th  nowrap>用户姓名</th>
							<th  nowrap>用户类型</th>
							<th  nowrap>用户权限</th>
							<th  nowrap>是否有效</th>
							<th  nowrap>所属单位</th>
							<th align="center" width="35px"  nowrap>编辑</th>
							<th align="center" width="35px"  nowrap>授权</th>
							<th align="center" width="70px" nowrap>重置密码</th>
						</tr>
					</thead>
					
					
					<tbody>
						<c:forEach var="detail" items="${userList}" varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >
								<td nowrap class="sequence"  align="center">
									${num.count}
								</td>
								<td nowrap align="center">
									${detail.userCode}
									<!--  input type="text" name="detail.userCode" value="" -->
								</td>
								<td nowrap  align="center">
									${detail.userName}
								</td>
								<td nowrap  align="center">
									${detail.userType}
								</td>
								<td nowrap  align="center">
									${userRoles[num.index]}
								</td>
								<td nowrap align="center">
									${detail.enabled}
								</td>
								<td nowrap  align="center">
									${detail.companyCode}
								</td>
								<td align="center" nowrap>
									<a href="userEdit.html?select=${num.index}">
									<img src="/images/edit.gif" alt="点击修改用户信息"/></a>
								</td>									
								<td nowrap>
									<c:if test="${! (detail.userType eq '管理员')}">
									<a href="userRole.html?select=${num.index}">
									<img src="/images/op.jpg" alt="点击为该用户授权" />
									</a>
									</c:if>
									<c:if test="${detail.userType eq '管理员'}">									
									<img src="/images/op.jpg" />									
									</c:if>
								</td>
								<td nowrap>
									<c:if test="${! (detail.userType eq '管理员')}">
									<a href="userManage!resetPassword.html?select=${num.index}">重置密码</a>
									</c:if>
									<c:if test="${detail.userType eq '管理员'}">									
									重置密码									
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</form>
				
		</div>	

	</body>
</html>