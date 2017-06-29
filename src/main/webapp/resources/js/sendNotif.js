
$(document).ready(function() {
    $('.select_form').multiselect({
    	nonSelectedText : 'Select',
    	maxHeight: 200,
    	enableCaseInsensitiveFiltering: true
    });
    
    $('#selectedConditions').multiselect({
    	nonSelectedText : 'Select',
    	maxHeight: 200,
    	enableCaseInsensitiveFiltering: true
    	//	   enableFiltering: true,
    });

 
} );

