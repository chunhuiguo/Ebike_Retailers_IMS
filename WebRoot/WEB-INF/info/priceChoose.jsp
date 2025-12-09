<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta name="decorator" content="pop-up" />
	</head>
	<body>
		<div class="chithead">
			<p class="title">
				整车价格管理
			</p>
		</div>
			<form action="/info/priceChoose.html" method="post">
			<s:token/>
			 	<div class="chithead"> 
					<p class="title">整车编码：${list[0].productCode}&nbsp;整车名称：${list[0].prefixName}${list[0].suffixName}&nbsp;整车颜色：${list[0].colorName}</p>			
				</div>
				<table class="chitdetail" >
					<thead>
						<tr>
						    <th  width="5%" nowrap>选项</th>	
							<th  width="5%" nowrap>行号</th>								
							<th  nowrap>价格名称</th>
							<th  nowrap>价格</th>
							<th  nowrap>折扣</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="price" items="${list}" varStatus="num">

							<tr <c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if> >						
							    <td nowrap>						 
									<input type="checkbox" name="selectId" value="${price.id}"<c:if test="${ empty price.price}">disabled="disabled"</c:if>>									
									</td>				
								<td nowrap width="10%">
									${num.count}
								</td>								
					            <td nowrap>
							        ${typeList[num.count-1].type}
								</td>
								<td nowrap>
									${price.price}
								</td>
								<td nowrap>
									${price.discount}
								</td>																	
						    </tr>
						</c:forEach>						
					</tbody>
				</table>				
				<table class="page"><tr><td>
				<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
				</td></tr>
				</table>					
				<table class="operate">					
					<tr >
						<td>
							 <input type="submit" name="submit" value="选中" />
							 <input type="submit" name="submit" value="退出" />							 		
						</td>						
					</tr>					
				</table>			
			</form>
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


