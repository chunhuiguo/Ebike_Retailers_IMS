<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Strict //EN">
<html>
	<head>
		<meta name="decorator" content="${ajax}" />
		<script type="text/javascript" src="/js/info/serviceCard.js"></script>	
        <style>
        .errors{
			display:block;
			position:absolute;
			}
        </style>
	</head>
	
	<body >
		<div class="container">
		<form action="/info/serviceCard.html" method="post" >
			<s:token/>
			<div class="chithead"> 
				<p class="title">服务卡信息录入</p>				
			</div>		
			<p align="center" > <font color="blue">提示:带<span style="color:#F00">*</span>号的是必填字段。</font></p></br>		
				
			
			<div align="center" style="width:800px;TABLE-LAYOUT:fixed;font-size:12px; margin-left:100px">

            <div style="height:25px;width:800px; ">
                
                <div style="width:60px;height:25px; float:left;">登录单位：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="text" value="${shopShortName}" class="short_input" disabled />
                </div> 
                <div style="width:60px;height:25px; float:left;"></div>               
            </div>

            <div style="height:25px;width:800px;">
            	
                <div style="width:60px;height:25px; float:left; float:left;">整车编码：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="text" name="productCustomer.productBarcode" value="${productCustomer.productBarcode}" class="short_input" /><span style="color:#F00">*</span>
                </div>
                <div style="width:260px;height:25px; float:left;">
            		<input type="submit" id="productInfoButton" name="method:readProductInfo" value="获取整车信息" >
            		<span class="errors">${errors['barcode'][0]}</span>
            	</div>              	
            </div>
            
            <div style="height:25px;width:800px;">
                
                <div style="width:60px;height:25px; float:left;">经销单位：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="text" value="${productCustomer.dealerCode}" class="short_input" disabled />
                </div>
            </div>
            
            <div style="height:25px;width:800px;">
                
                <div style="width:60px;height:25px; float:left;">整车规格：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="text" value="${productCustomer.productSpecName}" class="short_input" disabled />
                </div>
                <div style="width:60px;height:25px; float:left;">整车型号：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="text" value="${productCustomer.productTypeName}" class="short_input" disabled />
                </div>
            </div>
            
            <div style="height:25px;width:800px;">
                
                <div style="width:60px;height:25px; float:left;">整车颜色：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="text" value="${productCustomer.colorName}" class="short_input" disabled />
                </div>
                <div style="width:60px;height:25px; float:left;">控制器号：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="text" value="${productCustomer.controlBarcode}" class="short_input" disabled />
                </div>
            </div>
            
            <div style="height:25px;width:800px;">
                
                <div style="width:60px;height:25px; float:left;">车架编码：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="text" value="${productCustomer.frameBarcode}" class="short_input" disabled />
                </div>
                <div style="width:60px;height:25px; float:left;">轮毂编码：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="text" value="${productCustomer.hubBarcode}" class="short_input" disabled />
                </div>
            </div>
            
            <div style="height:25px;width:800px;">
                
                <div style="width:60px;height:25px; float:left;">上牌型号：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="text" value="${productCustomer.listCategory}" class="short_input" disabled />
                </div>
                <div style="width:60px;height:25px; float:left;">服务卡号：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="text" value="${productCustomer.serviceCardNumber}" class="short_input" disabled />
                </div>
            </div>
            
            <div style="height:25px;width:800px;">
                
                <div style="width:60px;height:25px; float:left;">用户姓名：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="text" name="productCustomer.customerName" id="customerName" value="${productCustomer.customerName}" class="short_input" width="100"  /><span style="color:#F00">*</span>
                    <span class="errors">${errors['customerName'][0]}</span>
                </div>
                <div style="width:60px;height:25px; float:left;">身份证号：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="text" name="productCustomer.customerIdentification" id="customerIdentification" value="${productCustomer.customerIdentification}" class="short_input"  width="100" />
                    <span class="errors">${errors['customerIdentification'][0]}</span>
                </div>
            </div>
            
            <div style="height:25px;width:800px;">
               	
                <div style="width:60px;height:25px; float:left;">用户性别：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="radio" name="productCustomer.customerSex" value="男" <c:if test="${productCustomer.customerSex == '男'}" >checked</c:if> />男&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="productCustomer.customerSex" value="女" <c:if test="${productCustomer.customerSex == '女'}" >checked</c:if> />女
                </div>                
            </div>
            
            <div style="height:25px;width:800px;">
                
                <div style="width:60px;height:25px; float:left;">联系电话：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="text" name="productCustomer.customerPhone" value="${productCustomer.customerPhone}" class="short_input"  />
                </div>
                <div style="width:60px;height:25px; float:left;">手机号码：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="text" name="productCustomer.customerCellPhone" id="productCustomercustomerCellPhone" value="${productCustomer.customerCellPhone}" class="short_input"  />
                    <span class="errors">${errors['cellPhone'][0]}</span>
                </div>
            </div>
            
            <div style="height:25px;width:800px;">
                
                <div style="width:60px;height:25px; float:left;">购车日期：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="text" name="buyDate" value="${buyDate}" class="short_input" readonly id="date" /><span style="color:#F00">*</span>
                    <span class="errors">${errors['buyDate'][0]}</span>
                </div>
                <div style="width:60px;height:25px; float:left;">输卡日期：</div>
                <div align="left"  style="width:300px;height:25px; float:left; float:left;">
                    <input type="text" value="${inputDate}" class="short_input" disabled />
                </div>
            </div>
            
            
            <div style="height:25px;width:800px;">
                
                <div style="width:60px;height:25px; float:left;">联系地址：</div>
                <div align="left"  style="width:470px;height:25px; float:left; float:left;">
                    <input type="text" name="productCustomer.customerAddress" value="${productCustomer.customerAddress}" class="long_input" />(省、地区、县)
                </div>               
            </div>
            </div>         
            
            <div align="center" style="width:800px;TABLE-LAYOUT:fixed; margin-left:50px;">
             
             <div style="height:25px;width:800px;">
                
                <div style="width:60px;height:20px; margin-top:5px; float:left;" colspan="2">保险选择：</div>
                <div align="left"  style="width:200px;height:25px; float:left;">
                    <input type="radio" name="hasInsurance" value="true" onclick="buyInsurance();" <c:if test="${hasInsurance}">checked</c:if> >购买保险&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="hasInsurance" value="false" onclick="giveUpInsurance();" <c:if test="${! hasInsurance}">checked</c:if> >放弃保险<span style="color:#F00">*</span>
                	
                </div>  
                <div style="width:200px;height:25px; float:left;" ><span class="errors">${errors['insuranceDetail'][0]}</span></div>                            
            </div> 
            
            <c:if test="${self == 'self'}" var="self"/>
            <div style="height:35px;width:800px;">
            	<div style="width:50px;height:25px; float:left;">
            		<input style="margin-top:5px;" type="checkbox" id="self" name="self" value="self" onclick="selectSelf();" <c:if test="${! hasInsurance}">disabled</c:if> <c:if test="${hasInsurance && self}">checked</c:if> >本人
            	</div>
                <div style="width:50px;height:25px; float:left; margin-top:10px;margin-left:5px">姓名：</div>
                <div style="width:170px;height:25px; float:left; margin-top:5px;">
                    <input type="text" id="selfName" name="selfInsuranceCard.customerName" id="same" value="${selfInsuranceCard.customerName}" class="short_input" <c:if test="${(! hasInsurance) || (! self)}">disabled</c:if> /><span style="color:#F00">*</span>
                	
                </div>
                <div style="width:60px;height:25px; float:left; margin-top:10px;margin-left:10px">身份证：</div>
                <div style="width:170px;height:25px; float:left; margin-top:5px;">
                    <input type="text" id="selfIdNo" name="selfInsuranceCard.identityCode" id="sode" value="${selfInsuranceCard.identityCode}" class="short_input" <c:if test="${(! hasInsurance) || (! self)}">disabled</c:if> /><span style="color:#F00">*</span>
                	
                </div>
                <div style="width:70px;height:25px; float:left; margin-top:10px;margin-left:10px">手机号码：</div>
                <div style="width:170px;height:25px; float:left; margin-top:5px;">
                    <input type="text" id="selfPhone" name="selfInsuranceCard.cellPhone" id="sone" value="${selfInsuranceCard.cellPhone}" class="short_input" <c:if test="${(! hasInsurance) || (! self)}">disabled</c:if> /><span style="color:#F00">*</span>
                	
                </div>
            </div>
            
            <c:if test="${other == 'other'}" var="other"/>
            <div style="height:35px;width:800px;">
            	<div style="width:50px;height:25px; float:left;">
            		<input style="margin-top:5px;" type="checkbox" id="other" name="other" value="other" onclick="selectOther();" <c:if test="${! hasInsurance}">disabled</c:if> <c:if test="${hasInsurance && other}">checked</c:if> >他人
            	</div>
                <div style="width:50px;height:25px; float:left;margin-top:10px;margin-left:5px">姓名：</div>
                <div style="width:170px;height:25px; float:left;margin-top:5px;">
                    <input type="text" id="otherName" name="otherInsuranceCard.customerName" value="${otherInsuranceCard.customerName}" class="short_input" <c:if test="${(! hasInsurance) || (! other)}">disabled</c:if> /><span style="color:#F00">*</span>
                	
                </div>
                <div style="width:60px;height:25px; float:left;margin-top:10px;margin-left:10px">身份证：</div>
                <div style="width:170px;height:25px; float:left;margin-top:5px;">
                    <input type="text" id="otherIdNo" name="otherInsuranceCard.identityCode" value="${otherInsuranceCard.identityCode}" class="short_input" <c:if test="${(! hasInsurance) || (! other)}">disabled</c:if> /><span style="color:#F00">*</span>
                	
                </div>
                <div style="width:70px;height:25px; float:left;margin-top:10px; margin-left:10px">手机号码：</div>
                <div style="width:170px;height:25px; float:left;margin-top:5px;">
                    <input type="text" id="otherPhone" name="otherInsuranceCard.cellPhone" value="${otherInsuranceCard.cellPhone}" class="short_input" <c:if test="${(! hasInsurance) || (! other)}">disabled</c:if> /><span style="color:#F00">*</span>
                	
                </div>
            </div>
            <div></div>
            </div>
            
            <div align="center" style="width:800px;TABLE-LAYOUT:fixed">
            <div style="height:25px;width:800px;">
            	<div></div>
            	<div align="center">
            		<input type="submit" name="method:save" value="保存" <c:if test="${! enableSaveButton}">disabled</c:if> >
            	
            		　　　　　　　　　　<input type="submit" value="重置" >
            	</div>  
            	<div><span class="errors">${errors['insurance'][0]}</span></div>          
            </div>            
            <c:if test="${errors['saveSuccess'][0] != null}">
            <div style="height:25px;width:800px;">
            	<div align="center" colspan="5">
            	<span class="errors">${errors['saveSuccess'][0]}</span>    
            	</div>   
            </div>
            </c:if>    
        	</div>
	    </form>
		</div>
	</body>
</html>

