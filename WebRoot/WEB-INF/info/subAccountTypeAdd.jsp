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

			<form action="/info/subAccountTypeAdd.html" method="post">
			<s:token/>
				<!-- 
				<table class="chitdetail" cellspacing="4">
					<tbody>
						<tr>
							<th width="30%" nowrap>
								已有的子账户类型:
							</th>
						</tr>
						<c:forEach var="subAccountType" items="${subAccountTypeList}"
							varStatus="num">
							<tr>
								<td nowrap>
									${subAccountType.name}
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				 -->
				<div class="chithead">
					<p class="title">
						添加子账户类型
					</p>
				</div>
				<table>
					<tr>
						<td>
							填写要添加的子账户名称
						</td>
						<td>
							<input type="text" name="subAccountType.name"
								value="${subAccountType.name}" />
							<span class="errors">${errors['subAccountType.name'][0]}</span>
						</td>
					</tr>
					<tr>
						<td>
							<input type="submit" name="submit" value="保存" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>


