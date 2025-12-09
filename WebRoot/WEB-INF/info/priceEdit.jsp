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
	<form action="/info/priceEdit.html" method="post">	
	<s:token/>
		<div class="chithead">
			<p class="title">
				整车价格管理——整车价格修改页面
			</p>
		</div>
		
		    <div class="chithead"> 
			    <p class="title">整车编码：${product.code}&nbsp;整车名称：${product.prefixName}${product.suffixName}&nbsp;整车颜色：${product.colorName}</p>			
		    </div>
			<table>			
				<tr>	
					<td>
						${priceType.flag}
						${priceType.type}
					</td>
				</tr>
				<tr>	
					<td>
						价格
						<input type="text" name="price.price" value="${price.price}" />
					</td>
					
				</tr>	
				</table>
			<input type="submit" id="submit" name="submit" value="保存" />
			<input type="submit" id="submit" name="submit" value="返回" />

		</form>
		</div>
	</body>
</html>