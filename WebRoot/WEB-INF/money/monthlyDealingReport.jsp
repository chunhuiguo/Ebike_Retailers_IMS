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
			<form action="/report/dealingQuery.html" method="post">
			<s:token/>
				<div class="chithead">
					<p class="title">
						账款月报查询
					</p>
				</div>
				<div class="chithead">
					<p>
						年份：
						<input name="page.propsNameList" value="reportYear" type="hidden" />
						<input name="page.propsValueList"
							value="${page.propsValueList[0]}" type="text" class="short_input" />
						月份：
						<input name="page.propsNameList" value="reportMonth" type="hidden" />
						<input name="page.propsValueList"
							value="${page.propsValueList[1]}" type="text" class="short_input" />
						
					</p>
					
				</div>
				<div class="left">
						<p>账款类型
							<select name="type" class="short_input">								
								<option value="应收" <c:if test="${type eq '应收'}">selected</c:if> >应收</option>
								<option value="应付" <c:if test="${type eq '应付'}">selected</c:if> >应付</option>													
							</select>
						</p>
						
						<p>账户类型
							<select name="accountType" class="short_input">								
								<option value="整车账户" <c:if test="${accountType eq '整车账户'}">selected</c:if> >整车账户</option>
								<option value="配件账户" <c:if test="${accountType eq '配件账户'}">selected</c:if> >配件账户</option>													
							</select>
						</p>
						<input type="submit" name="submit" value="查询" />
					</div>
				<table>
					<thead class="chitdetail"  align="center">
						<tr>

							<th width="10%" nowrap>
								行号
							</th>
							<th nowrap>
								往来单位简称
							</th>
							<th nowrap>
								类型
							</th>
							<th nowrap>
								账户名称
							</th>
							<th nowrap>
								上月余额
							</th>
							<th nowrap>
								本月应发生数
							</th>
							<th nowrap>
								本月实发生数
							</th>
							<th nowrap>
								本月余额
							</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="pSIDealingMonthlyReport"
							items="${psiDealingMonthlyReportList}" varStatus="num">
							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>
								<td nowrap class="sequence">
									${num.count}
								</td>
								<td nowrap>
									${pSIDealingMonthlyReport.dealingUnitShortName}
								</td>
								<td nowrap>
									${pSIDealingMonthlyReport.type}
								</td>
								<td nowrap>
									${pSIDealingMonthlyReport.subAccountType}
								</td>
								<td nowrap>
									${pSIDealingMonthlyReport.initialBalance}
								</td>
								<td nowrap>
									${pSIDealingMonthlyReport.dueAmount}
								</td>
								<td nowrap>
									${pSIDealingMonthlyReport.actualAmount}
								</td>
								<td nowrap>
									${pSIDealingMonthlyReport.finalBalance}
								</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
			</form>
		</div>
	</body>
</html>