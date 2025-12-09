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
				价格类型设置
			</p>
		</div>
			<form action="/info/typeAdd.html" method="post" name="purchase">
			<s:token/>
			 	<div class="chithead"> 
					<p class="title">请添加您所需要的价格类型</p>			
				</div>
				<table>
				<tr>
				    <td>				
						请选择是为整车还是配件添加价格类型
					</td>	
					<td>				
						<input type="radio" name="priceType.ppFlag" value="整车" checked="checked"/>整车
					</td>
					<td>				
						<input type="radio" name="priceType.ppFlag" value="配件" />配件
					</td>					
				</tr>
				<tr>	
					<td>				
						请选择要添加的价格类型
					</td>
					<td>				
						<input type="radio" name="priceType.flag" value="进价" />进价
					</td>
					<td>				
						<input type="radio" name="priceType.flag" value="售价" checked="checked"/>售价
					</td>					
				</tr>												
				<tr>	
					<td>
						价格名称				
						<input type="text" name="priceType.type" value="${priceType.type}" />
					</td>					
				</tr>	
				</table>										
				<table class="page"><tr><td>
				<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
				</td></tr>
				</table>					
				<table class="operate">					
					<tr >
						<td>
							 <input type="submit" name="submit" value="保存" />							
							 <input type="submit" name="submit" value="查看"/>				
						</td>
						<td align="right">						
							<input type="submit" name="submit" value="打印"/>
							<input type="submit" name="submit" value="退出"/>
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


