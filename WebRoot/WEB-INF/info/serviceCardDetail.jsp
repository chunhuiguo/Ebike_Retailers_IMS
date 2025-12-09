<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
	<head>
		<meta name="decorator" content="${ajax}pop-up" />
	</head>

	<body>


		<div class="container">
			<form action="/info/serviceCardList.html" method="post" >
				<s:token/>
				<div style="height:45px;"> 
					<p class="title" style="line-height: 45px;text-indent: 25px;font-size: 18px;font-family: "微软雅黑";color: #036;">服务卡信息</p>
				</div>

			<div style="width:600px; margin-left:100px;">
			<div style="height:25px;width:600px;">
                <div  style="width:60px; height:25px; float:left;">整车编码：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="text" value="${productCustomer.productBarcode}" class="short_input" disabled/>
                </div>   
                <div  style="width:60px; height:25px; float:left;"></div><div style="width:200px; height:25px; float:left;"></div>                          	
            </div>	<div style="height:25px;width:600px;">
                <div   style="width:60px; height:25px; float:left;">经销单位：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="text" value="${productCustomer.dealerCode}" class="short_input" disabled />
                </div>
                <div  style="width:60px; height:25px; float:left;"></div><div style="width:200px; height:25px; float:left;"></div>
            </div>	<div style="height:25px;width:600px;">
                <div   style="width:60px; height:25px; float:left;">整车规格：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="text" value="${productCustomer.productSpecName}" class="short_input" disabled />
                </div>
                <div   style="width:60px; height:25px; float:left;">整车型号：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="text" value="${productCustomer.productTypeName}" class="short_input" disabled />
                </div>
            </div>	<div style="height:25px;width:600px;">
                <div   style="width:60px; height:25px; float:left;">整车颜色：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="text" value="${productCustomer.colorName}" class="short_input" disabled />
                </div>
                <div   style="width:60px; height:25px; float:left;">控制器号：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="text" value="${productCustomer.controlBarcode}" class="short_input" disabled />
                </div>
            </div>	<div style="height:25px;width:600px;">
                <div   style="width:60px; height:25px; float:left;">车架编码：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="text" value="${productCustomer.frameBarcode}" class="short_input" disabled />
                </div>
                <div   style="width:60px; height:25px; float:left;">轮毂编码：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="text" value="${productCustomer.hubBarcode}" class="short_input" disabled />
                </div>
            </div>	<div style="height:25px;width:600px;">
                <div   style="width:60px; height:25px; float:left;">上牌型号：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="text" value="${productCustomer.listCategory}" class="short_input" disabled />
                </div>
                <div   style="width:60px; height:25px; float:left;">服务卡号：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="text" value="${productCustomer.serviceCardNumber}" class="short_input" disabled />
                </div>
            </div>	<div style="height:25px;width:600px;">
                <div   style="width:60px; height:25px; float:left;">用户姓名：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="text" value="${productCustomer.customerName}" class="short_input" disabled />
                </div>
                <div   style="width:60px; height:25px; float:left;">身份证号：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="text" value="${productCustomer.customerIdentification}" class="short_input" disabled />
                </div>
            </div>	<div style="height:25px;width:600px;">
                <div   style="width:60px; height:25px; float:left;">用户性别：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="radio" <c:if test="${productCustomer.customerSex == '男'}">checked</c:if> disabled>男&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" <c:if test="${productCustomer.customerSex == '女'}">checked</c:if> disabled>女
                </div>    
                <div  style="width:60px; height:25px; float:left;"></div><div style="width:200px; height:25px; float:left;"></div>            
            </div>	<div style="height:25px;width:600px;">
                <div   style="width:60px; height:25px; float:left;">联系电话：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="text" value="${productCustomer.customerPhone}" class="short_input" disabled />
                </div>
                <div   style="width:60px; height:25px; float:left;">手机号码：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="text" value="${productCustomer.customerCellPhone}" class="short_input" disabled />
                </div>
            </div>	<div style="height:25px;width:600px;">
                <div   style="width:60px; height:25px; float:left;">购车日期：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="text" value="${productCustomer.buyDate}" class="short_input" disabled/>
                </div>
                <div   style="width:60px; height:25px; float:left;">输卡日期：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="text" value="${productCustomer.inputDate}" class="short_input" disabled />
                </div>
            </div>	<div style="height:25px;width:600px;">
                <div   style="width:60px; height:25px; float:left;">联系地址：</div>
                <div   style="width:200px; height:25px; float:left;">
                    <input type="text" value="${productCustomer.customerAddress}" class="long_input" disabled />
                </div>   
                <div style="width:60px; height:25px; float:left;"></div><div style="width:200px; height:25px; float:left;"></div>            
            </div>
            </div> 
            
            <c:if test="${!empty insuranceCardList}">
            <div align="center" style="width:600px; margin-left:70px;">	<div style="height:25px;width:700px;">                
                <div   style="width:60px; height:25px; float:left;" colspan="2">保险信息</div>                                         
            </div>             
            <c:forEach var="insuranceCard" items="${insuranceCardList}">	<div style="height:25px;width:700px;">            	
                <div   style="width:60px; height:25px; float:left;text-align:right;">姓名：</div>
                <div   style="width:80px; height:25px; float:left;">
                    <input type="text" value="${insuranceCard.customerName}" style="width:60px;" disabled />
                </div>
                <div   style="width:60px; height:25px; float:left;text-align:right;">身份证：</div>
                <div   style="width:150px; height:25px; float:left;">
                    <input type="text" value="${insuranceCard.identityCode}" style="width:150px;" disabled />                	
                </div>
                <div   style="width:70px; height:25px; float:left; text-align:right;">手机号码：</div>
                <div   style="width:150px; height:25px; float:left;">
                    <input type="text" value="${insuranceCard.cellPhone}" style="width:100px" disabled />                	
                </div>
            </div>  
            </c:forEach>
            </div>
            </c:if>
            
            <div align="center" style="width:600px;">
            	<div>
            		<div align="center">
            			<input type="submit" name="method:back" value="返回">
            		</div>
            	</div>
            </div>
			</form>
		</div>


	</body>
</html>


