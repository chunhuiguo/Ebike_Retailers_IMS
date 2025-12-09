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


			<form action="/info/accountAdd.html" method="post">
				<s:token/>
				<table class="chitbody" cellspacing="4">
					<div class="chithead">
						<p class="title">
							开户
						</p>
					</div>
					<tr>
						<td>

							<p>
								往来单位
							</p>
							<input type="hidden" name="account.dealingUnitId"
								value="${account.dealingUnitId}" />
							<input type="text" name="account.dealingUnitShortName"
								value="${account.dealingUnitShortName}" readonly />
							<span class="errors">${errors['account.dealingUnitId'][0]}</span>
							<input type="submit" name="submit" value="选择往来单位" />

							<c:if test="{account.id!=null}">
								<input type="hidden" name="account.id" value="${account.id}" />
							</c:if>

						</td>
					</tr>
					<tr>
						<td>
							<p>
								账户类型
							</p>
							<select name="account.type">
								<option value="应收"
									<c:if test="${account.type == '应收'}">selected="selected"</c:if>>
									应收
								</option>
								<option value="应付"
									<c:if test="${account.type == '应付'}">selected="selected"</c:if>>
									应付
								</option>
							</select>
						</td>
						<td>
							<input type="submit" name="submit" value="选择子账户" />
						</td>
					</tr>
				</table>
				<table>
					<thead>
						<tr>
							<th width="10%" nowrap>
								选项
							</th>
							<th width="10%" nowrap>
								行号
							</th>
							<th nowrap>
								子账户名称
							</th>
							<th nowrap>
								子账户余额
								<span class="errors">${errors['balance'][0]}</span>
							</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="subAccountType" items="${subAccountTypeList}"
							varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>

								<td nowrap>
									<input type="checkbox" name="selectList" value="${num.index}">
								</td>
								<td nowrap class="sequence">
									${num.count}
								</td>
								<td nowrap>
									${subAccountType.name}
									<input type="hidden" name="subAccountList[${num.index}].name"
										value="${subAccountType.name}" />
								</td>
								<td nowrap>
									<input type="text" name="subAccountList[${num.index}].balance"
										value="<fmt:formatNumber value='${subAccountList[num.index].balance}' pattern='#,##0.00'/>" />
								</td>
							</tr>
						</c:forEach>

					</tbody>

					<tr>
						<td>
							<input type="submit" name="submit" value="保存" />
							<span class="errors">${errors['save'][0]}</span>
						</td>
					</tr>
				</table>
			</form>
		</div>





	</body>
</html>


