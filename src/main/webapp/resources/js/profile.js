var basic = null;

$(document).ready(function() {
	loadingCircle();
});
//
//function uploadImage(){
//	$('#addFile').click()
//}

function cropImageCircle(){ 
	$('#profile-basic').show();
	$('.img-reponsive').hide();
	basic = $('#profile-basic').croppie({
		viewport: {
			width: 180,
			height: 180,
			type: 'circle'
		},
		boundary: {
			width: 200,
			height: 200
		}
	});
	basic.croppie('bind', {
		url: $('#image\\.path').val(),
		zoom:1
		//points: [77,469,280,739]
	});
}

function loadingCircle(){
	if($('#crop').val() == 'true')
		cropImageCircle();
	else $('#profile-basic').hide();
}
//
//function updateFileData(){
//	 $('#\\.image\\.zoom').val(basic.croppie('get').zoom);
//	 $('#user\\.image\\.x').val(basic.croppie('get').points[0]);
//	 $('#user\\.image\\.y').val(basic.croppie('get').points[1]);
//	 $('#user\\.image\\.w').val(basic.croppie('get').points[2]-basic.croppie('get').points[0]);
//	 $('#user\\.image\\.h').val(basic.croppie('get').points[3]-basic.croppie('get').points[1]);
//}


function updateEnterprise(){
	$('.se-pre-con').show();
	$.ajax({
	    'url':  path+'settings/updateEnterprise?enterpriseId='+$('#enterprise').val(),
	    'type': 'GET',
//	    'data': $('form').serialize(),
	    'success': function(result){
//			 $("#module-image" ).html( result );
			 $('.se-pre-con').fadeOut('slow');
//			  loadSwitch();
//			  loadDate();
//			  showCompound();
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}