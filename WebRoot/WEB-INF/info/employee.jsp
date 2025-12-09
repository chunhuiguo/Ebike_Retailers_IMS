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


			
			<form action="/info/employee.html" method="post" >
				<s:token/>
				<div class="chithead"> 
					<p class="title">现有员工信息列表</p>
					
				</div>

				<table class="chitdetail">
					<thead>
						<tr>
							<th width="3%" nowrap>
								选项
							</th>
							<th width="3%" nowrap>
								行号
							</th>
							<th nowrap>
								员工姓名
							</th>
							<th nowrap>
								员工头衔
							</th>
							<th nowrap>
								经销商编码
							</th>
							<th nowrap>
								门店编码
							</th>
							<th nowrap>
								电话
							</th>
							<th nowrap>
								地址
							</th>
							<th nowrap>
								状态
							</th>
							<th nowrap>
								备注
							</th>
						</tr>
					</thead>


					<tbody>
						<c:forEach var="employee" items="${employeeList}" varStatus="num">

							<tr
								<c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>
								
								<td>
									<input type="radio" name="select" value="${num.index}">									
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
									${employee.dealerShortName}
								</td>
								
								<td nowrap>
									${employee.shopShortName}
								</td>
								<td nowrap>
									${employee.phone}
								</td>
								<td nowrap>
									${employee.address}
								</td>
								<td nowrap>
									<c:if test="${employee.disable == true}">有效</c:if>
									<c:if test="${employee.disable == false}">无效</c:if>
								</td>
								<td nowrap>
									${employee.remark}
								</td>
								
							</tr>
						</c:forEach>

					</tbody>
				</table>

			
				
				
				<table class="operate">
					
					<tr >
						<td align="right">
							 <input type="submit" name="submit" value="添加"/>
							 <input type="submit" name="submit" value="修改"/>							 
							 <input type="submit" name="submit" value="查询"/>							  	
							 <input type="submit" name="submit" value="停用"/>	
						</td>						
					</tr>	
					
				</table>
				<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
			</form>
		</div>


	</body>
</html>


