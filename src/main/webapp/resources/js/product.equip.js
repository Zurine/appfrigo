$( window ).load(function() {
	$('.edit-mode').hide();
});

$(document).ready(function() {
	
	$('#btn-prod').hide();
	 $('#table').DataTable( {
    	 "aaSorting": [],
    	   paging: false,
    } );
} );

function allowEdit(){
	if($('.edit-mode').is(':visible')){
		$('.edit-mode').hide();
		$('.show-mode').show();
		$('#btn-prod').hide();
	}
	else{
		$('.edit-mode').show();
		$('.show-mode').hide();
		$('#btn-prod').show();
	}
}
