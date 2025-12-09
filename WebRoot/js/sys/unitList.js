/**
 * @author tom
 */
$(function(){

	$('table.chitdetail tr').hover(function(){
		$(this).css({'background-color':'#ffffd8'});
	},function(){
		$(this).css({'background-color':'#fafafa'});
	}).click(function(){
		$('input[name="voucher.dealingUnitId"]' )
		.val( $('input[name="unit.id"]').val() );
		$('input[name="voucher.dealingUnitShortName"]' )
		.val( $.trim($($(this).find('td').get(2)).text()) );
		$.closedlg();
	});

});