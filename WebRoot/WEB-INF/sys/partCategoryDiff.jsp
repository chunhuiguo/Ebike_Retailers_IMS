<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
	<head>
		<meta name="decorator" content="${ajax}" />
	</head>

	<body>


		<div class="container">

			<form action="/sys/partCategoryDiff.html" method="post">
			<s:token/>
				<div class="chithead">
					<p class="title">
						公有配件分类
					</p>
					<p class="right">
						 配件名称：
						<input name="page.propsNameList" value="categoryName" type="hidden" />
						<input name="page.propsValueList" value="${page.propsValueList[0]}" type="text" class="long_input"/>
						<input type="submit" value="GO" class="gobtn"/>
					</p>
				</div>
				
				<table class="chitdetail">
					<thead>
						<tr>
							<th nowrap width="4%">
								选项
							</th>
							<th nowrap width="4%">
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
						<c:forEach var="partCategoryDiff" items="${partCategoryList}" varStatus="num">

							<tr	<c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>

								<td>
									<input type="checkbox" name="selectId" value="${partCategoryDiff.id.categoryId}">
								</td>
								<td nowrap class="sequence">
									${num.count+page.size*(page.cur-1)}
								</td>
								<td nowrap>
									${partCategoryDiff.categoryCode}
								</td>
								<td nowrap>
									${partCategoryDiff.categoryName}
								</td>
								<td nowrap>
									${partCategoryDiff.categoryLevel}
								</td>
								<td nowrap>
									${partCategoryDiff.parentCategoryCode}
								</td>
								<td nowrap>
									${partCategoryDiff.discription}
								</td>
								
							</tr>
						</c:forEach>

					</tbody>
				</table>
				
				<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />

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


