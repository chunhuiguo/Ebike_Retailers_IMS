/**
 * @author tom
 */
$(function(){
	
	$('#jsnote').hide();
	
	$('#box input').focus(function(){
		$(this).css({'background-color':'#fcfcfc','border-color':'#abc'});
	}).blur(function(){
		$(this).css({'background-color':'#fafafa','border-color':'#eee'});
	}).change(function(){
		var input = $(this).prev('.errors');
		var text = input.text();
		if(text) input.text('');
	}).first().focus();
	
	$('#kaptchaImage').click(function () { 
		$(this).hide()
		.attr('src', '/kaptcha.jpg?' + Math.floor(Math.random()*100) ).fadeIn(); 
	});
	
	var validator = $('#loginForm').validate({
		focusCleanup:true,
		rules:{
			userCode:{
				required:true
			},
			password:{
				required:true
			},
			verifyKey:{
				required:true
			}
		},
		messages:{
			userCode:{
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
			error.appendTo(element.prev('.errors'));
		}
	});
	
	$('a#submit').click(function(){	
		var name = $.trim( $('input[name="userCode"]').val() );
		var password = $.trim( $('input[name="password"]').val() );
		var loginTime = $('input[name="loginTime"]').val();
		var encrypt = $.sha1( $.md5(name+'{'+password) + loginTime );

		if(validator.form()){
			$('input[name="encrypt"]').val(encrypt);
			$("#loginForm").submit();
			$.blockUI({ 
				message: $('<img src="/js/lib/img/loading.gif"/>'),
				css:{background:'transparent', border:0},
				overlayCSS: { backgroundColor: '#EEFBF2'} 
			});
		}
		return false;
	});
	
	$(document).keydown(function(event) { 
		if (event.keyCode == 13) { 
				$('a#submit').click(); 
		} 
	});
	
});
