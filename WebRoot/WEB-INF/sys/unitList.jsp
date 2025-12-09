<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
<head>
	<meta name="decorator" content="${ajax}pop-up" />
	<script type="text/javascript" src="/js/sys/unitList.js"></script>
</head>
<body>
	<div class="container">
	<form>
	<s:token/>
	<div class="chithead"> 
		<p class="title">往来单位信息</p>	
		<div class="right">
				<p><a href="${page.listAct}?select=-1">返回</a></p>
			</div>			
	</div>
	<table class="chitdetail" align="center">
		<thead>
			<tr>
				<th width="3%" nowrap>选择</th>
				<th width="3%" nowrap>行号</th>
				<th  nowrap>单位简称</th>
				<th  nowrap>单位全称</th>
				<th  nowrap>单位类型</th>
				<th  nowrap>单位电话</th>	
				<th  nowrap>单位地址</th>								
				<th  nowrap>城市</th>
				<th  nowrap>省份</th>
			</tr>
		</thead>
	
		<c:forEach items="${unitList}" var="unit"  varStatus="num">
			<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >
				<td nowrap>
					<a href="${page.listAct }?select=${num.index}">选择</a>
				</td>
				<td nowrap class="sequence">
					${num.count}
				</td>
				<td nowrap>
					${unit.shortName}
				</td>
				<td nowrap>
					${unit.fullName}
				</td>
				<td nowrap>
					${unit.type}
				</td>
				<td nowrap>
					${unit.phone}
				</td>
				<td nowrap>
					${unit.address}
				</td>				
				<td nowrap>
					${unit.city}
				</td>
				<td nowrap>
					${unit.province}
				</td>
			</tr>
		</c:forEach>
	
	</table>
	
	<!--  
	<table>
		<td align="right">
			<input type="submit" name="submit" value="确定"/>
			<input type="submit" name="submit" value="退出"/>
		</td>
	</table>
	-->
	<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
	</form>	
	</div>
</body>
</html>