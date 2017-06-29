$(document).ready(function() {
	$('#products').multiselect({
		nonSelectedText : 'Select Products',
		enableClickableOptGroups: true,
	    maxHeight: 400,
	    includeSelectAllOption: true
    });
	
//	 $('#date').daterangepicker({
//	        singleDatePicker: true,
//	        calender_style: "picker_1",
//	        format: 'DD/MM/YYYY',
//	    }, function (start, end, label) {
//	        console.log(start.toISOString(), end.toISOString(), label);
//	    });
//	 $('.edit-mode').hide();
	 showAddStock();
});

function showAddStock(){
	$('#stock-load').hide();
	$('.btn-action').removeClass('active');
	if($('#stock-add').is(':visible')){
		$('#stock-add').hide();
	}
	else{
		$('#stock-add').show();
		$('#btn-add').addClass('active');
	}
	$('#edit-stock').hide();
}

function stockLoads(){
	$('#stock-add').hide();
	$('.btn-action').removeClass('active');
	if($('#stock-load').is(':visible')){
		$('#stock-load').hide();
	}
	else{
		$('#stock-load').show();
		$('#btn-check').addClass('active');
	}
	$('#edit-stock').hide();
}

function getStock(){
	$('.se-pre-con').show();
	var url = path+'truck/stock/history?date='+$('#date').val()+'&business='+$('#business').val();
	$.ajax({
	    'url':  url,
	    'type': 'GET',
	    'success': function(result){
			 $("#module-stock" ).html( result );
			 $('.se-pre-con').fadeOut('slow');
			 $('.edit-mode').hide();
			 $('#edit-stock').show();
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}

function allowEdit(){
	if($('.edit-mode').is(':visible')){
		$('.edit-mode').hide();
		$('.show-mode').show();
	}
	else{
		$('.edit-mode').show();
		$('.show-mode').hide();
	}
}
