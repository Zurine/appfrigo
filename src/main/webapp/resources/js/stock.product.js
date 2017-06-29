
$(document).ready(function() {
	$('.edit-mode').hide();
} );

function getEquipments(id){
	$('.se-pre-con').show();
	
	if($('#plus'+id).hasClass('fa-plus-square')){
		$('#plus'+id).removeClass('fa-plus-square');
		$('#plus'+id).addClass('fa-minus-square');
		
		$.ajax({
		    'url':  path+'operator/stock/equipmentStock?operatorId='+$('#operator').val()+'&productId='+id,
		    'type': 'GET',
		    'success': function(result){
				 $("#module-equipment"+id ).html( result );
				 $("#module-equipment"+id ).show();
				 $('.se-pre-con').fadeOut('slow');
				 
				 $('#module-equipment'+id).find('table')
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
		 $("#module-equipment"+id ).hide();
		 
		 $('.se-pre-con').fadeOut('slow');
	}
}

function getProducts(){
	$('.se-pre-con').show();

		
	$.ajax({
	    'url':  path+'operator/stock/listProducts?operatorId='+$('#operator').val(),
	    'type': 'GET',
	    'success': function(result){
			$("#module-products").html( result );
			noPaginatedTable();
			$('.edit-mode').hide();
			$('.se-pre-con').fadeOut('slow');
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
	

}


function editRow(obj,id){

	var row = $(obj).parent().parent();
	row.find('.edit-mode').show();
	row.find('.show-mode').hide();
	
	row.find('.edit-stock').val(row.find('.stock').text());
	row.find('.edit-min-stock').val(row.find('.min-stock').text());
}


function saveRow(obj,id){
	
	var row = $(obj).parent().parent();
	
	$('#stock').val(row.find('.edit-stock').val());
	$('#min-stock').val(row.find('.edit-min-stock').val());
	$('#prodId').val(id);
	$('#operatorId').val($('#operator').val());
	
	$('#submit').click();
//	var row = $(obj).parent();
//	row.find('.edit-mode').hide();
//	row.find('.show-mode').show();
}

function getContacts(){
	
	
	$.ajax({
	    'url':  path+'operator/stock/distributors?operatorId='+$('#operator').val(),
	    'type': 'GET',
	    'success': function(result){
			 $("#module-distributor").html( result );
			 
			 $('#distributor-mdl').modal('show');
			 

	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}