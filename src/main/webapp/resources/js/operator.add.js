var basic = null;

$(document).ready(function() {
	loading();
	 if($('#user\\.email').val()!='')
	    	showOperatorUser();
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

//function updateFileData(){
//	 $('#operator\\.image\\.zoom').val(basic.croppie('get').zoom);
//	 $('#operator\\.image\\.x').val(basic.croppie('get').points[0]);
//	 $('#operator\\.image\\.y').val(basic.croppie('get').points[1]);
//	 $('#operator\\.image\\.w').val(basic.croppie('get').points[2]-basic.croppie('get').points[0]);
//	 $('#operator\\.image\\.h').val(basic.croppie('get').points[3]-basic.croppie('get').points[1]);
//}

function loadImage(){
	
	$('.se-pre-con').show();
	$.ajax({
	    'url':  path+'operator/addFile',
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
//		url: $('#operator\\.image\\.path').val(),
//		points: [0,0,0,0],
//		zoom:0
//	}).then(function(){ 
//		basic.croppie('setZoom', 0)
//	});
//}


function showOperatorUser(){
	if($('#operator-user').is(':visible')){
		$('#operator-user').hide();
		$('#fa-plus-icon').addClass('fa-plus');
		$('#fa-plus-icon').removeClass('fa-minus-square');
	}
	else{
		$('#operator-user').show();
		$('#fa-plus-icon').addClass('fa-minus-square');
		$('#fa-plus-icon').removeClass('fa-plus');
	}
}