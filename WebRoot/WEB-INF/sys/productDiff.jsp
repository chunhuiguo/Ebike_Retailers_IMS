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

			<form action="/sys/productDiff.html" method="post">
				<s:token/>
				<div class="chithead">
					<p class="title">
						待添加绿源整车信息
					</p>
					</div>
		<div class="chithead">		
		<p>
			编码：
			<input name="page.propsNameList" value="productCode" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[0]}" type="text" class="short_input"/>			
			&nbsp;&nbsp;&nbsp;&nbsp;			
			车型：
			<input name="page.propsNameList" value="prefixName" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[1]}" type="text" class="short_input"/>
			<input type="submit" value="筛选" class="gobtn"/>
		</p>
	</div>
	
				<table class="chitdetail">
					<thead>
						<tr>
							<th nowrap width="2%">
								选项
							</th>
							<th nowrap width="2%">
								行号
							</th>
							<th nowrap width="4%">
								编码
							</th>
							<th nowrap width="4%">
								车型
							</th>
							<th nowrap width="4%">
								电机
							</th>							
							<th nowrap width="2%">
								轮胎尺寸
							</th>
							<th nowrap width="3%">
								颜色
							</th>							
							<th nowrap width="2%">
								计量单位
							</th>
							<th nowrap width="4%">
								标配电池编码
							</th>
							<th nowrap width="3%">
								标配电池数量
							</th>
							<th nowrap width="5%">
								标配电池描述
							</th>							
							
						</tr>
					</thead>


					<tbody>
						<c:forEach var="productDiff" items="${productDiffList}" varStatus="num">

							<tr	<c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>

								<td>
									<input type="checkbox" name="selectList" value="${num.index}">
								</td>
								<td nowrap class="sequence">
									${num.count}
								</td>
								<td nowrap>
									${productDiff.productCode}
								</td>
								<td nowrap>
									${productDiff.prefixName}
								</td>
								<td nowrap>
									${productDiff.suffixName}
								</td>								
								<td nowrap>
									${productDiff.wheelDiameter}
								</td>
								
								<td nowrap>
									${productDiff.colorName}
								</td>								
								<td nowrap>
									${productDiff.unit}
								</td>
								<td nowrap>
									${productDiff.batteryCode}
								</td>
								<td nowrap>
									${productDiff.batteryQty}
								</td>
								<td nowrap>
									${productDiff.batteryDesc}
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


