function addRow(){
	
	$('.se-pre-con').show();
	$.ajax({
	    'url':  path+'distributor/addItem',
	    'type': 'POST',
	    'data': $('form').serialize(),
	    'success': function(result){
			 $("#module-contact" ).html( result );
			 $('.se-pre-con').fadeOut('slow');

	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});

}

function removeRow(id){

	$('#row').val(id);
	$('.se-pre-con').show();
	$.ajax({
	    'url':  path+'distributor/removeItem',
	    'type': 'POST',
	    'data': $('form').serialize(),
	    'success': function(result){
			 $("#module-contact" ).html( result );
			 $('.se-pre-con').fadeOut('slow');
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}
