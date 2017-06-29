$(function () {
    
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
});

$(document).ready(function() {
    $('#equipment').multiselect({
    	nonSelectedText : 'Select Equipment'
    });
    
    $('#region').multiselect({
    	nonSelectedText : 'Select Region'
    });
    
    $('#channel').multiselect({
    	nonSelectedText : 'Select Channel'
    });
    
    $('#equipmentType').multiselect({
    	nonSelectedText : 'Select Equipment Type'
    });
    
    $('#product').multiselect({
    	nonSelectedText : 'Select Product'
    });
} );

function submitData(){
	$('.se-pre-con').show()
	$.ajax({
		'url':  path+'/statistic/ranking',
	    'type': 'POST',
	    'data': $('#ranking').serialize(),
	    'success': function(result){
			 $("#module-statistics").html(result);
			 $('.se-pre-con').fadeOut('slow');
			 $('#table').DataTable( {
		    	 "aaSorting": []
			 } );
			 if($('#table tr').get(1).innerText == 'No data available in table')
			 	$('#fileGenerator').hide();
			 else $('#fileGenerator').show();
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}