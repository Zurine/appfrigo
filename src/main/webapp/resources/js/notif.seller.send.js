$(document).ready(function() {
	$('.us-table').hide();
	$('.bu-table').hide();
});


function checkBusiness(obj){
	var state =  $(obj).parent().find('input')[0].checked;
	var size = $(obj).parent().find('input').length;
	if($(obj).hasClass('op-line')){
		for(var i=1;i<size;i++){
			if($(obj).parent().find('input')[i].checked!=state)
				$(obj).parent().find('input')[i].click();
		}
	}
}

function checkSeller(obj){
	var state =  $(obj).parent().find('input')[0].checked;
	var size = $(obj).parent().find('input').length;
	if($(obj).hasClass('bu-line')){
		for(var i=1;i<size;i++){
			if($(obj).parent().find('input')[i].checked!=state)
				$(obj).parent().find('input')[i].click();
		}
	}
}

function openOperator(obj){
	if($(obj).parent().find('.bu-table').is(':visible')){
		$(obj).parent().find('.bu-table').hide();
		$(obj).find('.fa').addClass('fa-plus-square');
		$(obj).find('.fa').removeClass('fa-minus-square');
	}
	else {
		$(obj).parent().find('.bu-table').show();
		$(obj).find('.fa').addClass('fa-minus-square');
		$(obj).find('.fa').removeClass('fa-plus-square');
	}
}


function openEquipment(obj){
	if($(obj).parent().find('.us-table').is(':visible')){
		$(obj).parent().find('.us-table').hide();
		$(obj).find('.fa').addClass('fa-plus-square');
		$(obj).find('.fa').removeClass('fa-minus-square');
	}
	else {
		$(obj).parent().find('.us-table').show();
		$(obj).find('.fa').addClass('fa-minus-square');
		$(obj).find('.fa').removeClass('fa-plus-square');
	}
}

function setSmall(){
	$('.third-table').find('.switchery').css('height','15px');
	$('.third-table').find('.switchery').css('width','26px');
	$('.third-table').find('.switchery small').css('height','15px');
	$('.third-table').find('.switchery small').css('width','15px');
}

function setMedium(){
	$('.second-table').find('.switchery').css('height','18px');
	$('.second-table').find('.switchery').css('width','29px');
	$('.second-table').find('.switchery small').css('height','18px');
	$('.second-table').find('.switchery small').css('width','18px');
}

function setSwitchery(){
	 setMedium();
	 setSmall();
}
