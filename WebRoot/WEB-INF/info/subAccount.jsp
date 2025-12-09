<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
	<head>
		<meta name="decorator" content="pop-up"  />
	</head>

	<body>


		<div class="container">


			<form action="/info/subAccountSelect.html" method="post">
			<s:token/>
			<div class="chithead">
					<p class="title">
						子账户信息
					</p>
			</div>

				<table class="chitdetail" >
					<thead>
						<tr>
							<th width="10%" nowrap>选项</th>
							<th width="10%" nowrap>行号</th>
							<th  nowrap>子账户名称</th>
							<th  nowrap>子账户余额</th>
						</tr>
					</thead>
					
					
					<tbody>
						<c:forEach var="subAccount" items="${subAccountList}"
							varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>
								<td nowrap>
									<input type="checkbox" name="selectList"
										value="${num.index}">
								</td>
								<td nowrap class="sequence">
									${num.count}
								</td>
								<td nowrap>
									${subAccount.name}
								</td>
								<td nowrap>
									<fmt:formatNumber value='${subAccount.balance}' pattern='#,##0.00'/>
								</td>
							</tr>
						</c:forEach>

					</tbody>			
				</table>
				<table class="operate">

					<tr>
				
						<td align="right">
						
							<input type="submit" name="submit" value="确定" />
					
							<input type="submit" name="submit" value="退出" />

						</td>
					</tr>

				</table>


			</form>
		</div>



	</body>
</html>


