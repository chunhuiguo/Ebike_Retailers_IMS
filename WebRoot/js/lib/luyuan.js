/**
 * @author tom
 */
;(function($) {
	$.fn.wait = function() {
		$(this).block({ 
			message: $('<img src="/js/lib/img/loading.gif" style=""/>'),
			centerY: 0,
			css:{background:'transparent', border:0,top: '150px'},
			overlayCSS: {backgroundColor: '#EEFBF2'} 
		});
	}
	$.fn.unwait = function(){
		$(this).unblock();
	}
	
	$.prompt= function(opts) {
		var defaults ={
			type:'alert',//or confirm -default=alert
			title:'错误',
			msg:'服务器异常或请求超时！',
			width:'300px',
			height:'100px',
			icon:'ricon',
			yn:''
		};
		opts = $.extend({}, defaults, opts || {});
		if( opts.type == 'confirm' ){
			opts.title = '确认';
			opts.height = '130px',
			opts.icon = 'bicon';
			opts.yn = '<div class="lyConfirm"><a href="#" class="yes">确 定</a><a href="#" class="no">取 消</a></div>';
		}
		
		var Dialog =
			'<div id="lyDlg">' +
				'<div class="lyDrag">'+opts.title+'<a href="#" id="lyClose"></a></div>' + 		
				'<div id="lyContent"><p class="lyInfo"><span class="'+opts.icon+'"></span>'+opts.msg +
			'</p></div>'+opts.yn+'</div>';
		var dlg = $(Dialog).css({width:opts.width,height:opts.height});
		
		$.blockUI({ 
			message: dlg,centerY: 0,
			css: {background: 'transparent',border: 0,top:'150px'},
			overlayCSS: {backgroundColor: '#EEFBF2'}
		});
		$('#lyDlg').jqDrag('.lyDrag');
		$('#lyClose').click(function(){
			$.unblockUI(); return false;
		});
		$('.yes').click(function(){
			$.unblockUI(); return true;
		});
		$('.no').click(function(){
			$.unblockUI(); return false;
		});
	}

	$.closedlg = function(){
		$.unblockUI();
	}
   	
	$.dialog = function(opts){
		var defaults ={
			url:'',
			title:'',
			width:'700px',
			height:'500px',
			left:'180px',
			top:'50px'
		};
		opts = $.extend({}, defaults, opts || {});
		
		var Dialog =
			'<div id="lyDlg">' +
				'<div class="lyDrag">'+opts.title+'<a href="#" id="lyClose"></a></div>' + 		
				'<div id="lyContent">' + 
			'</div><div class="lyResize"></div></div>';
		var dlg = $(Dialog).css({ width: opts.width, height: opts.height });
		$.ajax({
			type: "POST",
			global:false,
			url: opts.url,
			timeout: 5000,
			data: 'ajax=main',
			success: function(data){
				dlg.find('#lyContent').html(data); 
			} 
		});

		$.blockUI({
			message: dlg,
			centerY: 0,
			centerX: 0,
			css: {top: opts.top, left:opts.left, background: 'transparent',border: 0},
			overlayCSS: {backgroundColor: '#EEFBF2'}
		});	

		$('#lyDlg').jqResize('.lyResize').jqDrag('.lyDrag');
		$('#lyClose').click(function(){
			$.unblockUI();return false;
		});
		
	}


})(jQuery);