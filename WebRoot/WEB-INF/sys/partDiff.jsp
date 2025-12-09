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

			<form action="/sys/partDiff.html" method="post">
			<s:token/>
				<div class="chithead">
					<p class="title">
						待添加绿源配件信息
					</p>
					</div>
		<div class="chithead">		
		<p>
			编码：
			<input name="page.propsNameList" value="partCode" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[0]}" type="text" class="short_input"/>			
			&nbsp;&nbsp;&nbsp;&nbsp;
			名称：
			<input name="page.propsNameList" value="partName" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[1]}" type="text" class="short_input"/>
			<input type="submit" value="筛选" class="gobtn"/>
		</p>
	</div>
	
				<table class="chitdetail">
					<thead>
						<tr>
							<th width="3%" nowrap>选项</th>
							<th width="3%" nowrap>行号</th>
							<th  nowrap>编码</th>
							<th  nowrap>名称</th>	
							<th  nowrap>规格</th>				
							<th  nowrap>单位</th>
						</tr>
					</thead>


					<tbody>
						<c:forEach var="partDiff" items="${partDiffList}" varStatus="num">

							<tr	<c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>

								<td>
									<input type="checkbox" name="selectList" value="${num.index}">
								</td>
								<td nowrap class="sequence">
									${num.count}
								</td>
								<td nowrap>
									${partDiff.partCode}
								</td>
								<td nowrap>
									${partDiff.partName}
								</td>
							    <td nowrap>
									${partDiff.specType}
								</td>							
								<td nowrap>
									${partDiff.unit}
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>

				<table class="operate">

					<tr>
						<td align="right">
							<input type="submit" name="submit" value="添加"/>
						</td>
					</tr>

				</table>
				
				<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
			</form>
		</div>


	</body>
</html>


