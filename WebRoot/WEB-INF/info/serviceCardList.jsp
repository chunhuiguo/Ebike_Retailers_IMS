<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">

<html>
	<head>
		<meta name="decorator" content="${ajax}" />		
		<script type="text/javascript" src="/js/info/serviceCardList.js"></script>	
	</head>

	<body>
		<div class="container">
			<form action="/info/serviceCardList.html" method="post">	
				<s:token/>			
				<div class="chithead"> 
					<p class="title">服务卡查询</p>
					<br/>						
				</div>									
				
	<div class="chithead">	
		<p>
			经销单位：			
			<input value="${dealerShortName}" type="text" class="short_input" disabled/>			
			&nbsp;&nbsp;&nbsp;&nbsp;
			整车型号：
			<input name="page.propsNameList" value="productPrefixName" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[0]}" type="text" class="short_input"/>
			&nbsp;&nbsp;&nbsp;&nbsp; 
			服务卡号：
			<input name="page.propsNameList" value="serviceCardNumber" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[1]}" type="text" class="short_input"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			整车颜色：
			<input name="page.propsNameList" value="colorName" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[2]}" type="text" class="short_input"/>
		</p>
		<p>
			整车编码：			
			<input name="page.propsNameList" value="productBarcode" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[3]}" type="text" class="short_input"/>			
			&nbsp;&nbsp;&nbsp;&nbsp;
			控制器号：
			<input name="page.propsNameList" value="controlBarcode" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[4]}" type="text" class="short_input"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			车架编码：
			<input name="page.propsNameList" value="frameBarcode" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[5]}" type="text" class="short_input"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			轮毂编码：
			<input name="page.propsNameList" value="hubBarcode" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[6]}" type="text" class="short_input"/>
		</p>
		<p>
			购车日期：			
			<input type="text" name="buyStartDate" value="${buyStartDate}" class="short_input" readonly id="date1" />	
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
			<input type="text" name="buyEndDate" value="${buyEndDate}" class="short_input" readonly id="date2" />	
			&nbsp;&nbsp;&nbsp;&nbsp;
			输卡日期：			
			<input type="text" name="inputStartDate" value="${inputStartDate}" class="short_input" readonly id="date3" />	
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
			<input type="text" name="inputEndDate" value="${inputEndDate}" class="short_input" readonly id="date4" />				
		</p>
		<p>
			用户姓名：			
			<input name="page.propsNameList" value="customerName" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[7]}" type="text" class="short_input"/>			
			&nbsp;&nbsp;&nbsp;&nbsp;
			来源数据：
			<input type="radio" name="history" value="false" <c:if test="${! history}">checked</c:if> >当前数据&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="history" value="true" <c:if test="${history}">checked</c:if> >历史数据
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
			<input type="submit" name="method:filter" value="筛选" />
			&nbsp;&nbsp;&nbsp;&nbsp;	
			<input type="submit" value="重置" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
			<input type="submit" name="method:exportExcel" value="导出Excel" />
		</p>
	</div>
	
				<div class="chithead">	
					<p>
						<font size="3">总共筛选到${totalCount}条服务卡信息</font>											
					</p>
				</div>				
	
				<table class="chitdetail" >					
					<thead>
						<tr>
							<th width="3%" nowrap>详细</th>
							<th width="3%" nowrap>行号</th>
							<!--<th  nowrap>经销单位</th>-->
							<th  nowrap>整车编码</th>
							<th  nowrap>整车型号</th>
							<th  nowrap>车架编号</th>
							<th  nowrap>电机编号</th>
							<th  nowrap>控制器编号</th>
							<th width="4%" nowrap>用户姓名</th>
							<th width="7%" nowrap>手机号码</th>
							<th width="4%" nowrap>地址</th>
							<th width="4%" nowrap>联系电话</th>
							<th width="7%" nowrap>购车日期</th>
							<th width="7%" nowrap>输卡日期</th>
						</tr>
					</thead>
					
					
					<tbody>
						<c:forEach var="productCustomer" items="${productCustomerList}" varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>
								<td nowrap>
									<a href="/info/serviceCardList!detail.html?select=${num.index}">详细</a>
								</td>
								<td nowrap class="sequence" >
									${num.count}
								</td>
								<!--<td nowrap>
									${productCustomer.dealerCode}
								</td>-->
								<td nowrap>
									${productCustomer.productBarcode}
								</td>
								<td nowrap>
									${productCustomer.productPrefixName}
								</td>
								<td nowrap>
									${productCustomer.frameBarcode}
								</td>
								<td nowrap>
									${productCustomer.hubBarcode}
								</td>
								<td nowrap>
									${productCustomer.controlBarcode}
								</td>
								<td nowrap>
									${productCustomer.customerName}
								</td>									
								<td nowrap>
									${productCustomer.customerCellPhone}
								</td>
								<td nowrap>
									${productCustomer.customerAddress}
								</td>
								<td nowrap>
									${productCustomer.customerPhone}
								</td>
								<td nowrap>
									${productCustomer.buyDate}
								</td>
								<td nowrap>
									${productCustomer.inputDate}
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


