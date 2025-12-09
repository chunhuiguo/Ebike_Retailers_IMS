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
		<form action="/info/priceAdd.html" method="post">
		<s:token/>
		<div class="chithead"> 
	    <table>
	    <tr>
	    <td>
	    <p class="title">整车价格管理——整车价格设置页面</p>
	    </td>						
		</tr>
		<tr>
		<td><p class="title">整车编码：${product.code} &nbsp;整车名称：${product.prefixName}${product.suffixName}&nbsp;整车颜色：${product.colorName}</p>
		</td>
		<td><p>
			<input type="submit" id="submit" name="submit" value="选择整车" /></p>
		</td>
		</tr></table>	
	     </div>							    
			<table >
			<tr>
			 <td>				
			不同颜色的同一款整车是否需要单独设价：						
			</td>
			<td>			
			<input type="radio" name="radio"  value="否" checked/>否											
			<input type="radio" name="radio"  value="是" />是
			</td>
			</tr>	    
				<c:forEach var="list" items="${priceTypeList}" varStatus="num">
					<tr>
						<td>
						    ${list.flag}
							${list.type}
							<input type="hidden" name="priceList[${num.count}-1].priceType" ></td>
							<td><input type="text" name="priceList[${num.count}-1].price"/></td>
																										
					</tr>
				</c:forEach>
				
			</table>
			<span class="errors">${errors['price1.price'][0]}</span>	
			<span class="errors">${errors['price2.price'][0]}</span>	
			<br>
			<input type="submit" id="submit" name="submit" value="保存" />
			<input type="submit" id="submit" name="submit" value="返回" />
		</form>
		 </div>	
	</body>
</html>