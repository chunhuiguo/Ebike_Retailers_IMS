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
	    <form action="/info/typeList.html" method="post">
	    <s:token/>
	    <div class="chithead"> 
	    <table>
	    <tr><td>
	    <p class="title">价格类型管理——价格类型列表</p></td>				
		   <td><p><select name="select">
				   <option value="all">全部显示</option>
				   <option value="product" <c:if test= "${select =='product'}"> selected </c:if>>显示整车价格类型</option>
				   <option value="part" <c:if test= "${select =='part'}"> selected </c:if>>显示配件价格类型</option>
				  </select>
			  <input type="submit" name="submit" value="筛选" /></p>	</td>
			  </tr></table>					
	    </div>
	      <table class="chitdetail" cellspacing="2"  align="center">
		   <thead>
			<tr>
				<th width="5%" nowrap>选项</th>
				<th width="5%" nowrap>行号</th>
				<th  nowrap>价格类型名称</th>
				<th  nowrap>整车/配件</th>
				<th  nowrap>进价/售价</th>				
			</tr>
		  </thead>
	
		<c:forEach items="${priceTypeList}" var="priceType"  varStatus="num">
			<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >
				<td nowrap>
					<input type="radio" name="selectId" value="${priceType.id}">
				</td>
				<td nowrap class="sequence">
					${num.count}
				</td>
				<td nowrap>
					${priceType.type}
				</td>
				<td nowrap>
					${priceType.ppFlag}
				</td>
				<td nowrap>
					${priceType.flag}
				</td>								
			</tr>
		</c:forEach>	
	</table>	
	<table class="operate">					
					<tr >
						<td>
							 <input type="submit" name="submit" value="添加" />
							 <input type="submit" name="submit" value="修改" />
						   				
														
						</td>
						<td align="right">						
							<input type="submit" name="submit" value="打印"/>
							<input type="submit" name="submit" value="退出"/>
						</td>
					</tr>					
		</table>
	</form>	
</div>
</body>
</html>