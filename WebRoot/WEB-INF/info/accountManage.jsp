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
			<form action="/info/accountManage.html" method="post">
			<s:token/>
				<div class="chithead">
					<p class="title">
						账户管理
					</p>
				</div>
				<div class="chithead">
					<p>
						&nbsp;&nbsp;类型
						<select name="type">
							<option value="所有类型"
								<c:if test="${type == '所有类型'}">selected="selected"</c:if>>
								所有类型
							</option>

							<option value="应收"
								<c:if test="${type == '应收'}">selected="selected"</c:if>>
								应收
							</option>
							<option value="应付"
								<c:if test="${type == '应付'}">selected="selected"</c:if>>
								应付
							</option>
						</select>
						<input type="submit" name="submit" value="筛选">
					</p>


				</div>
				<br>
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
								类型
							</th>
							<th nowrap>
								总账户名称
							</th>
							<th nowrap>
								是否有效
							</th>
						</tr>
					</thead>


					<tbody>
						<c:forEach var="account" items="${accountList}" varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>
								<td nowrap>
									<!--  <input type="checkbox" name="selectList" value="${account.id}">
									-->
									<input type="radio" name="select" value="${num.index}">
								</td>
								<td nowrap class="sequence">
									${num.count}
								</td>
								<td nowrap>
									${account.type}
								</td>
								<td nowrap>
									${account.dealingUnitShortName}
								</td>
								<td nowrap>
									<c:if test="${account.disable==true}">有效</c:if>
									<c:if test="${account.disable==false}">无效</c:if>
								</td>

							</tr>
						</c:forEach>

					</tbody>

				</table>

				<table class="operate">

					<tr>
						<td>
							<input type="submit" name="submit" value="开户" />
							<input type="submit" name="submit" value="停用" />
							<input type="submit" name="submit" value="启用" />
							<input type="submit" name="submit" value="账户明细" />
							<input type="submit" name="submit" value="查看子账户类型" />
							<span class="errors">${errors['start'][0]}</span>
							<span class="errors">${errors['stop'][0]}</span>
						</td>

					</tr>

				</table>
				<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
			</form>
		</div>

	</body>
</html>


