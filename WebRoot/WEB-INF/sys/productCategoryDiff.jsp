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

			<form action="/info/productCategoryAdd.html" method="post">
			<s:token/>
				<div class="chithead">
					<p class="title">
						待添加的绿源整车分类列表
					</p>
				</div>	
					
					
					<a href="/info/productCategory.html">查看已添加的绿源整车分类列表</a><br/>
				分类名称：<input type="text" name="">
				<input type="submit" name="select" value="查询">
				<table class="chitdetail">
					<thead>
						<tr>
							<th nowrap width="3%">
								选项
							</th>
							<th nowrap width="3%">
								行号
							</th>
							<th nowrap width="12%">
								分类编码
							</th>
							<th nowrap width="12%">
								分类名称
							</th>
							<th nowrap width="12%">
								分类等级
							</th>
							<th nowrap width="12%">
								上级分类编码
							</th>
							<th nowrap width="12%">
								细节描述
							</th>
							
						</tr>
					</thead>


					<tbody>
						<c:forEach var="productCategoryDiff" items="${productCategoryList}" varStatus="num">

							<tr	<c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>

								  <td>
									<input type="checkbox" name="selectId" value="${productCategoryDiff.id.categoryId}">
								</td>
								
								<td nowrap class="sequence">
									${num.count}
								</td>
								<td nowrap>
									${productCategoryDiff.categoryCode}
								</td>
								<td nowrap>
									${productCategoryDiff.categoryName}
								</td>
								<td nowrap>
									${productCategoryDiff.categoryLevel}
								</td>
								<td nowrap>
									${productCategoryDiff.parentCategoryCode}
								</td>
								<td nowrap>
									${productCategoryDiff.discription}
								</td>
								
								
							</tr>
						</c:forEach>

					</tbody>
				</table>

				 <table class="operate">

					<tr>
						<td>
							<input type="submit" name="submit" value="添加"/>
						</td>
					</tr>

				</table> 
			</form>
		</div>


	</body>
</html>


