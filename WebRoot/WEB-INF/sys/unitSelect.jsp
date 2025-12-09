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

			
			<form action="/sys/unitSelectInfo.html" method="post">
				<s:token/>
				<div class="chithead"> 
					<p class="title">往来单位信息管理--查询</p>
				</div>		
			    
	<div class="chithead">	
		<p>
			简称：
			<input name="page.propsNameList" value="shortName" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[0]}" type="text" class="short_input"/>			
			&nbsp;&nbsp;&nbsp;&nbsp;
			全称：
			<input name="page.propsNameList" value="fullName" type="hidden" />
			<input name="page.propsValueList" value="${page.propsValueList[1]}" type="text" class="short_input"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			类型：
			<input name="page.propsNameList" value="type" type="hidden" />			
			<select name="unitType" >
				<option value="所有" <c:if test="${unitType eq '所有'}">selected</c:if>>所有</option>	
				<option value="供应商" <c:if test="${unitType eq '供应商'}">selected</c:if>>供应商</option>	
				<option value="零售客户" <c:if test="${unitType eq '零售客户'}">selected</c:if>>零售客户</option>			
				<option value="直属门店" <c:if test="${unitType eq '直属门店'}">selected</c:if>>直属门店</option>								
				<option value="承包门店" <c:if test="${unitType eq '承包门店'}">selected</c:if>>承包门店</option>
			</select>			
			<input type="submit" name="submit" value="筛选" class="gobtn"/>
		</p>
	</div>
				
				<table class="chitdetail">
					<thead>
						<tr>
							<th width="3%" nowrap>
								选项
							</th>
							<th nowrap>
								行号
							</th>
							<th nowrap>
								往来单位简称
							</th>
							<th nowrap>
								往来单位全称
							</th>
							<th nowrap>
								往来单位类型
							</th>
							<th nowrap>
								负责人姓名
							</th>
							<th nowrap>
								负责人头衔
							</th>
							<th nowrap>
								负责人电话
							</th>
							<th nowrap>
								单位电话
							</th>
							<th nowrap>
								单位传真
							</th>
							<th nowrap>
								单位地址
							</th>
							<th nowrap>
								城市
							</th>
							<th nowrap>
								省份
							</th>
							<th nowrap>
								状态
							</th>
						</tr>
					</thead>


					<tbody>
						<c:forEach var="unit" items="${unitList}" varStatus="num">

							<tr id=wml
								<c:if test="${num.count%2 eq 0 }">bgcolor="#f8f8ff"</c:if>>
								
								<td>
									<input type="radio" name="select" value="${num.index}">
								</td>
								<td nowrap class="sequence">
									${num.count}
								</td>
								<td nowrap>
									${unit.shortName}
								</td>
								<td nowrap>
									${unit.fullName}
								</td>
								
								<td nowrap>
									${unit.type}
								</td>
								
								<td nowrap>
									${unit.directorName}
								</td>
								<td nowrap>
									${unit.directorTitle}
								</td>
								<td nowrap>
									${unit.directorPhone}
								</td>
								<td nowrap>
									${unit.phone}
								</td>
								<td nowrap>
									${unit.fax}
								</td>
								<td nowrap>
									${unit.address}
								</td>
								<td nowrap>
									${unit.city}
								</td>
								<td nowrap>
									${unit.province}
								</td>
								<td nowrap>
									<c:if test="${unit.disable == true}">有效</c:if>
									<c:if test="${unit.disable == false}">无效</c:if>
								</td>
								
								
							</tr>
						</c:forEach>

					</tbody>
				</table>
			 
				
				
				<table class="operate">
					
					<tr >
						<td align="right">							
							<input type="submit" name="submit" value="修改"/>
							<input type="submit" name="submit" value="退出"/>
						
						</td>
					</tr>	
					
				</table>
				
				<page:apply-decorator page="/WEB-INF/public/page.jsp" name="panel" />
			</form>
		</div>
	</script>
		

	</body>
</html>


