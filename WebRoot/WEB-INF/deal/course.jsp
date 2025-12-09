<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
	<head>
		<meta name="decorator" content="${ajax}" />
	</head>

	<body>
		<div class="container">

			
			<form action=<c:if test="${type eq '整车'}">"/deal/course.html"</c:if><c:if test="${type eq '配件'}">"/deal/pcourse.html"</c:if> method="post">
			
				<div class="chithead"> 
					<p class="title">${type}业务单据历史</p>
					<br/>
					[日期：${startDate}至${endDate}]								
				</div>									
					<div class="left">
						<p>单据类型
							<c:set var="array" value="进货单,进货退货单,销售单,销售退货单,调拨入库单,调拨出库单,报损单,报溢单" />							
							
							<select name="voucherType" class="short_input">
								<option value="所有单据" <c:if test="${voucherType eq '所有单据'}">selected</c:if> >所有单据</option>
								<c:forTokens items="${array}" delims="," var="val"> 
									<c:set var="temp" value="${type}${val}"></c:set>					
									<option value="${temp}" <c:if test="${voucherType eq temp}">selected</c:if> >${temp}</option>						
								</c:forTokens>
							</select>							
						</p>
						
						<p>冲抵过滤
							<select name="errorType" class="short_input">								
								<option value="所有单据" <c:if test="${errorType eq '所有单据'}">selected</c:if> >所有单据</option>
								<option value="未冲抵单据" <c:if test="${errorType eq '未冲抵单据'}">selected</c:if> >未冲抵单据</option>
								<option value="冲抵单据" <c:if test="${errorType eq '冲抵单据'}">selected</c:if> >冲抵单据</option>													
							</select>
						</p>
						<input type="submit" name="submit" value="筛选">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" name="submit" value="设置查询条件">	
					</div>
				
	
				<table class="chitdetail" >
					<span class="errors">${errors['voucher.isError'][0]}</span>
					<span class="errors">${errors['voucherDetail.product'][0]}</span>
					<thead>
						<tr>
							<th width="3%" nowrap>选项</th>
							<th width="3%" nowrap>行号</th>
							<th  nowrap>单据日期</th>
							<th  nowrap>单据编号</th>
							<th  nowrap>单据类型</th>
							<th  nowrap>摘要</th>
							<th  nowrap>门店</th>
							<th  nowrap>经手人</th>
							<th  nowrap>制单人</th>
							<th  nowrap>记账人</th>
						</tr>
					</thead>
					
					
					<tbody>
						<c:forEach var="voucher" items="${voucherList}" varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> 
								<c:if test="${voucher.isError}">class="errors"</c:if> >
								<td nowrap >
									<input type="radio" name="select" value="${num.index}">
								</td>
								<td nowrap class="sequence" >
									${num.count}
								</td>
								<td nowrap>
									${voucher.checkDate}
								</td>
								<td nowrap>
									${voucher.code}
								</td>
								<td nowrap>
									${voucher.type}
								</td>
								<td nowrap>
									${voucher.brief}
								</td>
								<td nowrap>
									${voucher.shopShortName}
								</td>
								<td nowrap>
									${voucher.handlerName}
								</td>
								<td nowrap>
									${voucher.creatorName}
								</td>									
								<td nowrap>
									${voucher.accountantName}
								</td>
							</tr>
						</c:forEach>
						
					</tbody>
				</table>
				
				<table class="operate">

					<tr>
						<td align="right">
							<input type="submit" name="submit" value="查看">	
							<!-- 
							<input type="submit" name="submit" value="冲抵" <c:if test="${! viewPrice}">disabled</c:if> />	
							 -->		
							<input type="submit" name="submit" value="冲抵"/>								
						</td>
					</tr>

				</table>
			
				<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
				
			</form>
				
		</div>	


	</body>
</html>


