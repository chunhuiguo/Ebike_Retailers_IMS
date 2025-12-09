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
		<script type="text/javascript" src="/js/deal/purchase.js"></script>
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
			
			
			<form action=<c:if test="${type eq '整车'}">"/deal/check.html"</c:if>
						<c:if test="${type eq '配件'}">"/deal/pcheck.html"</c:if> method="post">
				<s:token/>
				
				<div class="chithead"> 
					<p class="title">${type}盘点</p>					
					<div class="right">
						<input type="hidden" name="voucher.id" value="${voucher.id}">						
						<p>单据编码
							<c:choose>
								<c:when test="${voucher.code != null}">
									<input name="voucher.code" value="${voucher.code}"  class="code_input" readonly>
								</c:when>
								<c:otherwise>
									<input name="voucher.code" value="自动生成"  class="code_input" readonly>
								</c:otherwise>
							</c:choose>
						</p>
					</div>
				</div>
				
				
				<table class="chitbody" cellspacing="4">
					<tr><td width="500">						
						<p>盘点日期</p>																
						<input name="voucher.checkDate" value="${voucher.checkDate}" class="short_input" readonly 
						id="date" />
						<span class="errors">${errors['voucher.checkDate'][0]}</span>
					</td>
					<td>条码区：<span class="errors">${errors['voucher.codeback'][0]}</span></td></tr>
					<tr><td>						
						<p>盘点仓库</p>
						<select name="wwwarehouseid">
						<c:forEach var="warehouse" items="${warehouseList}" varStatus="num">
							<option value="${warehouse.id}">${warehouse.name}</option>
						</c:forEach>
						</select>												
					</td>
						<td rowspan="3"><textarea name="voucher.productBarcodeTxt" rows="5" class="short_input short_input_code">${voucher.productBarcodeTxt}</textarea></td>
					</tr>						
					<tr><td>	
						<p>盘点人</p>
						
						<select name="voucher.employeeName" >
						<c:forEach var="employee" items="${employeeList}" varStatus="num">
							<option value="${employee.name}">${employee.name}</option>
						</c:forEach>
						</select>
					</td></tr>
					 
					<tr><td>	
						<p>摘要</p><input type="text" name="voucher.brief" value="${voucher.brief}" class="long_input" <c:if test="${checked}">readonly</c:if>/>
					</td></tr>
					<tr><td>	
						<p>备注</p><input type="text" name="voucher.remark" value="${voucher.remark}" class="long_input" <c:if test="${checked}">readonly</c:if>/>

					</td><td><input type="submit" name="method:entercode" value="盘点"/></td></tr>
				</table>
				
								
				<table class="chitdetail" >
					<thead>
						<tr>										
							<th width="3%" nowrap>行号</th>
							<th  nowrap>商品编码</th>
							<th  nowrap>商品全名</th>							
							<th  nowrap>颜色</th>
							<th  nowrap>单位</th>
							<th  nowrap>库存数量</th>
							<th  nowrap>实际数量</th>					
							<th  nowrap>操作</th>
						</tr>
					</thead>
					
					
					<tbody>
						<c:forEach var="inventory" items="${inventorys}" varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >
								<td nowrap class="sequence" >
									${num.count}
								</td>
								<td nowrap>
									${inventory.productCode}
								</td>
								<td nowrap>
									${inventory.productName}
								</td>
								<td nowrap>
									${inventory.productColor}
								</td>
								<td nowrap>
									${inventory.productUnit}
								</td>
								<td nowrap>
									${inventory.qty}
								</td>
								<td nowrap>
									${inventory.qtynew}
								</td>
								<td nowrap>
									
								</td>
								
								
								
							</tr>
						</c:forEach>						
					</tbody>
				</table>
				
				
				
				<table>
					<tr>
						<td>
							<!--<input type="hidden" name="voucher.creatorId" value="${voucher.creatorId}">
							<input type="hidden" name="voucher.creatorName" value="${voucher.creatorName}">
							制单人：<input name="input_value"  readonly>&nbsp;&nbsp;&nbsp;&nbsp;仓库总数：${totalQty}&nbsp;&nbsp;&nbsp;&nbsp;现有总数：${totalQtynew}  -->
							制单人：${voucher.employeeName}&nbsp;&nbsp;&nbsp;&nbsp;仓库总数：${totalQty}&nbsp;&nbsp;&nbsp;&nbsp;现有总数：${totalQtynew}
						</td>
					</tr>
					<tr>
						<td align="right" class="noprint">							
						
							<input type="submit" name="method:save" value="保存" <c:if test="${checked}">disabled</c:if> />
							<input type="submit" name="method:exportExcel" value="导出Excel"/>
							<input type="submit" name="method:print" value="打印" id="print"/>
							
						</td>
					</tr>	
					
				</table>
				
			</form>
				
		</div>

	</body>
</html>