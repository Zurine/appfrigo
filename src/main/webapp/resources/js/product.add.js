//var basic = null;

$(document).ready(function() {
	loading();
	//if($('#id').val()=='0')
		radioAction();
	//else{
	//	$('#type1, #type2').iCheck('disable');
	//}
});

$( window ).load(function() {
	$('#type1').on('ifChanged', function(event){
		showSimple();
	});	
	$('#type2').on('ifChanged', function(event){
		showCompound();
	});	
});

function radioAction(){
	if($("#type1")[0].checked)
		showSimple();
	else showCompound();
}
//USANDO

function showSimple(){
	$('.compound-data').hide();
}

function showCompound(){
	$('.compound-data').show();
}

function clickRemoveBtn(id){
	$('.div-'+id).remove();
}

function addCategory(){

	$.ajax({
	    'url':  path+'product/addCategory',
	    'type': 'POST',
	    'data': $('form').serialize(),
	    'success': function(result){
			 $("#module-category" ).html( result );
			 $('.se-pre-con').fadeOut('slow');
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}