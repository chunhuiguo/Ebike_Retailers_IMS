<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta name="decorator" content="${ajax}" />
	</head>
	<body>
	<div class="container">
		<form action="/info/priceList.html" method="post">
		<s:token/>
			<div class="chithead">
				<table>
					<tr>
						<td>
							<p class="title">
								整车价格管理——整车价格查询
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<p class="title">
								整车编码：${product.code}
								&nbsp;整车名称：${product.prefixName}${product.suffixName}&nbsp;整车颜色：${product.colorName}
							</p>
						</td>
						<td>
							<input type="submit" id="submit" name="submit" value="选择整车" />
						</td>
					</tr>
				</table>
			</div>
			<span class="errors">${errors['priceType.type'][0]}</span>
			<table class="chitdetail">
				<thead>
					<tr>
						<th width="5%" nowrap>
							选项
						</th>
						<th width="5%" nowrap>
							行号
						</th>
						<th nowrap>
							进价/售价
						</th>
						<th nowrap>
							价格类型名称
						</th>
						<th nowrap>
							价格
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="price" items="${priceList}" varStatus="num">

						<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>
							<td nowrap>
								<input type="checkbox" name="selectId" value="${price.id}"
									<c:if test="${ empty price.price}">disabled="disabled"</c:if>>
							</td>
							<td nowrap width="10%">
								${num.count}
							</td>
							<td nowrap>
								${priceTypeList[num.count-1].flag}
							</td>
							<td nowrap>
								${priceTypeList[num.count-1].type}
							</td>
							<td nowrap>
								<fmt:formatNumber value="${price.price}" pattern="#,##0.00" />
								<!-- <input type="text" name="price.price" value="<fmt:formatNumber value='${price.price}' pattern='#,##0.00'/>" readonly> -->
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
            <span class="errors">${errors['price.price'][0]}</span>
			<table class="operate">
				<tr>
					<td>
						<input type="submit" name="submit" value="设置价格" />
						<input type="submit" name="submit" value="修改" />
						<input type="submit" name="submit" value="删除" />
					</td>
					<td align="right">
						<input type="submit" name="submit" value="打印" />
						<input type="submit" name="submit" value="退出" />
					</td>
				</tr>
			</table>
		</form>
		</div>
		<!-- 
    <script type="text/javascript">
    
	function opener(url,title,id){
		var sw,sh,w=h=400,pra;
		sw=Math.floor((window.screen.width/2-w/2));
		sh=Math.floor((window.screen.height/2-h/2));
		pra="height="+h+", width="+w+", top="+sh+", left="+sw+"menubar=no, scrollbars=yes, resizable=no,location=no, status=no";

		var element = document.getElementById(id);
		element.onclick = function(){
			window.open(url,title,pra,true);
		}
	}
	window.onload = function(){
		
		//opener("/info/store.html","选择仓库","store");

	}
	</script> -->

	</body>
</html>


