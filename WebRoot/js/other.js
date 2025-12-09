
$(document).ready(function() {
	$(".chitdetail tr").mouseover(function() {
		$(this).addClass("Selectline");
	})
	$(".chitdetail tr").mouseout(function() {
		$(this).removeClass("Selectline");
	})
	$(".chitdetail tr").dblclick(function() {
		$(this).find("input").click();
	})
})