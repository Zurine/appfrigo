//var basic = null;

$(document).ready(function() {
	loading();
});

//function loading(){
//	if($('#crop').val() == 'true'){
//		$('#save-file').show();
//		$('#edit-file').hide();
//		cropImage();
//	}
//	else {
//		$('#demo-basic').hide();
//		$('#edit-file').show();
//		$('#save-file').hide();
//	}
//}
//
//function updateFileData(){
//	 $('#category\\.image\\.zoom').val(basic.croppie('get').zoom);
//	 $('#category\\.image\\.x').val(basic.croppie('get').points[0]);
//	 $('#category\\.image\\.y').val(basic.croppie('get').points[1]);
//	 $('#category\\.image\\.w').val(basic.croppie('get').points[2]-basic.croppie('get').points[0]);
//	 $('#category\\.image\\.h').val(basic.croppie('get').points[3]-basic.croppie('get').points[1]);
//}

function loadImage(){
	
	$('.se-pre-con').show();
	$.ajax({
	    'url':  path+'category/addFile',
	    'type': 'POST',
	    'data': $('form').serialize(),
	    'success': function(result){
			 $("#module-image" ).html( result );
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
//function validate(){
//	if($('#crop').val() == 'true')
//	{
//		$('.error-row').append('<div class="error-wrapper col-md-6 col-md-offset-3"><div><p>You have to save the image before</p></div></div>');
//		$('html, body').animate({ scrollTop: 0 }, 'slow');
//	}
//	else $('#submitForm').click();
//}
//
//function uploadImage(){
//	$('#addFile').click()
////	loadImage();
//}
//
//
//function cropImage(){ 
//	$('#demo-basic').show();
//	$('#productImage').hide();
//	$('#save-file').show();
//	$('#edit-file').hide();
//	basic = $('#demo-basic').croppie({
//		viewport: {
//			width: 150,
//			height: 150
//		},
//		boundary: {
//			width: 180,
//			height: 180
//		}
//	});
//	
//	basic.croppie('bind', {
//		url: $('#category\\.image\\.path').val(),
//		points: [0,0,0,0],
//		zoom:0
//	}).then(function(){ 
//		basic.croppie('setZoom', 0)
//	});
//}
