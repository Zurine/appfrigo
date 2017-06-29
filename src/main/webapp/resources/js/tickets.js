$(document).ready(function() {
	
	$('#startDate').daterangepicker({
	    singleDatePicker: true,
	    calender_style: "picker_1",
	    format: 'DD/MM/YYYY',
	}, function (start, end, label) {
	    console.log(start.toISOString(), end.toISOString(), label);
	});

	$('#endDate').daterangepicker({
	    singleDatePicker: true,
	    calender_style: "picker_1",
	    format: 'DD/MM/YYYY',
	}, function (start, end, label) {
	    console.log(start.toISOString(), end.toISOString(), label);
	});
	
    $('#equipment').multiselect({
		maxHeight: 300,
	    enableFiltering: true,
	    includeSelectAllOption: true,
	    nonSelectedText: 'Select Equipment',
    });
} );


function submitData(){
	$('.se-pre-con').show();
	$.ajax({
	    'url':  path+'ticket/list',
	    'type': 'POST',
	    'data': $('form').serialize(),
	    'success': function(result){
			 $("#module-statistics" ).html( result );
			 $('#list-statistics').show();
			 $('.se-pre-con').fadeOut('slow');
				confirmDelete();
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}

function getProducts(id){
	$('.se-pre-con').show();
	$('#id').val(id);
//	$('#equipment').val($('#businessId').val());
	
	if($('#plus'+id).hasClass('fa-plus-square')){
		$('#plus'+id).removeClass('fa-plus-square');
		$('#plus'+id).addClass('fa-minus-square');
		
		$.ajax({
		    'url':  path+'ticket/ticketProducts',
		    'type': 'POST',
		    'data': $('form').serialize(),
		    'success': function(result){
				 $("#module-products"+id ).html( result );
				 $("#module-products"+id ).show();
				 $('.se-pre-con').fadeOut('slow');
				 
				 $('#module-products'+id).find('table')
				// $('#table-sub'+id)
				 .DataTable( {
			    	 aaSorting: [],
			    	 bPaginate: false,
			    	 bFilter : false,
			    	 bInfo : false
			    });
			
		    },
		    'error': function(result){
				 $('.se-pre-con').fadeOut('slow');
		    }
		});
	}
	else{
		$('#plus'+id).removeClass('fa-minus-square');
		$('#plus'+id).addClass('fa-plus-square');
		 $("#module-products"+id ).hide();
		 
		 $('.se-pre-con').fadeOut('slow');
	}
}

function getCSV(){
	$.ajax({
	    'url':  path+'ticket/getTicketCSV',
	    'type': 'GET',
//	    'data': $('#sales').serialize(),
	    'success': function(result){
			 $("#file-module" ).html( result );
			 if($('#url').val()!=''){
		    		window.open($('#url').val(),'_blank');
		    	}
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}
