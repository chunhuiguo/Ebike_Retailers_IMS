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
		<div class="chithead">
			<p class="title">
				价格类型管理——价格类型修改页面
			</p>
		</div>
		<form action="/info/typeEdit.html" method="post">	
		<s:token/>	
			<table>
				<tr>
				    <td>				
						请选择要添加整车或配件：</td><td>									
						<input type="radio" name="priceType.ppFlag"  <c:if test= "${priceType.ppFlag =='整车'}"> checked </c:if> value="整车" />整车								
						<input type="radio" name="priceType.ppFlag" <c:if test= "${priceType.ppFlag =='配件'}"> checked </c:if> value="配件" >配件
					</td>					
				</tr>
				<tr>	
					<td>				
						请选择要添加进价或售价：</td><td>					
						<input type="radio" name="priceType.flag" <c:if test= "${priceType.flag =='进价'}"> checked </c:if> value="进价" />进价			
						<input type="radio" name="priceType.flag" <c:if test= "${priceType.flag =='售价'}"> checked </c:if> value="售价" />售价
					</td>					
				</tr>												
				<tr>	
					<td>
						价格类型名称：</td><td>					
						<input type="text" name="priceType.type" value="${priceType.type}" />
						<span class="errors">${errors['priceType.type'][0]}</span>
					</td>					
				</tr>	
				</table>						
			<input type="submit" id="submit" name="submit" value="保存" />
			<input type="submit" id="submit" name="submit" value="返回" />

		</form>
	</body>
</html>