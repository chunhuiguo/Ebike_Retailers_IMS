<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
<head>
	<meta name="decorator" content="${ajax}pop-up" />
</head>
<body>
	<div class="container">
	<form>
	<s:token/>
		<div class="chithead"> 
			<p class="title">员工信息</p>
			<c:if test="${forUser}">
				<br/>&nbsp;&nbsp;<font color="blue">只能选择还没有设置用户的员工</font>	
			</c:if>
			<div class="right">
				<p><a href="${page.listAct}?select=-1">返回</a></p>
			</div>			
		</div>
	
	<table class="chitdetail" align="center">
		<thead>
			<tr>
				<th width="3%" nowrap>选择</th>
				<th width="3%" nowrap>行号</th>
				<th  nowrap>员工姓名</th>
				<th  nowrap>员工头衔</th>
				<th  nowrap>员工电话</th>	
				<th  nowrap>员工地址</th>
				<c:if test="${forUser}"><th  nowrap>用户</th></c:if>				
				<th  nowrap>备注</th>				
			</tr>
		</thead>
	
		<c:forEach items="${employeeList}" var="employee"  varStatus="num">
			<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >
				<td nowrap>
					<c:choose>
						<c:when test="${forUser && (! (userCodeList[num.index] eq ''))}">
							选择
						</c:when>
						<c:otherwise>
							<a href="${page.listAct }?select=${num.index}">选择</a>
						</c:otherwise>
					</c:choose>	
								
				</td>
				<td nowrap class="sequence">
					${num.count}
				</td>
				<td nowrap>
					${employee.name}
				</td>
				<td nowrap>
					${employee.title}
				</td>
				<td nowrap>
					${employee.phone}
				</td>
				<td nowrap>
					${employee.address}
				</td>
				<c:if test="${forUser}">
				<td nowrap>
					${userCodeList[num.index]}
				</td>
				</c:if>		
				<td nowrap>
					${employee.remark}
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