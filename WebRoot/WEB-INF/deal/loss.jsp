<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
	<head>
		<meta name="decorator" content="${ajax}" />
		<script type="text/javascript" src="/js/deal/loss.js"></script>
		<script type="text/javascript">
		/*
        $(document).ready(function() {
        	$(".short_input_code").val("");
        	$(".short_input_code").focus();
            
            $(".short_input_code").keydown(function(event) {
                if (event.keyCode == 13 ) {
				if ($(".short_input_code").val().length == 16){
					$(".short_input_code").val($(".short_input_code").val().substring(3,12));
					$("#tiaomasaomiao").click();
					}
				return false;
                    }
            });
        });
        */
    </script>
	</head>
	<body>
		<div class="container">			
			<c:if test="${voucher.isChecked == true}" var="checked"/>
			<input type="hidden" id="type" name="type" value="${type}">
			
			
			<form action=<c:if test="${type eq '整车'}">"/deal/loss.html"</c:if>
						<c:if test="${type eq '配件'}">"/deal/ploss.html"</c:if> method="post">
				<s:token/>
				
				<div class="chithead"> 
					<p class="title">${type}报损单</p>					
					<div class="right">
						<input type="hidden" name="voucher.id" value="${voucher.id}">						
						<p>单据编码
							<c:choose>
								<c:when test="${voucher.code != null}">
									<input name="voucher.code" value="${voucher.code}"  class="code_input" disabled>
								</c:when>
								<c:otherwise>
									<input name="voucher.code" value="自动生成"  class="code_input" disabled>
								</c:otherwise>
							</c:choose>
						</p>
					</div>
				</div>
				
				
				<table class="chitbody" cellspacing="4">
					<tr><td width="500">						
						<p>报损日期</p>																
						<input name="voucher.checkDate" value="${voucher.checkDate}" class="short_input" readonly  <c:if test="${checked}">disabled</c:if>	id="date" />
						<span class="errors">${errors['voucher.checkDate'][0]}</span>
					</td>
					<c:if test="${type eq '整车'}"><td>
					条码区：<span class="error">${errors['voucher.codeback'][0]}</span>
					</td></c:if>
					</tr>
					<tr><td>						
						<p>报损单位</p>												
						<input type="hidden" name="voucher.dealingUnitId" value="${voucher.dealingUnitId}">											
						<input type="text" name="voucher.dealingUnitShortName" value="${voucher.dealingUnitShortName}" class="short_input" readonly <c:if test="${checked}">disabled</c:if>/>						
					</td>
					<c:if test="${type eq '整车'}"><td rowspan="4"><textarea name="voucher.productBarcodeTxt" rows="6" class="short_input" <c:if test="${checked}">disabled</c:if>>${voucher.productBarcodeTxt }</textarea>
					</td></c:if>
					</tr>											
					<tr><td>	
						<p>经手人</p>
						<input type="hidden" name="voucher.handlerId" value="${voucher.handlerId}"/>
						<input type="text" name="voucher.handlerName" value="${voucher.handlerName}" class="short_input" readonly <c:if test="${checked}">disabled</c:if>/>
						<input type="submit" name="method:employeeList" value="选择经手人" <c:if test="${checked}">disabled</c:if> />		
						<span class="errors">${errors['voucher.handler'][0]}</span>
					</td></tr>
					<tr><td>
						<p>出库仓库</p>
						<input type="hidden" name="voucher.warehouseId" value="${voucher.warehouseId}"/>
						<input type="text" name="voucher.warehouseName" value="${voucher.warehouseName}" class="short_input" readonly <c:if test="${checked}">disabled</c:if>/>
						<input type="submit" name="method:warehouseList" value="选择出库仓库" <c:if test="${checked}">disabled</c:if> />
						<span class="errors">${errors['voucher.warehouse'][0]}</span>
					</td></tr>
					<tr><td>	
						<p>摘要</p><input type="text" name="voucher.brief" value="${voucher.brief}" class="long_input" <c:if test="${checked}">disabled</c:if>/>
					</td></tr>
					<tr><td>	
						<p>备注</p><input type="text" name="voucher.remark" value="${voucher.remark}" class="long_input" <c:if test="${checked}">disabled</c:if>/>

					</td>
					<c:if test="${type eq '整车'}"><td><input type="submit" name="method:entercode" value="确认条码" <c:if test="${checked}">disabled</c:if>/></td>
					</c:if> </tr>
				</table>
				
				<table class="noprint">
					<tr>
					<td>
					<input type="submit" name="method:partList" value="添加商品" <c:if test="${checked}">disabled</c:if> />
					<!--  
					<input type="submit" <c:if test="${type eq '整车'}"> name="method:productList" </c:if>
										<c:if test="${type eq '配件'}"> name="method:partList" </c:if> value="添加商品" <c:if test="${checked}">disabled</c:if> />
					-->											
					<input type="submit" name="method:delProduct" value="删除商品" <c:if test="${checked}">disabled</c:if> />
					<input type="submit" name="method:calculate" value="计算金额" <c:if test="${checked}">disabled</c:if> />
					<span class="errors"><s:actionerror/></span>
					<span class="errors">${errors['productInventory'][0]}</span>					
					</td>
					</tr>
				</table>				
				<table class="chitdetail" >
					<thead>
						<tr>							
							<th width="3%" nowrap class="noprint">选项</th>							
							<th width="3%" nowrap>行号</th>
							<th  nowrap>商品编码</th>
							<th  nowrap>商品全名</th>							
							<th  nowrap>颜色</th>
							<th  nowrap>单位</th>
							<th  nowrap>数量<span class="errors">${errors['voucherDetail.qty'][0]}</span></th>
							<c:if test="${viewPrice}">
							<th  nowrap>单价<span class="errors">${errors['voucherDetail.price'][0]}</span></th>
							<th  nowrap>金额</th>
							</c:if>						
							<th  nowrap>备注</th>
						</tr>
					</thead>
					
					
					<tbody>					
						<c:forEach var="voucherDetail" items="${voucherDetailList}" varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >
								<td nowrap class="sequence noprint">								
								<!-- 整车编码以“6”开头，如果明细是整车，checkbox禁掉，不让删除明细，修改整车的明细只能通过条码来 -->
								<input type="checkbox" name="selectList" value="${num.index}"  <c:if test="${checked || (fn:startsWith(voucherDetailList[num.index].productCode, '6'))}">disabled</c:if> >									
								<c:if test="${voucherDetail.id != null}">							
									<input type="hidden" name="voucherDetailList[${num.index}].id" value="${voucherDetail.id}">	
								</c:if>													
								</td>
								<td nowrap class="sequence">
									${num.count}									
								</td>								
								<td nowrap>										
									<input type="text" name="voucherDetailList[${num.index}].productCode" value="${voucherDetail.productCode}" readonly <c:if test="${checked}">disabled</c:if>>									
								</td>								
								<td nowrap>
								<input type="text" name="voucherDetailList[${num.index}].productName" value="${voucherDetail.productName}" readonly <c:if test="${checked}">disabled</c:if>>
								</td>								
								<td nowrap>
									 <input type="text" name="voucherDetailList[${num.index}].productColor" value="${voucherDetail.productColor}" readonly <c:if test="${checked}">disabled</c:if>>
								</td>
								<td nowrap>
									<input type="text" name="voucherDetailList[${num.index}].productUnit" value="${voucherDetail.productUnit}" readonly <c:if test="${checked}">disabled</c:if>>
								</td>
								<td nowrap align="left">								
									<input type="text" name="voucherDetailList[${num.index}].qty" value="${voucherDetail.qty}" <c:if test="${checked}">disabled</c:if>>
								</td>
								<c:if test="${viewPrice}">						
								<td nowrap>									
									<input type="text" name="voucherDetailList[${num.index}].price" value="<fmt:formatNumber value='${voucherDetail.price}' pattern='#,##0.00'/>" <c:if test="${checked}">disabled</c:if>>
								</td>								
								<td nowrap>
									<input type="text" name="voucherDetailList[${num.index}].total" value="<fmt:formatNumber value='${voucherDetail.total}' pattern='#,##0.00'/>" readonly <c:if test="${checked}">disabled</c:if>>
								</td>	
								</c:if>					
								<td nowrap>
									<input type="text" name="voucherDetailList[${num.index}].remark" value="${voucherDetail.remark}" <c:if test="${checked}">disabled</c:if>>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="4"></td>
							<td align="right">合计数量</td>
							<td>${voucher.qty}</td>
							<td>合计金额</td>
							<td><fmt:formatNumber value="${voucher.total}" pattern='#,##0.00'/></td>
						</tr>
					</tbody>
				</table>

				
				<table>
					<tr>
						<td>
							<input type="hidden" name="voucher.creatorId" value="${voucher.creatorId}">
							<input type="hidden" name="voucher.creatorName" value="${voucher.creatorName}">
							制单人：${voucher.creatorName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;记账人：${voucher.accountantName}
						</td>						
						<td align="right" class="noprint">							
							<input type="submit" name="method:save" value="保存" <c:if test="${checked}">disabled</c:if> />
							<input type="submit" name="method:check" value="记账" <c:if test="${checked || (! viewPrice)}">disabled</c:if> />							
							<input type="submit" name="method:cancel" value="放弃" <c:if test="${checked}">disabled</c:if> />
							<input type="submit" name="method:print" value="打印" id="print" disabled/>						
						</td>
					</tr>					
					
				</table>
				
				
				
				
			</form>
				
		</div>

	</body>
</html>