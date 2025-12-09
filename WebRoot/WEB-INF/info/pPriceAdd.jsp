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
	<form action="/info/pPriceAdd.html" method="post">
	<s:token/>
		<div class="chithead"> 
	    <table>
	    <tr>
	    <td>
	    <p class="title">配件价格管理——配件价格设置页面</p>
	    </td>						
		</tr>
		<tr>
		<td><p class="title">配件编码：${part.code }&nbsp;配件名称：${part.name}&nbsp;配件规格：${part.specType}&nbsp;配件颜色：${part.partColor}</p>
		</td>
		<td><p>
			<input type="submit" id="submit" name="submit" value="选择配件" /></p>
		</td>
		</tr></table>	
	     </div>
			<table>		    
				<c:forEach var="list" items="${priceTypeList}" varStatus="num">
					<tr>
						<td>
							${list.flag}
							${list.type}
							<input type="hidden" name="ppriceList[${num.count}-1].priceType" >	
							</td>
							<td>
							<input type="text" name="ppriceList[${num.count}-1].price"/>							
						</td>						
					</tr>
				</c:forEach>
			</table>
			<span class="errors">${errors['pprice1.price'][0]}</span>	
			<span class="errors">${errors['pprice2.price'][0]}</span>	
			<br>
			<input type="submit" id="submit" name="submit" value="保存" />
			<input type="submit" id="submit" name="submit" value="返回" />

		</form>
		 </div>
	</body>
</html>