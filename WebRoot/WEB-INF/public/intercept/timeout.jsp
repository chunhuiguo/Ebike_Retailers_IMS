<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
	<head>
		<title>重新登录</title>
		<meta name="decorator" content="error" />
	</head>

	<body>

		
			
	
		<br/> <br/> <br/>
		<h3>由于您长时间不操作或非法访问，请重新登录！</h3><a href="/login.html" target="_top">回登录页面</a><br>
	
		
		
		<script language="javascript">
		function redirectUrl(redirectTime) { 
			setTimeout("window.location.href='/login.html';",redirectTime*1000); 
		} 
		self.onload=function(){ 
		}; 
		
		redirectUrl(2); 
		</script>



	</body>
</html>
