function deleteFirst(id){
	$('.msg-row').find('.error-wrapper').remove();
	loadId(id);
	$('.se-pre-con').show();
	
	$.ajax({
	    'url':  path+'product/delete?id='+id,
	    'type': 'GET',
	    'success': function(result){
			 $("#module-message" ).html( result );
			 if($('#module-message  #delete').val()=='true')
			 {
				 $('#confirm-delete').find('.btn-ok').attr('href', $('#deleteLink'+id).attr('href'));
				 $('#confirm-delete').modal('show');
			 }
			 else{
				$('.msg-row').find('.error-wrapper').remove();
				$('.msg-row').append('<div class="error-wrapper col-md-6 col-md-offset-3"><div><p>You cannot delete this item</p></div></div>');
				$('.info-wrapper').remove();
			 }
			 $('.se-pre-con').fadeOut('slow');
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}
