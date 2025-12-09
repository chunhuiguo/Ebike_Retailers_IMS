<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
<head>
	<meta name="decorator" content="${ajax}pop-up" />
</head>
<body>
	<div class="container">
	<form action="/info/partInventoryList.html" method="post">
	<div class="chithead"> 
		<p class="title"><c:if test="${part}">配件</c:if><c:if test="${! part}">组件</c:if>信息</p>
		<!--  
		<c:if test="${! part}">
			<br/><br/>
			<p align="right">
				<a href="/info/productInventoryList!list.html?result=${result}&amp;warehouseId=${warehouseId}&amp;dealingUnitId=${dealingUnitId}"><font size="3" color="blue">整车信息</font></a>
				&nbsp;&nbsp;<font size="3">|</font>&nbsp;&nbsp;
				<font size="3" color="blue">组件信息</font>
			</p>
		</c:if>
		-->
	</div>		
	<div class="chithead">	
		<p>
			编码：
			<input name="page.propsNameList" value="code" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[0]}" type="text" class="short_input"/>			
			&nbsp;&nbsp;&nbsp;&nbsp;
			名称：
			<input name="page.propsNameList" value="name" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[1]}" type="text" class="short_input"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			规格：
			<input name="page.propsNameList" value="specType" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[2]}" type="text" class="short_input"/>
			<input type="submit" name="method:filter" value="筛选" />
		</p>
	</div>
	
	<table class="chitdetail" align="center">
		<thead>
			<tr>
				<th width="3%" nowrap>选项</th>
				<th width="3%" nowrap>行号</th>
				<th  nowrap>编码</th>
				<th  nowrap>名称</th>	
				<th  nowrap>规格</th>				
				<th  nowrap>颜色</th>
				<th  nowrap>单位</th>
				<th  nowrap>门店</th>				
				<th  nowrap>仓库</th>
				<th  nowrap>库存</th>
			</tr>
		</thead>
	
		<c:forEach items="${partInventoryList}" var="partInventory"  varStatus="num">
			<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >
				<td nowrap>
					<input type="checkbox" name="selectList" value="${num.index}">
				</td>
				<td nowrap class="sequence">
					${num.count}
				</td>
				<td nowrap>
					${partInventory.code}
				</td>
				<td nowrap>
					${partInventory.name}
				</td>
				<td nowrap>
					${partInventory.specType}
				</td>				
				<td nowrap>
					${parInventoryt.partColor}
				</td>
				<td nowrap>
					${partInventory.unit}
				</td>
				<td nowrap>
					${partInventory.shopShortName}
				</td>				
				<td nowrap>
					${partInventory.warehouseName}
				</td>
				<td nowrap>
					${partInventory.qty}
				</td>
			</tr>
		</c:forEach>
	
	</table>
	
	<table>
	<tr>
		<td align="right">
			<input type="submit" name="method:confirm" value="确定"/>
			<input type="submit" name="method:cancel" value="退出"/>
		</td>
	</tr>	
	</table>
	
	<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
	
	</form>	
	</div>

</body>
</html>