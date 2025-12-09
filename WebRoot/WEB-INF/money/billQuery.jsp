<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
	<head>
		<meta name="decorator" content="pop-up" />
	</head>
	<body>
		<div class="container">
			<form action="/money/billQuery.html" method="post">
				<s:token/>
				<div class="chithead"> 
					<p class="title">查询条件</p>
				</div>				
				<table class="chitbody" cellspacing="4">					
					<tr><td>						
						<p>往来单位</p>												
						<input type="hidden" name="dealing.dealingUnitId" value="${dealing.dealingUnitId}">											
						<input type="text" name="dealing.dealingUnitShortName" value="${dealing.dealingUnitShortName}" class="short_input" readonly/>
						<input type="submit" name="submit" value="选择往来单位" />							
					</td></tr>					
					<tr><td>
						<p>单据编码</p>						
						<input type="text" name="dealing.code" value="${dealing.code}" class="code_input" />												
					</td></tr>
					
					<tr><td>	
						<p>单据类型</p>
						<select name="dealing.brief" class="short_input">								
							<option value="所有单据" >所有单据</option>
							<option value="收款单" >收款单</option>
							<option value="付款单" >付款单</option>					
						</select>						
					</td></tr>
					<tr><td>	
						<p>冲抵过滤</p>
						<select name="errorType" class="short_input">								
							<option value="所有单据" >所有单据</option>
							<option value="未冲抵单据" >未冲抵单据</option>
							<option value="冲抵单据" >冲抵单据</option>													
						</select>
					</td></tr>
					<tr><td>						
						<p>开始日期</p>												
						<input name="dealing.checkDate" value="${dealing.checkDate}" id="checkDate" class="short_input" readonly />
					</td></tr>
					<tr><td>						
						<p>结束日期</p>												
						<input name="dealing.createDate" value="${dealing.createDate}" id="createDate" class="short_input" readonly />
					</td></tr>
				</table>				
				<table>					
					<tr >						
						<td align="right">
							<input type="submit" name="submit" value="确定" />
							<input type="submit" name="submit" value="重置" />
							<input type="submit" name="submit" value="退出" />							
						</td>
					</tr>						
				</table>				
			</form>
				
		</div>	
		
	<script type="text/javascript">
	function checkDateInput(){
		var element = document.getElementById("checkDate");
		element.onfocus = function(){
			WdatePicker();
		}
	}	

	function createDateInput(){
		var element = document.getElementById("createDate");
		element.onfocus = function(){
			WdatePicker();
		}
	}

	window.onload = function(){
		checkDateInput();
		createDateInput();		
	}
	</script>
			
	</body>
</html>

