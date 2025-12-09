/**
 * @author tom
 */
$(function(){
	
	$('#date').click(function(){
		WdatePicker();
	});
	
	/*
	$('table.chitbody input[value="选择供货单位"]').click(function(){
		var type=document.getElementById("type").value;
		if(type == "整车")
			$.dialog({url:'/sys/unitList!list.html?result=purchase',title:"供货单位"});
		else
			$.dialog({url:'/sys/unitList!list.html?result=ppurchase',title:"供货单位"});
		return false;
	});
	*/
	/*
	$('table.chitbody input[value="选择经手人"]').click(function(){
		openshow('/sys/purchaseUnitList.html','供货单位',700,500,1);
		return false;
	});
	
	var validator = $('#loginForm').validate({
		focusCleanup:true,
		rules:{
			'voucher.checkDate':{
				required:true
			},
			'voucher.dealingUnitShortName':{
				required:true
			},
			'':{
				required:true
			}
		},
		messages:{
			userName:{
				required:"请输入用户名"
			},
			password:{
				required:"请输入密码"
			},
			verifyKey:{
				required:"请输入验证码"
			}
		},
		errorPlacement: function(error, element) {
			error.prependTo(element.siblings('.errors'));
		}
	});
	
    $('input[type="submit"]').click(function() { 
    	 //$.blockUI({ message: $('#loginForm') }); 

    	return false;
    }); 
	
*/

	
});