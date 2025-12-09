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
			<form action="dayChoose.html" method="post" name="purchase">
			<s:token/>
			 	<div class="chithead"> 
					<p class="title">选择日期和仓库页面</p>			
				</div>
				<table>
				<tr>
				    <td>				
						日期：<input name="voucher.checkDate" value="${voucher.checkDate}" id="date" class="short_input" readonly />					
					</td>
									
				</tr>
				<tr>	
					<td>			
						仓库：<input  name="warehouse.name" value="${warehouse.name}" class="short_input" readonly/>
						<input type="hidden" name="warehouse.id" value="${warehouse.id}" >
		                <input type="submit" name="submit" value="选择仓库" />															
					</td>					
				</tr>																
				</table>
				<span class="errors">${errors['date'][0]}</span>
				<span class="errors">${errors['lastMonth'][0]}</span>
				<table class="operate">					
					<tr >
						<td>
							 <input type="submit" name="submit" value="确定" />							
							 <input type="submit" name="submit" value="取消"/>				
						</td>						
					</tr>					
				</table>													
			</form>
<script type="text/javascript">
	function date_input(){
		var element = document.getElementById("date");
		element.onfocus = function(){
			WdatePicker();
		}
	}
	window.onload = function(){
		date_input();
		//opener("/info/supplier.html","供货单位","partaker");
		//opener("/info/employee.html","经手人","handler");
		//opener("/info/department.html","部门","department");
		//opener("/info/store.html","收货仓库","store");
	}	
	</script>   
	</body>
</html>


