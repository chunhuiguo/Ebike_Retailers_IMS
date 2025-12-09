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


			<form action="/money/payable.html" method="post">
			<s:token/>
				<div class="chithead">
					<p class="title">
						应付账
					</p>
				</div>

				<table class="chitdetail">
					<thead>
						<tr>
							<th width="10%" nowrap>
								选项
							</th>
							<th width="10%" nowrap>
								行号
							</th>
							<th nowrap>
								往来单位名称
							</th>
							<th nowrap>
								应付款
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="account" items="${accountList}" varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>
								<td nowrap>
									<input type="radio" name="select" value="${account.id}">
								</td>
								<td nowrap class="sequence">
									${num.count}
								</td>

								<td nowrap>
									${account.dealingUnitShortName}
								</td>
								<td nowrap>
									<fmt:formatNumber value='${account.balance}' pattern='#,##0.00' />
								</td>
							</tr>
						</c:forEach>

					</tbody>

				</table>

				<table class="operate">
					<tr>
						<td>
							<input type="submit" name="submit" value="查看明细" />
						</td>
					</tr>
				</table>
				<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
			</form>
		</div>


	</body>
</html>


