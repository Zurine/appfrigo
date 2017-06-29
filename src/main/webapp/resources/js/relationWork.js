$(function () {
    
    $('#startDate').datetimepicker({
        format: 'DD/MM/YYYY',
     
    });
    $('#endDate').datetimepicker({
        format: 'DD/MM/YYYY' // 
    });
});

$(document).ready(function() {
	  $('#table').DataTable( {
	    	 "aaSorting": []
	    } );
} );

function submitData(){
	
	$.ajax({
	   /* 'url': 'http://localhost:8089/appfrigo/statistic/search',*/
		'url':  path+'statistic/relWork',
	    'type': 'POST',
	    'data': $('#sales').serialize(),
	    'success': function(result){
	    	
			 $( "#module-statistics" ).html( result );
			 $('#list-statistics').show();
	    }
	});
}