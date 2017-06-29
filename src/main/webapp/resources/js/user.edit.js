$(document).ready(function() {
	
	$( "#type" ).change(function() {
		$('#getCombo').click();
		});
	
	$('#businessIds').multiselect({
    	maxHeight: 400,
    });
} );



//$( window ).load(function() {
//
//	$('#type1,#type2').on('ifChanged', function(event){
//		if($("#type1")[0].checked)
//			$('#equipment-select').hide();
//		else $('#equipment-select').show();
//	});
//	updateState();
//	
//});
//
//$(document).ready(function() {
//	  $('#businessIds').multiselect({
//	    	nonSelectedText : 'Select Equipment',
//	    	maxHeight: 400
//	    });
//	} );
//
//function updateState()
//{
//	if($('#businessIds').val()!=null && $('#businessIds').val()!=0)
//	{
//		$("#type2").iCheck('check');
//		$('#equipment-select').show();
//	}
//	else $("#type1").iCheck('check');
//}