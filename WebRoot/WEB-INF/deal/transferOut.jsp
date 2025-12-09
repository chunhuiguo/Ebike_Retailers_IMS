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
		<script type="text/javascript" src="/js/deal/transferOut.js"></script>
		<script type="text/javascript">
		$(function(){
			$('#print').click(function(){
				
				LODOP.SET_PRINT_PAPER(0, 0, 220, 0, "");
		        LODOP.SET_PRINT_PAGESIZE(3, 550, 45, "");
		        LODOP.ADD_PRINT_TEXT(3, 32, 146, 25, "库存调拨出库单");
		        LODOP.SET_PRINT_STYLEA(0, "FontName", "楷体");
		        LODOP.SET_PRINT_STYLEA(0, "FontSize", 12);
		        LODOP.SET_PRINT_STYLEA(0, "Bold", 1);
		        LODOP.ADD_PRINT_RECT(33, 1, 210, 1, 0, 1);
		        LODOP.ADD_PRINT_TEXT(21, 1, 35, 20, "品名");
		        LODOP.SET_PRINT_STYLEA(0, "FontSize", 6);
		        LODOP.ADD_PRINT_TEXT(21, 130, 25, 20, "数量");
		        LODOP.SET_PRINT_STYLEA(0, "FontSize", 6);

				var gaodu = 36;
		        $(".trc").each(function(i){
		            //if (i == $(".chitdetail tbody tr").)
		        	LODOP.ADD_PRINT_TEXT(gaodu, 1, 190, 20, $(this).children("td:eq(3)").children("input").val()+$(this).children("td:eq(4)").children("input").val());
		            LODOP.ADD_PRINT_TEXT(gaodu+12, 130, 20, 20, $(this).children("td:eq(6)").children("input").val());
		            gaodu += 30;
		        	 });

		        LODOP.ADD_PRINT_RECT(gaodu, -1, 210, 1, 0, 1);
		        gaodu += 5;
		        LODOP.ADD_PRINT_TEXT(gaodu, 10, 180, 5, "发货仓库："+$("#voucherwarehouseName").val());
		        gaodu += 15;
		        LODOP.ADD_PRINT_TEXT(gaodu, 10, 180, 5, "收货单位："+$("#voucherdealingUnitShortName").val());
		        gaodu += 15;
		        LODOP.ADD_PRINT_TEXT(gaodu, 10, 180, 5, "单据编码："+$("#vouchercode").val());
		        gaodu += 15;
		        LODOP.ADD_PRINT_TEXT(gaodu, 10, 180, 5, "调拨日期："+$("#date").val());
		        gaodu += 15;
		        LODOP.ADD_PRINT_TEXT(gaodu, 10, 180, 5, "签收：");
		        LODOP.PRINT();
				return false;
			});
			});
    </script>
	</head>
	<body>
	<object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width="0" height="0"> 
</object>
		<div class="container">			
			<c:if test="${voucher.isChecked == true}" var="checked"/>
			<input type="hidden" id="type" name="type" value="${type}">
			
			
			<form action=<c:if test="${type eq '整车'}">"/deal/transferOut.html"</c:if>
						<c:if test="${type eq '配件'}">"/deal/ptransferOut.html"</c:if> method="post">
				<s:token/>
				
				<div class="chithead"> 
					<p class="title">${type}调拨出库单</p>					
					<div class="right">
						<input type="hidden" name="voucher.id" value="${voucher.id}">						
						<p>单据编码
							<c:choose>
								<c:when test="${voucher.code != null}">
									<input name="voucher.code" value="${voucher.code}" id="vouchercode"  class="code_input" disabled>
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
						<p>调拨日期</p>																
						<input name="voucher.checkDate" value="${voucher.checkDate}" class="short_input" readonly <c:if test="${checked}">disabled</c:if> id="date" />
						<span class="errors">${errors['voucher.checkDate'][0]}</span>
					</td><c:if test="${type eq '整车'}"><td>
					条码区：<span class="errors">${errors['voucher.codeback'][0]}</span>
					</td></c:if> </tr>
					<tr><td>						
						<p>收货单位</p>												
						<input type="hidden" name="voucher.dealingUnitId" value="${voucher.dealingUnitId}">											
						<input type="text" name="voucher.dealingUnitShortName" id="voucherdealingUnitShortName" value="${voucher.dealingUnitShortName}" class="short_input" readonly <c:if test="${checked}">disabled</c:if>/>
						<input type="submit" name="method:unitList" value="选择收货单位" <c:if test="${checked}">disabled</c:if> />	
						<span class="errors">${errors['voucher.dealingUnitId'][0]}</span>						
					</td><c:if test="${type eq '整车'}"><td rowspan="4"><textarea name="voucher.productBarcodeTxt" rows="6" class="short_input" <c:if test="${checked}">disabled</c:if>>${voucher.productBarcodeTxt}</textarea>
					</td></c:if> </tr>											
					<tr><td>	
						<p>经手人</p>
						<input type="hidden" name="voucher.handlerId" value="${voucher.handlerId}"/>
						<input type="text" name="voucher.handlerName" value="${voucher.handlerName}" class="short_input" readonly <c:if test="${checked}">disabled</c:if>/>
						<input type="submit" name="method:employeeList" value="选择经手人" <c:if test="${checked}">disabled</c:if> />		
						<span class="errors">${errors['voucher.handler'][0]}</span>
					</td></tr>
					<tr><td>
						<p>发货仓库</p>
						<input type="hidden" name="voucher.warehouseId" value="${voucher.warehouseId}"/>
						<input type="text" name="voucher.warehouseName" id="voucherwarehouseName" value="${voucher.warehouseName}" class="short_input" readonly <c:if test="${checked}">disabled</c:if>/>
						<input type="submit" name="method:warehouseList" value="选择发货仓库" <c:if test="${checked}">disabled</c:if> />
						<span class="errors">${errors['voucher.warehouse'][0]}</span>
					</td></tr>
					<tr><td>	
						<p>摘要</p><input type="text" name="voucher.brief" value="${voucher.brief}" class="long_input" <c:if test="${checked}">disabled</c:if>/>
					</td></tr>
					<tr><td>	
						<p>备注</p><input type="text" name="voucher.remark" value="${voucher.remark}" class="long_input" <c:if test="${checked}">disabled</c:if>/>

					</td><c:if test="${type eq '整车'}"><td>
						<input type="submit" name="method:entercode" value="确认条码" <c:if test="${checked}">disabled</c:if>>
					</td></c:if> </tr>
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

							<tr class="trc" <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >
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
							<input type="button" value="打印" id="print" disabled/>						
						</td>
					</tr>					
					
				</table>
				
				
				
				
			</form>
				
		</div>

	</body>
</html>