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
} );


function submitData(){
	$('.se-pre-con').show();
	$.ajax({
	    'url':  path+'tracking/list',
	    'type': 'POST',
	    'data': $('form').serialize(),
	    'success': function(result){
			 $("#module-statistics" ).html( result );
			 $('#list-statistics').show();
			 $('.se-pre-con').fadeOut('slow');

	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}

function getProducts(id){
	$('.se-pre-con').show();
//	$('#id').val($('#data').val());
//	$('#equipment').val($('#businessId').val());
//	$('#startDate').val(start);
//	('#endDate').val(end);
	
//	start = start.format('dd/mm/yyyy hh:mm');
	if($('#plus'+id).hasClass('fa-plus-square')){
		$('#plus'+id).removeClass('fa-plus-square');
		$('#plus'+id).addClass('fa-minus-square');
		
		$.ajax({
		    'url':  path+'tracking/productWorkDay?id='+id,//+'&start='+$('#start'+id).text()+"&end="+$('#end'+id).val()+"&business="+$('#businessId').val(),
		    'type': 'GET',
//		    'data': $('form').serialize(),
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