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
			<form action="/money/payment.html" method="post">
			<s:token/>
				<div class="chithead">
					<p class="title">
						付款单
					</p>
					<div class="right">
						<p>
							编号
							<c:choose>
								<c:when test="${dealing.code != null}">
									<input name="dealing.code" value="${dealing.code}"
										class="code_input" readonly>
								</c:when>
								<c:otherwise>
									<input name="dealing.code" value="自动生成" class="code_input"
										readonly>
								</c:otherwise>
							</c:choose>
						</p>

					</div>
				</div>
				<table class="chitbody" cellspacing="4">
					<tr>
						<td>
							<p>
								付款日期
							</p>
							<input name="dealing.checkDate" value="${dealing.checkDate}"
								id="date" class="short_input"
								<c:if test="${dealing.isChecked == true}">readonly</c:if> />
							<span class="errors">${errors['dealing.checkDate'][0]}</span>

						</td>
					</tr>
					<tr>
						<td>
							<p>
								收款单位
							</p>
							<input type="hidden" name="dealing.dealingUnitId"
								value="${dealing.dealingUnitId}" />
							<input type="text" name="dealing.dealingUnitShortName"
								value="${dealing.dealingUnitShortName}" readonly />
							<span class="errors">${errors['dealing.dealingUnitId'][0]}</span>
							<input type="submit" name="submit" value="选择收款单位"
								<c:if test="${dealing.isChecked == true}">disabled</c:if> />

						</td>
					</tr>
					<tr>
						<td>
							<p>
								经手人
							</p>
							<input type="text" name="dealing.handlerName"
								value="${dealing.handlerName}" class="short_input"
								<c:if test="${dealing.isChecked == true}">readonly</c:if> />
							<span class="errors">${errors['dealing.handlerName'][0]}</span>
						</td>
					</tr>
					<tr>
						<td>

							<p>
								摘要
							</p>
							<input type="text" name="dealing.brief" value="${dealing.brief}"
								class="long_input"
								<c:if test="${dealing.isChecked == true}">readonly</c:if> />

						</td>
					</tr>
					<tr>
						<td>

							<p>
								备注
							</p>
							<input type="text" name="dealing.remark"
								value="${dealing.remark}" class="long_input"
								<c:if test="${dealing.isChecked == true}">readonly</c:if> />

						</td>
					</tr>
				</table>
				<span class="errors">${errors['dealingDetailList'][0]}</span>
				<c:if test="${dealing.isChecked != true}">
					<input type="submit" name="submit" value="选择账户"
						<c:if test="${dealing.isChecked == true}">disabled</c:if> />
					<input type="submit" name="submit" value="删除账户"
						<c:if test="${dealing.isChecked == true}">disabled</c:if> />
					<input type="submit" name="submit" value="计算金额"
						<c:if test="${dealing.isChecked == true}">disabled</c:if> />
				</c:if>
				<table class="chitdetail">
					<thead>
						<tr>
							<th width="3%" nowrap>
								选项
							</th>
							<th width="3%" nowrap>
								行号
							</th>
							<th nowrap>
								付款账户名称
							</th>
							<th nowrap>
								金额
								<span class="errors">${errors['dealingDetail.total'][0]}</span>
							</th>

							<th nowrap>
								备注
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="dealingDetail" items="${dealingDetailList}"
							varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>
								<td nowrap>
									<input type="checkbox" name="selectList" value="${num.index}"
										<c:if test="${dealing.isChecked == true}">disabled</c:if> />
								</td>

								<td nowrap class="sequence">
									${num.count}
									<c:if test="${dealingDetailList!=null}">
										<input type="hidden" name="dealingDetailList[${num.index}].id"
											value="${dealingDetail.id}">
									</c:if>
								</td>
								<td nowrap>
									<input type="text"
										name="dealingDetailList[${num.index}].subAccountType"
										value="${dealingDetail.subAccountType}"
										<c:if test="${dealing.isChecked == true}">readonly</c:if>>

									<input type="hidden"
										name="dealingDetailList[${num.index}].subAccountId"
										value="${dealingDetail.subAccountId}">
								</td>
								<td nowrap>
									<input type="text" name="dealingDetailList[${num.index}].total"
										value="<fmt:formatNumber value='${dealingDetail.total}' pattern='#,##0.00'/>"
										<c:if test="${dealing.isChecked == true}">readonly</c:if>>


								</td>
								<td nowrap>
									<input type="text"
										name="dealingDetailList[${num.index}].remark"
										value="${dealingDetail.remark}">
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="3"></td>
							<td>
								合计金额
								<fmt:formatNumber value='${dealing.total}' pattern='#,##0.00' />
							</td>
						</tr>
					</tbody>
				</table>
				<table>
					<tr>
						<td>
							制单人:${dealing.accountantName }
						</td>

					</tr>
					<tr>
						<td align="right">
							<input type="submit" name="submit" value="打印" />
							<c:if test="${dealing.isChecked != true}">
								<input type="submit" name="submit" value="保存" />
								<input type="submit" name="submit" value="记账" />
								<input type="submit" name="submit" value="放弃" />
							</c:if>
						</td>
					</tr>
				</table>
			</form>

		</div>
		<c:if test="${dealing.isChecked != true}">
			<script type="text/javascript">
	function date_input() {
		var element = document.getElementById("date");
		element.onfocus = function() {
			WdatePicker();
		}
	}

	window.onload = function() {
		date_input();

	}
</script>

		</c:if>


	</body>
</html>