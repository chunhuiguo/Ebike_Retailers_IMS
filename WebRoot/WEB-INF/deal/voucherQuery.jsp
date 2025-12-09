<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
	<head>
		<meta name="decorator" content="pop-up" />
	</head>
	<body>
		<div class="container">
			<form action=<c:if test="${type eq '整车'}">"/deal/voucherQuery.html"</c:if><c:if test="${type eq '配件'}">"/deal/pvoucherQuery.html"</c:if> method="post">
			
				<div class="chithead"> 
					<p class="title">查询条件</p>
				</div>				
				<table class="chitbody" cellspacing="4">					
					<tr><td>						
						<p>往来单位</p>		
						<input type="hidden" name="voucher.dealingUnitId" value="${voucher.dealingUnitId}">											
						<input type="text" name="voucher.dealingUnitShortName" value="${voucher.dealingUnitShortName}" class="short_input" readonly/>
						<input type="submit" name="submit" value="选择往来单位" />
					</td></tr>					
					<tr><td>	
						<p>经手人</p>
						<input type="hidden" name="voucher.handlerId" value="${voucher.handlerId}"/>
						<input type="text" name="voucher.handlerName" value="${voucher.handlerName}" class="short_input" readonly/>
						<input type="submit" name="submit" value="选择经手人" />							
					</td></tr>
					<tr><td>
						<p>仓库</p>
						<input type="hidden" name="voucher.warehouseId" value="${voucher.warehouseId}"/>
						<input type="text" name="voucher.warehouseName" value="${voucher.warehouseName}" class="short_input" readonly/>
						<input type="submit" name="submit" value="选择仓库">					
					</td></tr>
					<tr><td>
						<p>单据编码</p>						
						<input type="text" name="voucher.code" value="${voucher.code}" class="code_input" />												
					</td></tr>
					<tr><td>
						<p>发货通知单编码</p>						
						<input type="text" name="voucher.shipOrderCode" value="${voucher.shipOrderCode}" class="short_input" />												
					</td></tr>
					<tr><td>	
						<p>单据类型</p>
						<select name="voucher.brief" class="short_input">								
							<option value="所有单据" >所有单据</option>
							<option value="整车进货单" >整车进货单</option>
							<option value="整车进货退货单" >整车进货退货单</option>
							<option value="整车销售单" >整车销售单</option>
							<option value="整车销售退货单" >整车销售退货单</option>	
							<option value="整车调拨入库单" >整车调拨入库单</option>
							<option value="整车调拨出库单" >整车调拨出库单</option>	
							<option value="整车报损单" >整车报损单</option>	
							<option value="整车报溢单" >整车报溢单</option>						
						</select>						
					</td></tr>
					<tr><td>	
						<p>冲抵过滤</p>
						<select name="errorType" class="short_input">								
							<option value="所有单据" >所有单据</option>
							<option value="未冲抵单据" >未冲抵单据</option>
							<option value="冲抵单据" >冲抵单据</option>													
						</select>
					</td></tr>
					<tr><td>						
						<p>开始日期</p>												
						<input name="voucher.checkDate" value="${voucher.checkDate}" id="checkDate" class="short_input" readonly />
						<span class="errors">${errors['voucher.checkDate'][0]}</span>
					</td></tr>
					<tr><td>						
						<p>结束日期</p>												
						<input name="voucher.createDate" value="${voucher.createDate}" id="createDate" class="short_input" readonly />
						<span class="errors">${errors['voucher.createDate'][0]}</span>
					</td></tr>
				</table>				
				<table>					
					<tr >						
						<td align="right">
							<input type="submit" name="submit" value="确定" />
							<input type="submit" name="submit" value="重置" />
							<input type="submit" name="submit" value="退出" />							
						</td>
					</tr>						
				</table>				
			</form>
				
		</div>	
		
	<script type="text/javascript">
	function checkDateInput(){
		var element = document.getElementById("checkDate");
		element.onfocus = function(){
			WdatePicker();
		}
	}	

	function createDateInput(){
		var element = document.getElementById("createDate");
		element.onfocus = function(){
			WdatePicker();
		}
	}

	window.onload = function(){
		checkDateInput();
		createDateInput();		
	}
	</script>
			
	</body>
</html>

