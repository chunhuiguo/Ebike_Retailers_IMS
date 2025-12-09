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


			<form action="/money/draft.html" method="post">
			<s:token/>
				<div class="chithead">
					<p class="title">
						未处理账款单据
					</p>
					</div>
					<div class="left">
						<p>
							单据类型
							<select name="accountType">
								<option value="所有单据"
									<c:if test="${accountType == '所有单据'}">selected="selected"</c:if>>
									所有单据
								</option>

								<option value="付款单"
									<c:if test="${accountType == '付款单'}">selected="selected"</c:if>>
									付款单
								</option>
								<option value="收款单"
									<c:if test="${accountType == '收款单'}">selected="selected"</c:if>>
									收款单
								</option>
							</select>
							<input type="submit" name="submit" value="筛选">
						</p>
				</div>


				<table class="chitdetail" cellspacing="2" align="center">
					<thead>
						<tr>
							<th width="3%" nowrap>
								选项
							</th>
							<th width="3%" nowrap>
								行号
							</th>
							<th nowrap>
								制单日期
							</th>
							<th nowrap>
								单据编号
							</th>
							<th nowrap>
								单据类型
							</th>
							<th nowrap>
								单据摘要
							</th>
							<th nowrap>
								制单人
							</th>
						</tr>
					</thead>


					<tbody>
						<c:forEach var="dealing" items="${dealingList}" varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>
								<td nowrap>
									<input type="checkbox" name="selectList" value="${dealing.id}">
								</td>
								<td nowrap>
									${num.count}

								</td>
								<td nowrap>
									${dealing.createDate}
								</td>
								<td nowrap>
									${dealing.code}
								</td>
								<td nowrap>
									${dealing.type}
								</td>
								<td nowrap align="left">
									${dealing.brief}
								</td>
								<td nowrap>
									${dealing.creatorName}
								</td>

							</tr>
						</c:forEach>
					</tbody>


				</table>



				<input type="submit" name="submit" value="修改单据">
				<input type="submit" name="submit" value="删除单据">
				
				<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />

			</form>


		</div>



	</body>
</html>

