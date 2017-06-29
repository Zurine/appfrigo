var basic = null;

$(document).ready(function() {
	loading();
});




function loading(){
	if($('#crop').val() == 'true')
		cropImage();
	else $('#demo-basic').hide();
}


function updateFileData(){
	 $('#voucher\\.image\\.zoom').val(basic.croppie('get').zoom);
	 $('#voucher\\.image\\.x').val(basic.croppie('get').points[0]);
	 $('#voucher\\.image\\.y').val(basic.croppie('get').points[1]);
	 $('#voucher\\.image\\.w').val(basic.croppie('get').points[2]-basic.croppie('get').points[0]);
	 $('#voucher\\.image\\.h').val(basic.croppie('get').points[3]-basic.croppie('get').points[1]);
}

function validate(){
	if($('#crop').val() == 'true')
	{
		$('.error-row').append('<div class="error-wrapper col-md-6 col-md-offset-3"><div><p>You have to save the image before</p></div></div>');
		$('html, body').animate({ scrollTop: 0 }, 'slow');
	}
	else $('#submitForm').click();
}

function uploadImage(){
	$('#addFile').click()
}

function cropImage(){ 
	$('#demo-basic').show();
	$('#voucherImage').hide();
	basic = $('#demo-basic').croppie({
		viewport: {
			width: 250,
			height: 250
		},
		boundary:  { width: 300, height: 300 }
	});
	
	basic.croppie('bind', {
		url: $('#voucher\\.image\\.path').val(),
		points: [77,469,280,739],
		zoom:1
	});
}

