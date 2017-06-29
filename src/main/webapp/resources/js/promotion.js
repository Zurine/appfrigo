var hourCont = 0;

$(document).ready(function() {
	initializeDate();
});

function validate(){
	var timeZone = new Array();
	var error = false;
	for (var i = 0; i <= hourCont; i++) 
	{
		if(validateTime($('#hora-inicio_' + i).val()) && validateTime($('#hora-fin_' + i).val()))
		{
			timeZone.push($('#hora-inicio_' + i).val() + '-' + $('#hora-fin_' + i).val());
		}
		else
		{
			error = true;
			break;
		}
	}
	
	if(error)
	{
		$('.error-wrapper').remove();
		$('.error-row').append('<div class="error-wrapper col-md-6 col-md-offset-3"><div><p>Invalid time format.</p></div></div>');
		$('html, body').animate({ scrollTop: 0 }, 'slow');
	}
	else
	{
	    $('#timeZone').val(JSON.stringify(timeZone));
		$('#submitForm').click();
	}

}

/*function loadTime() {
    var timeZone = new Array();
    for (var i = 0; i <= hourCont; i++) {
        timeZone.push($('#hora-inicio_' + i).val() + '-' + $('#hora-fin_' + i).val());
            
    }
    $('#timeZone').val(JSON.stringify(timeZone));
}*/

function initializeDate() {
	
    $('#fechaIni').daterangepicker({
        singleDatePicker: true,
        calender_style: "picker_1",
        format: 'DD/MM/YYYY HH:mm',
    }, function (start, end, label) {
        console.log(start.toISOString(), end.toISOString(), label);
    });
    
    $('#fechaFin').daterangepicker({
        singleDatePicker: true,
        calender_style: "picker_1",
        format: 'DD/MM/YYYY HH:mm',
    }, function (start, end, label) {
        console.log(start.toISOString(), end.toISOString(), label);
    });

}

function validateTime(value) //:\d{2}
{
   if (!/^\d{2}:\d{2}$/.test(value)) return false;
    var parts = value.split(':');
    if (parts[0] > 23 || parts[1] > 59 || parts[2] > 59) return false;
    return true;
}
function getCode(){
	
	$.ajax({

	    'url': path+'equipment/promotion/codes',
	    'type': 'POST',
	    'data': $('#promotion').serialize(),
	    'success': function(result){
	    	
			 $( "#module_code" ).html( result );
			 /*if($( "#code" ).val()==true )
			 {
				 $('#code_field').show();
			 }*/
	    }
	});
}

function loadTime() {
    var timeZone = new Array();
    for (var i = 0; i <= hourCont; i++) {
        timeZone.push($('#hora-inicio_' + i).val() + '-' + $('#hora-fin_' + i).val());
            
    }
    $('#timeZone').val(JSON.stringify(timeZone));
}

function addMore() {
    hourCont++;
    var data = '<div class="row"> ' +
                    '<div class="form-group col-md-4">' +
                        '<input type="text" class="form-control has-feedback-left" name="hora-inicio_' + hourCont + '" id="hora-inicio_' + hourCont + '">' +
                        '<span class="fa fa-clock-o form-control-feedback left" aria-hidden="true"></span>' +
                     '</div>' +
                     '<div class="form-group col-md-4">' +
                     '<input type="text" class="form-control has-feedback-left" name="hora-fin_' + hourCont + '" id="hora-fin_' + hourCont + '">' +
                     '<span class="fa fa-clock-o form-control-feedback left" aria-hidden="true"></span>' +
                  '</div>' +
                    '</div>';

    $(data).appendTo('#timeGroup');
    
}
