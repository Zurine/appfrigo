$(function () {
    
    $('#startDate').datetimepicker({
        format: 'DD/MM/YYYY HH:mm',
     
    });
    $('#endDate').datetimepicker({
        format: 'DD/MM/YYYY HH:mm' // 
    });
});

$(document).ready(function() {
    $('#table').DataTable( {
        "bSort": false
    } );
} );

function submitData(){
	
	$.ajax({
		/*'url': 'http://localhost:8089/appfrigo/contador/search',*/

		'url':  path+'contador/search',
	    'type': 'POST',
	    'data': $('#contador').serialize(),
	    'success': function(result){
	    	
			 $( "#module-contador" ).html( result );
			 $('#list-contador').show();
	    }
	});
}