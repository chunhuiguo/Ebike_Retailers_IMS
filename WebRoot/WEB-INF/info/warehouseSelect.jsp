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
		
	
		<div class="container">

			
			<form action="/info/warehouseSelectInfo.html" method="post">
			<s:token/>	
				<div class="chithead"> 
					<p class="title">仓库信息管理--查询</p>
				</div>
	<div class="chithead">	
		<p>
			名称：
			<input name="page.propsNameList" value=name type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[0]}" type="text" class="short_input"/>						
			&nbsp;&nbsp;&nbsp;&nbsp;
			门店：
			<input name="page.propsNameList" value="shopShortName" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[1]}" type="text" class="short_input"/>
			&nbsp;&nbsp;&nbsp;&nbsp;									
			<input type="submit" name="submit" value="筛选" class="gobtn"/>
		</p>
	</div>   
	
				<table class="chitdetail">
					<thead>
						<tr>
							<th width="3%" nowrap>
								选项
							</th>
							<th nowrap>
								行号
							</th>
							<th nowrap>
								仓库名称
							</th>
							<th nowrap>
								门店简称
							</th>
							<th nowrap>
								仓库地址
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
						<c:forEach var="warehouse" items="${warehouseList}" varStatus="num">

							<tr id=wml
								<c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>
								
								<td>
									<input type="radio" name="select" value="${num.index}">
								</td>
								<td nowrap class="sequence">
									${num.count}
								</td>
								<td nowrap>
									${warehouse.name}
								</td>
								<td nowrap>
									${warehouse.shopShortName}
								</td>
								<td nowrap>
									${warehouse.address}
								</td>
								<td nowrap>
									<c:if test="${warehouse.disable == true}">有效</c:if>
									<c:if test="${warehouse.disable == false}">无效</c:if>
								</td>
								<td nowrap>
									${warehouse.remark}
								</td>
								
							</tr>
						</c:forEach>

					</tbody>
				</table>
				
				
				<table class="operate">
					
					<tr >
						<td align="right">						
							
							<input type="submit" name="submit" value="修改"/>
							<input type="submit" name="submit" value="退出"/>
							

						</td>
					</tr>	
					
				</table>
				
				<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
			</form>
		</div>
		

	</body>
</html>


