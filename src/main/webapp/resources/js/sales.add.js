$(document).ready(function() {
	loadDate();
} );

function loadDate(){
	   $('#date').daterangepicker({
	       singleDatePicker: true,
	       calender_style: "picker_1",
	       format: 'DD/MM/YYYY',
	       maxDate: new Date()
	   }, function (start, end, label) {
	       console.log(start.toISOString(), end.toISOString(), label);
	   });
}

function showCompound(){
  for(var i=0;i<$('.product_row').length;i++)
  {
	   if($($('.product_row').get(i)).find('option:selected').attr('data-compound') == 'true')
	   {
		   $($('.product_row').get(i)).find('#btn-see').show();
	   }
	   else  $($('.product_row').get(i)).find('#btn-see').hide();
  }
}

$('#btn-add').click(function(){
	
	$('.se-pre-con').show();
	$.ajax({
	    'url':  path+'sales/addRow',
	    'type': 'POST',
	    'data': $('form').serialize(),
	    'success': function(result){
			 $("#module-seller" ).html( result );
			 $('.se-pre-con').fadeOut('slow');
			  loadSwitch();
			  loadDate();
			  showCompound();
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
	});



function clickRemoveBtn(id){
//	if(id!=0)
//	$('.div-'+id).remove();
	$('#row').val(id);
	$('.se-pre-con').show();
	$.ajax({
	    'url':  path+'sales/removeItem',
	    'type': 'POST',
	    'data': $('form').serialize(),
	    'success': function(result){
			 $("#module-seller" ).html( result );
			 $('.se-pre-con').fadeOut('slow');
			  loadSwitch();
			  loadDate();
			  showCompound();
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}
//
//function callComposed(id){
//	if($('#select-'+id).find('option:selected').attr('data-compound') == 'true')
//	{
//		$('#productId').val($($('#select-'+id)).find('option:selected').attr('data-generic'));
//		$('#row').val(id);
//		$('#getComposed').click();   
//	}
//}

function getSeller(){
	$('.se-pre-con').show();
	$.ajax({
	    'url':  path+'sales/getProducts',
	    'type': 'POST',
	    'data': $('form').serialize(),
	    'success': function(result){
			 $("#module-seller" ).html( result );
			 $('.se-pre-con').fadeOut('slow');
			  loadSwitch();
			  loadDate();
			  showCompound();
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}

function callComposed(id){
	$('.se-pre-con').show();
	if($('#select-'+id).find('option:selected').attr('data-compound') == 'true')
	{
		$('#productId').val($($('#select-'+id)).find('option:selected').attr('data-generic'));
		$('#row').val(id);
		
		$.ajax({
		    'url':  path+'sales/getComposed',
		    'type': 'POST',
		    'data': $('form').serialize(),
		    'success': function(result){
				 $("#module-seller" ).html( result );
				 $('.se-pre-con').fadeOut('slow');
				  loadSwitch();
				  loadDate();
				  showCompound();
		    },
		    'error': function(result){
				 $('.se-pre-con').fadeOut('slow');
		    }
		});
		
	}
	else  {
		$($('.product_row').get(id)).find('#btn-see').hide();
		$($('.product_row').get(id)).find('.components').hide();
		
		 $('.se-pre-con').fadeOut('slow');
	}
	
	
}

function validate(){
	if($('#date').val() == '')
	{
		$('.error-row').append('<div class="error-wrapper col-md-6 col-md-offset-3"><div><p>Datetime is required</p></div></div>');
		$('html, body').animate({ scrollTop: 0 }, 'slow');
	}
	else{
		$('#submitForm').click();
	}
}
