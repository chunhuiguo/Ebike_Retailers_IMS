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
	<form action="/info/part.html" method="post">
	<s:token/>
	<div class="chithead"> 
		<p class="title">已添加绿源配件信息</p>
		
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
			<input type="submit" name="submit" value="筛选" />
		</p>
	</div>
	
	<table class="chitdetail" cellspacing="2"  align="center">
		<thead>
			<tr>
				<th width="3%" nowrap>行号</th>
				<th  nowrap>编码</th>
				<th  nowrap>名称</th>	
				<th  nowrap>规格</th>				
				<th  nowrap>颜色</th>
				<th  nowrap>单位</th>
			</tr>
		</thead>
	
		<c:forEach items="${partList}" var="part"  varStatus="num">
			<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >				
				<td nowrap class="sequence">
					${num.count}
				</td>
				<td nowrap>
					${part.code}
				</td>
				<td nowrap>
					${part.name}
				</td>
				<td nowrap>
					${part.specType}
				</td>				
				<td nowrap>
					${part.partColor}
				</td>
				<td nowrap>
					${part.unit}
				</td>
			</tr>
		</c:forEach>
	
	</table>	
	
	<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
	
	</form>	
	</div>

</body>
</html>