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
			<form action="/report/salesQuery.html" method="post">
			<s:token/>
				<table class="chitbody" cellspacing="4">
					<div class="chithead">
						<p class="title">
							销售月报查询
						</p>
					</div>
					<tr>
						<td>
							<input name="page.propsNameList" value="reportYear" type="hidden" />
							<input name="page.propsValueList"
								value="${page.propsValueList[0]}" type="text"
								class="short_input" />
							<!--  <select name="reportYear">
								<c:forEach var="year" items="${yearList}">
									<option value="${year}" <c:if test="${reportYear == '${year}'}">selected</c:if>>
										${year}
									</option>
								</c:forEach>
							</select>-->
							年
							<input name="page.propsNameList" value="reportMonth" type="hidden" />
							<input name="page.propsValueList"
								value="${page.propsValueList[1]}" type="text"
								class="short_input" />
							<!--  <select name="reportMonth">
								<option value="12">
									12
								</option>
								<option value="11">
									11
								</option>
								<option value="10">
									10
								</option>
								<option value="9">
									9
								</option>
								<option value="8">
									8
								</option>
								<option value="7">
									7
								</option>
								<option value="6">
									6
								</option>
								<option value="5">
									5
								</option>
								<option value="4">
									4
								</option>
								<option value="3">
									3
								</option>
								<option value="2">
									2
								</option>
								<option value="1">
									1
								</option>
							</select>-->
							月
						</td>
						<td>
							<input type="submit" name="submit" value="查询" />
						</td>
					</tr>
				</table>
				<table>
					<thead>
						<tr>

							<th width="10%" nowrap>
								行号
							</th>
							<th nowrap>
								整车编码
							</th>
							<th nowrap>
								颜色
							</th>
							<th nowrap>
								仓库名称
							</th>
							<th nowrap>
								门店简称
							</th>
							<th nowrap>
								上月结存数
							</th>
							<th nowrap>
								本月进货量
							</th>
							<th nowrap>
								本月销售量
							</th>
							<th nowrap>
								本月结存数
							</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="pSIProductMonthlyReport"
							items="${pSIProductMonthlyReportList}" varStatus="num">
							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>
								<td nowrap class="sequence">
									${num.count}
								</td>
								<td nowrap>
									${pSIProductMonthlyReport.productCode}
								</td>
								<td nowrap>
									${pSIProductMonthlyReport.productColor}
								</td>
								<td nowrap>
									${pSIProductMonthlyReport.warehouseName}
								</td>
								<td nowrap>
									${pSIProductMonthlyReport.shopShortName}
								</td>
								<td nowrap>
									${pSIProductMonthlyReport.initialQty}
								</td>
								<td nowrap>
									${pSIProductMonthlyReport.inQty}
								</td>
								<td nowrap>
									${pSIProductMonthlyReport.outQty}
								</td>
								<td nowrap>
									${pSIProductMonthlyReport.finalQty}
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