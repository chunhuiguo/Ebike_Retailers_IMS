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

			
			<form action="/sys/unitUpdate.html" method="post" >
				<s:token/>
				<div class="chithead"> 
					<p class="title">往来单位信息管理--修改</p>
				</div>
				<table class="chitbody" cellspacing="4">
					<tr><td>						
						<p>往来单位简称</p>												
						<input type="text" name="unit.shortName" value="${unit.shortName}" />*	
						<span class="errors">${errors['unit.shortName'][0]}</span>					
					</td></tr>	
					<tr><td>						
						<p>往来单位全称</p>												
						<input type="text" name="unit.fullName" value="${unit.fullName}" />*	
						<span class="errors">${errors['unit.fullName'][0]}</span>				
					</td></tr>	
					<tr><td>						
						<p>往来单位类型</p>												
						<select name="unit.type" >
							<option value="供应商" <c:if test="${unit.type == '供应商'}">selected</c:if> >供应商</option>
							<option value="零售客户" <c:if test="${unit.type == '零售客户'}">selected</c:if> >零售客户</option>				            
				            <option value="直属门店" <c:if test="${unit.type == '直属门店'}">selected</c:if> >直属门店</option>
				            <option value="承包门店" <c:if test="${unit.type == '承包门店'}">selected</c:if> >承包门店</option> 			             
				        </select>					
					</td></tr>	
					<tr><td>						
						<p>负责人名称</p>												
						<input type="text" name="unit.directorName" value="${unit.directorName}" />						
					</td></tr>	
					<tr><td>						
						<p>负责人头衔</p>
						<input type="text" name="unit.directorTitle" value="${unit.directorTitle}" />					
					</td></tr>	
					<tr><td>						
						<p>负责人电话</p>												
						<input type="text" name="unit.directorPhone" value="${unit.directorPhone}" />						
					</td></tr>	
					<tr><td>						
						<p>单位电话</p>												
						<input type="text" name="unit.phone" value="${unit.phone}" />					
					</td></tr>	
					<tr><td>						
						<p>单位传真</p>												
						<input type="text" name="unit.fax" value="${unit.fax}" />				
					</td></tr>	
					<tr><td>						
						<p>单位地址</p>												
						<input type="text" name="unit.address" value="${unit.address}" />						
					</td></tr>	
					<tr><td>						
						<p>城市</p>												
						<input type="text" name="unit.city" value="${unit.city}" />					
					</td></tr>	
					<tr><td>						
						<p>省份</p>												
						<input type="text" name="unit.province" value="${unit.province}" />					
					</td></tr>	
					<tr><td>						
						<p>状态</p>												
						<input type="radio" name="unit.disable" value="true" <c:if test="${unit.disable}">checked</c:if> />有效<br/>
				        <input type="radio" name="unit.disable" value="false" <c:if test="${! unit.disable}">checked</c:if> />无效			
					</td></tr>			
				</table>			
				
				<table class="operate">
					
					<tr >
						<td>		
						</td>
						
						<td align="right">
							
							<input type="submit" name="submit" value="保存"/>
							<input type="submit" name="submit" value="退出"/>
							

						</td>
					</tr>	
					
				</table>
				
				
			</form>
		</div>
		

	</body>
</html>


