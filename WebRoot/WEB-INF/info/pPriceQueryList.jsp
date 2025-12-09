<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta name="decorator" content="${ajax}" />
	</head>
<body>	 
     <div class="container">    	  
	  <form action="/info/pPriceQueryList.html" method="post">
	  <s:token/>
	    <div class="chithead"> 
	    <table>
	    <tr>
	    <td>
	    <p class="title">配件销售价格查询</p>
	    </td>						
		</tr>
		<tr>
		<td><p class="title">配件编码：${part.code} &nbsp;配件名称：${part.name}&nbsp;配件规格：${part.specType}&nbsp;配件颜色：${part.partColor}</p>
		</td>	
		<td><input type="submit" id="submit" name="submit" value="选择配件" />
		</td>	
		</tr>
		</table>					
	    </div>		
		<table class="chitdetail" >
					<thead>
						<tr>
						    <th  width="5%" nowrap>选项</th>	
							<th  width="5%" nowrap>行号</th>			
							<th  nowrap>价格类型</th>
							<th  nowrap>价格</th>										
						</tr>
					</thead>
					<tbody>
						<c:forEach var="pprice" items="${ppriceList}" varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >						
							    <td nowrap>						 
									<input type="checkbox" name="selectId" value="${pprice.id}"<c:if test="${ empty pprice.price}">disabled="disabled"</c:if>>									
								</td>								
								<td nowrap width="10%">
									${num.count}
								</td>
					            <td nowrap>
							         ${priceTypeList[num.count-1].type}
								</td>
								<td nowrap>
									<fmt:formatNumber value="${pprice.price}" pattern="#,##0.00"/>
								</td>																								
						    </tr>
						</c:forEach>					
					</tbody>
				</table>				
								
	<input type="submit" id="submit" name="submit" value="退出" />					
	</form>
	</div>
	</body>
</html>


