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
				<div class="chithead">
					<p class="title">
						销售月报查询
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
						<input type="submit" name="submit" value="查询" />
					</p>
				</div>
				<table>
					<thead class="chitdetail"  align="center">
						<tr>

							<th width="10%" nowrap>
								行号
							</th>
							<th nowrap>
								整车编码
							</th>
							<th nowrap>
								整车车型
							</th>
							<th nowrap>
								颜色
							</th>
							<th nowrap>
								仓库名称
							</th>
							<th nowrap>
								所属单位
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
							items="${psiProductMonthlyReportList}" varStatus="num">
							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>
								<td nowrap class="sequence">
									${num.count}
								</td>
								<td nowrap>
									${pSIProductMonthlyReport.productCode}
								</td>
								<td nowrap>
									${pSIProductMonthlyReport.productName}
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