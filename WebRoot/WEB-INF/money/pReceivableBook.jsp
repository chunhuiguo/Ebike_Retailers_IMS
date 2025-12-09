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
			<form action="/money/pReceivableBook.html" method="post">
			<s:token/>
				<div class="chithead">
					<p class="title">
						配件应收台账
					</p>
				</div>
				<table class="chitdetail" cellspacing="2" align="center">
					<thead>
						<tr>
							<th width="3%" nowrap>
								选项
							</th>
							<th width="3%" nowrap>
								行号
							</th>
							<th nowrap>
								类型
							</th>
							<th nowrap>
								单据类型
							</th>
							<th nowrap>
								单据编号
							</th>
							<th>
								单据日期
							</th>
							<th nowrap>
								应收金额
							</th>
							<th nowrap>
								实收金额
							</th>
							<th nowrap>
								余额
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="pDealingBook" items="${pdealingBookList}"
							varStatus="num">
							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>
								<td nowrap>
									<input type="radio" name="select" value="${num.index}">
								</td>
								<td nowrap>
									${num.count}
								</td>
								<td nowrap>
									${pDealingBook.type}
								</td>
								<td nowrap align="left">
									${pDealingBook.voucherType}
								</td>
								<td nowrap align="left">
									${pDealingBook.voucherCode}
								</td>
								<td nowrap align="left">
									${pDealingBook.voucherDate}
								</td>
								<td nowrap>
									<fmt:formatNumber value='${pDealingBook.dueTotal}'
										pattern='#,##0.00' />
								</td>
								<td nowrap align="left">
									<fmt:formatNumber value='${pDealingBook.actualTotal}'
										pattern='#,##0.00' />
								</td>
								<td nowrap align="left">
									<fmt:formatNumber value='${pDealingBook.balance}'
										pattern='#,##0.00' />
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<table class="operate">
					<tr>
						<td>
							<input type="submit" name="submit" value="查看单据">
							<input type="submit" name="submit" value="退出">
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

