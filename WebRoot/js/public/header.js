/**
 * @author tom
 * 菜单不刷新，ajax方式填充<div id="main">
 * 后来修改为ajax的数据被main下.tabpanel_content .html_content包裹
 */
 
$(function(){

	/*************************************************************/
	/****used by tabs****/
	
	var firstpage ='';
  	if( $('#main .container:first-child').length >0 ){
		firstpage = $('#main').html();
		$('.container').remove();
	}
	var tabpanel = new TabPanel({  
        renderTo:'main',   
        height:'570px',  
        border:'none',  
        active : 0,
        items : [{
			id:'_lyuan',
			title: '首页',  
			html: firstpage,
			closable: false
		}]
    }); 


	/****jquery ajax setting****/
	$.ajaxSetup({
		type: "POST",
		global:false,
		cache:false,
		timeout: 5000,
		data: 'ajax=main'
	});
    $('#busy').ajaxStart(function(){
			$(this).show();
		}).ajaxStop(function(){
			$(this).hide();
		}).ajaxError( function(){
			$.prompt({type:'confirm'});//
	});
   	

   	function setTabData(url){
   		var i = 0, b = true;
   		for(;i < tabpanel.getTabsCount();  i++){
   			if( tabpanel.getTitle(i) == $(url).text()){
   				b = false; break;
   			}
   		}
   		if( b ){//tabpanel为空时的判断 b|| i==0
			$.ajax({
				type: "POST",
				global:true,
				timeout: 5000,
				data: 'ajax=main',
				url: $(url).attr('href'),
				success: function(data){ 
					tabpanel.addTab({
						id:getlyId(url),
						title: $(url).text(),  
						html: data,
						closable: true
					}); 
				}
			});
		}else $('#'+getlyId(url)).trigger("click");	
   	}

   	function getlyId(url){
   		return $(url).attr('href').replace(/\//g,'_').replace(/(.html)/,'');
   	}
	function getlyUrl(id){
		return id.replace(/_/g,'/')+'.html';
	}
	
	/*************************************************************/
	/****used by menu****/

	$('ul#menu ul li a').click(function(){
		var sel = $(this).parents('table');
		if( sel.length == 0 )
			$(this).parent().parent().hide();
		else
			sel.hide();
		
		setTabData(this);
		return false;
	});
	
	$('ul#menu li a.top').click(function(){
		var str = $(this).children('span:first').text();
		if(	str == '修改密码' || str == '首页' ){
			setTabData(this);
			return false;
		}
	}).mouseover(function(){
		var sel =  $(this).has('table');
		if( sel.length == 0 )
			$(this).next().show();
		else 
			$(this).find('table:first').show();

		return false;
	});
	
	/*************************************************************/
	/****used by page.jsp****/
	
	
		
});