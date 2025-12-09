/**
 * @author tom
 */
$(function(){
	
	$('#date').click(function(){
		WdatePicker();
	});
    $("#customerName").keyup(function() {
    	//alert("Animation Done.");

        $("#same").val($("#customerName").val());
    });
    $("#customerName").change(function() {
        $("#same").val($("#customerName").val());
    });
    $("#customerIdentification").keyup(function() {
        $("#sode").val($("#customerIdentification").val());
    });
    $("#customerIdentification").change(function() {
        $("#sode").val($("#customerIdentification").val());
    });
    $("#productCustomercustomerCellPhone").keyup(function() {
        $("#sone").val($("#productCustomercustomerCellPhone").val());
    });
    $("#productCustomercustomerCellPhone").change(function() {
        $("#sone").val($("#productCustomercustomerCellPhone").val());
    });
});

//选中“购买保险”单选框
function buyInsurance() {
	document.getElementById("self").disabled = false;
	document.getElementById("self").checked = true;
	document.getElementById("other").disabled = false;

	document.getElementById("selfName").disabled = false;
	document.getElementById("selfIdNo").disabled = false;
	document.getElementById("selfPhone").disabled = false;
}

//选中“放弃”单选框
function giveUpInsurance() {
	document.getElementById("self").disabled = true;
	document.getElementById("self").checked = false;
	document.getElementById("other").disabled = true;
	document.getElementById("other").checked = false;

	document.getElementById("selfName").disabled = true;
	document.getElementById("selfIdNo").disabled = true;
	document.getElementById("selfPhone").disabled = true;
	document.getElementById("otherName").disabled = true;
	document.getElementById("otherIdNo").disabled = true;
	document.getElementById("otherPhone").disabled = true;
	
	document.getElementById("selfName").value = "";
	document.getElementById("selfIdNo").value = "";
	document.getElementById("selfPhone").value = "";
	document.getElementById("otherName").value = "";
	document.getElementById("otherIdNo").value = "";
	document.getElementById("otherPhone").value = "";
}

//改变“本人”复选框
function selectSelf() {
	//选中
	if(document.getElementById("self").checked == true) {
		document.getElementById("selfName").disabled = false;
		document.getElementById("selfIdNo").disabled = false;
		document.getElementById("selfPhone").disabled = false;		
	}
	//放弃
	else {
		document.getElementById("selfName").disabled = true;
		document.getElementById("selfIdNo").disabled = true;
		document.getElementById("selfPhone").disabled = true;
		
		document.getElementById("selfName").value = "";
		document.getElementById("selfIdNo").value = "";
		document.getElementById("selfPhone").value = "";
	}
}

//改变“他人”复选框
function selectOther() {
	//选中
	if(document.getElementById("other").checked == true) {
		document.getElementById("otherName").disabled = false;
		document.getElementById("otherIdNo").disabled = false;
		document.getElementById("otherPhone").disabled = false;
	}
	//放弃
	else {
		document.getElementById("otherName").disabled = true;
		document.getElementById("otherIdNo").disabled = true;
		document.getElementById("otherPhone").disabled = true;
		
		document.getElementById("otherName").value = "";
		document.getElementById("otherIdNo").value = "";
		document.getElementById("otherPhone").value = "";
	}
}

/*
function readProductInfo() {
	document.getElementById("productInfoButton").click();
}
*/