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
			<form action="/money/course.html" method="post">
			<s:token/>
				<div class="chithead">
					<p class="title">
						账款单据历史
					</p>
					<br/>[日期：${startDate}至${endDate}]	
				</div>
				<div class="left">
					<p>
						单据类型
						<select name="accountType">
							<option value="所有单据"
								<c:if test="${accountType == '所有单据'}">selected="selected"</c:if>>
								所有单据
							</option>
							<option value="付款单"
								<c:if test="${accountType == '付款单'}">selected="selected"</c:if>>
								付款单
							</option>
							<option value="收款单"
								<c:if test="${accountType == '收款单'}">selected="selected"</c:if>>
								收款单
							</option>
						</select>
					</p>
					<p>
						冲抵过滤
						<select name="errorType" class="short_input">
							<option value="所有单据"
								<c:if test="${errorType == '所有单据'}">selected="selected"</c:if>>
								所有单据
							</option>
							<option value="未冲抵单据"
								<c:if test="${errorType == '未冲抵单据'}">selected="selected"</c:if>>
								未冲抵单据
							</option>
							<option value="冲抵单据"
								<c:if test="${errorType == '冲抵单据'}">selected="selected"</c:if>>
								冲抵单据
							</option>
						</select>
					</p>
					<input type="submit" name="submit" value="筛选">

				</div>
				<table class="chitdetail" cellspacing="2" align="center">
					<thead>
						<span class="errors">${errors['chargeAgainst'][0]}</span>
						<tr>
							<th width="3%" nowrap>
								选项
							</th>
							<th width="3%" nowrap>
								行号
							</th>
							<th nowrap>
								制单日期
							</th>
							<th nowrap>
								单据编号
							</th>
							<th nowrap>
								单据类型
							</th>
							<th nowrap>
								单据摘要
							</th>
							<th nowrap>
								经手人
							</th>
							<th nowrap>
								制单人
							</th>
							<th nowrap>
								记账人
							</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="dealing" items="${dealingList}" varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>

								<td nowrap>
									<input type="radio" name="select" value="${dealing.id}">
								</td>
								<td nowrap>
									<font size="2"
										<c:if test="${dealing.isError }">color="red"</c:if>>${num.count}</font>
								</td>
								<td nowrap>
									<font size="2"
										<c:if test="${dealing.isError }" >color="red"</c:if>>${dealing.createDate}</font>
								</td>
								<td nowrap>
									<font size="2"
										<c:if test="${dealing.isError}" >color="red"</c:if>>${dealing.code}</font>
								</td>
								<td nowrap>
									<font size="2"
										<c:if test="${dealing.isError}" >color="red"</c:if>>${dealing.type}</font>
								</td>
								<td nowrap align="left">
									<font size="2"
										<c:if test="${dealing.isError}" >color="red"</c:if>>${dealing.brief}</font>
								</td>
								<td nowrap align="left">
									<font size="2"
										<c:if test="${dealing.isError}" >color="red"</c:if>>${dealing.handlerName}</font>
								</td>
								<td nowrap>
									<font size="2"
										<c:if test="${dealing.isError}" >color="red"</c:if>>${dealing.creatorName}</font>
								</td>
								<td nowrap align="left">
									<font size="2"
										<c:if test="${dealing.isError}" >color="red"</c:if>>${dealing.accountantName}</font>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<table class="operate">
					<tr>
						<td>
							<input type="submit" name="submit" value="查看">
							<input type="submit" name="submit" value="冲抵">
							<input type="submit" name="submit" value="查询">
						</td>
					</tr>
				</table>
				
				<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
			</form>
		</div>
		<script type="text/javascript">
	
</script>


	</body>
</html>

