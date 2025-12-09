<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
	<head>
		<meta name="decorator" content="login" />
		<script type="text/javascript" src="/js/home/login.js"></script>
	</head>
	
	<body>

		<div id="jsnote">请取消禁用Javascript后再登录！</div>
		
		<form id="loginForm" action="enter.html" method="post" >
			<s:token/>

			<div id="top"></div>
			
			<div id="box">
				
				<img src="/css/login/logo.gif"  id="logo"/>
				
				
				<div>
					<span>用户名：</span><span class="errors">${errors['userCode'][0]}</span>
					<input name="userCode" value=""/>
				</div>

				
				<div>
					<span>密　码：</span><span class="errors">${errors['password'][0]}</span>
					<input type="password" name="password" value=""/>
				</div>
				
				<div>
					<span>验证码：</span><span class="errors">${errors['verifyKey'][0]}</span>
					<input name="verifyKey" value=""/>
				</div>

				<div>
					<img src="kaptcha.jpg" id="kaptchaImage" title="点击图片 重新获得验证码"/>
				</div>
				
				<div><a href="#" id="submit"></a></div>

			</div>

			<div id="bottom"></div>
			
			<input type="hidden" name="loginTime" value="${loginTime}" />
			<input type="hidden" name="encrypt" value="" />

		</form>
		
	</body>

</html>