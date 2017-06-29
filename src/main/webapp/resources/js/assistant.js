$(document).ready(function() {

	var step =  $('#step').val();
	switch(step) {
    case '1':
    	$('#operator').addClass('step-done');
		$('#operatorRow').css('color','#FF95B0');
		$('#equipment').css('background-color','#FF95B0');
        break;
    case '2':
    	$('#operator').addClass('step-done');
    	$('#equipment').addClass('step-done');
		$('#equipmentRow').css('color','#FF95B0');
		$('#msm').css('background-color','#FF95B0');
        break;
    case '3':
    	$('#operator').addClass('step-done');
    	$('#equipment').addClass('step-done');
    	//$('#msm').addClass('step-done');
		$('#msmRow').css('color','#FF95B0');
		$('#msm').css('background-color','#FF95B0');
        break;
    case '4':
    	$('#operator').addClass('step-done');
    	$('#equipment').addClass('step-done');
    	$('#msm').addClass('step-done');
//    	$('#product').addClass('step-done');
        break;
	}
	
} );


function callOperator(){
    window.location = $('#web-url').val()+'operator/list?state=1';
}

function callEquipment(){
    window.location = path+'equipment/list?state=1';
}

function callMsm(){
    window.location = path+'seller/list?state=1';
}

function callProduct(){
	var id = $('#currentBusiness').val();
    window.location = path+'equipment/product/list?id='+id;
}