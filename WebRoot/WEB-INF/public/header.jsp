<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>

	<body>
		<span class="preload0"></span>
		<span class="preload1"></span>
		<span class="preload2"></span>
		
		<ul id="menu">
		
		<li><a href="/luyuan.html" class="top"><span>首页</span></a></li>
		
		<c:forEach items="${sessionScope.menu}" var="menu">   
			<li><a href="#" class="top"><span class="down">${menu.key}</span>
				<!--[if gte IE 7]><!--></a><!--<![endif]--> 
				<!--[if lte IE 6]><table><tr><td><![endif]-->
			   	<ul>
			   	
				<c:forEach items="${menu.value}" var="item">
					<li><a href="${item.url}">${item.opName}</a></li>
				</c:forEach> 
				
				</ul>
				<iframe></iframe>
				<!--[if lte IE 6]></td></tr></table></a><![endif]-->
			</li> 
		</c:forEach> 		
		
		<li><a href="/sys/changePW.html" class="top"><span>修改密码</span></a></li>
		<!--  <li><a href="#" class="top"><span>帮助</span></a></li>  -->
		<li><a href="/logout.html" class="top"><span>退出系统</span></a></li>
		</ul>
	
	
	</body>
</html>