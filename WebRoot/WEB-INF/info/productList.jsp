<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
<head>
	<meta name="decorator" content="pop-up" />
</head>
<body>
	
	<form action="/info/productSelect.html" method="post">
	<s:token/>
	<div class="chithead"> 
		<p class="title">整车信息</p>
		<c:if test="${! inventoryIntial}">
		<br/><br/>
		<p align="right">
			<font size="3" color="blue">整车信息</font>
			&nbsp;&nbsp;<font size="3">|</font>&nbsp;&nbsp;
			<a href="/info/voucherPartList.html"><font size="3" color="blue">组件信息</font></a>
		</p>
		</c:if>
	</div>		
	<div class="chithead">	
		<p>
			编码：
			<input name="page.propsNameList" value="code" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[0]}" type="text" class="short_input"/>			
			&nbsp;&nbsp;&nbsp;&nbsp;
			车型：
			<input name="page.propsNameList" value="prefixName" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[1]}" type="text" class="short_input"/>
			<input type="submit" name="submit" value="筛选" />
		</p>
	</div>
	
	<table class="chitdetail" cellspacing="2"  align="center">
		<thead>
			<tr>
				<th width="3%" nowrap>选项</th>
				<th width="3%" nowrap>行号</th>
				<th  nowrap>编码</th>
				<th  nowrap>车型</th>	
				<th  nowrap>电机</th>				
				<th  nowrap>颜色</th>
				<th  nowrap>单位</th>
			</tr>
		</thead>
	
		<c:forEach items="${productList}" var="product"  varStatus="num">
			<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >
				<td nowrap>
					<input type="checkbox" name="selectList" value="${num.index}">
				</td>
				<td nowrap class="sequence">
					${num.count}
				</td>
				<td nowrap>
					${product.code}
				</td>
				<td nowrap>
					${product.prefixName}
				</td>
				<td nowrap>
					${product.suffixName}
				</td>				
				<td nowrap>
					${product.colorName}
				</td>
				<td nowrap>
					${product.unit}
				</td>
			</tr>
		</c:forEach>
	
	</table>
	
	<table>
	<tr>
		<td align="right">
			<input type="submit" name="submit" value="确定"/>
			<input type="submit" name="submit" value="退出"/>
		</td>
	</tr>	
	</table>
	
	<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
	
	</form>	


</body>
</html>