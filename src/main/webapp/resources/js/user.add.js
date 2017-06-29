//$( window ).load(function() {
//
//	$('#type1,#type2').on('ifChanged', function(event){
//		if($("#type1")[0].checked)
//			$('#equipment-select').hide();
//		else $('#equipment-select').show();
//	});
//});


$(document).ready(function() {
	
	$( "#type" ).change(function() {
		$('#getCombo').click();
		});
	loadBusinessSelect();

} );

function loadBusinessSelect(){
	$('#businessIds').multiselect({
    	maxHeight: 400,
    });
}


function getBusiness(){
	$('.se-pre-con').show();
	$.ajax({
	    'url':  path+'user/getBusiness',
	    'type': 'POST',
	    'data': $('form').serialize(),
	    'success': function(result){
			 $("#module-business" ).html( result );
			 $('.se-pre-con').fadeOut('slow');
			 loadBusinessSelect();
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}