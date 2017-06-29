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
	 $('#retail\\.image\\.zoom').val(basic.croppie('get').zoom);
	 $('#retail\\.image\\.x').val(basic.croppie('get').points[0]);
	 $('#retail\\.image\\.y').val(basic.croppie('get').points[1]);
	 $('#retail\\.image\\.w').val(basic.croppie('get').points[2]-basic.croppie('get').points[0]);
	 $('#retail\\.image\\.h').val(basic.croppie('get').points[3]-basic.croppie('get').points[1]);
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
	$('#retailImage').hide();
	basic = $('#demo-basic').croppie({
		viewport: {
			width: 320,
			height: 100
		},
		boundary:  { width: 400, height: 220 }
	});
	
	basic.croppie('bind', {
		url: $('#retail\\.image\\.path').val(),
		points: [77,469,280,739],
		zoom:1
	});
}

