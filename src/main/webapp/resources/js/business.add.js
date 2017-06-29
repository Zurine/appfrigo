
function getDistributor(){
	$('.se-pre-con').show();
	$.ajax({
	    'url':  path+'equipment/getDistributor',
	    'type': 'POST',
	    'data': $('form').serialize(),
	    'success': function(result){
			 $("#module-distributor" ).html( result );
			 $('.se-pre-con').fadeOut('slow');
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}