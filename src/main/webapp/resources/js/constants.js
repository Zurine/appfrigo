//var id = 0;
//var basic = null;
//var path =  '';
//
//$(document).ready(function() {
//	path =  $('#web-url').val();
//	blockSubmit();
//	
//	confirmDelete();
//	loadMenu();
//	 $('#timezone').val(moment().format("Z"));
//	 $('#language').val(navigator.language || navigator.userLanguage);
//});
//
//function confirmDelete(){
//	$('#confirm-delete').on('show.bs.modal', function(e) {
//		if($('#deleteLink'+id).attr('href')!=null)
//			$(this).find('.btn-ok').attr('href', $('#deleteLink'+id).attr('href'));
//		else $(this).find('.btn-ok').attr('href', $('#delete').attr('href'));
//    });
//}
//
//function deleteDefault(){
//	deleteFirst(id);
//}
//
//function blockSubmit(){
//	$('form').submit(function(){
//	    $(this).find('input[type=submit]').prop('disabled', true);
//	});
//}
//
//function loadId(currentId){
//	id = currentId;
//}
//
//function loadMenu(){
//	var parts = window.location.pathname.replace('appfrigo/','');
//	parts = parts.split("/");
//	if(parts.length < 4){
//		$('#li-'+parts[1]).addClass('active');
//		$('#li-'+parts[1]).closest('.treeview').addClass('active');
//	}
//	else{
//		$('#li-'+parts[1]+'-'+parts[2]).addClass('active');
//		$('#li-'+parts[1]+'-'+parts[2]).closest('.treeview').addClass('active');
//	}
//}
//
//
////CROP
//function loading(){
//	if($('#crop').val() == 'true'){
//		$('#save-file').show();
//		cropImage();
//	}
//	else {
//		$('#demo-basic').hide();
//		$('#save-file').hide();
//	}
//}
//
//function validate(){
//	if($('#crop').val() == 'true')
//	{
//		$('.error-row').append('<div class="error-wrapper col-md-6 col-md-offset-3"><div><p>You have to save the image before</p></div></div>');
//		$('html, body').animate({ scrollTop: 0 }, 'slow');
//	}
//	else $('#submit').click();
//}
//
//function uploadImage(){
//	$('#addFile').click()
//}
//
//function updateFileData(){
//	 $('#image\\.zoom').val(basic.croppie('get').zoom);
//	 $('#image\\.x').val(basic.croppie('get').points[0]);
//	 $('#image\\.y').val(basic.croppie('get').points[1]);
//	 $('#image\\.w').val(basic.croppie('get').points[2]-basic.croppie('get').points[0]);
//	 $('#image\\.h').val(basic.croppie('get').points[3]-basic.croppie('get').points[1]);
//}
//
//function cropImage(){ 
//	$('#demo-basic').show();
//	$('#image-basic').hide();
//	basic = $('#demo-basic').croppie({
//		viewport: {
//			width: 150,
//			height: 150
//		},
//		boundary: {
//			width: 180,
//			height: 180
//		},
//	});
//	basic.croppie('bind', {
//		url: $('#image\\.path').val(),
//		points: [0,0,0,0],
//		zoom:0
//	}).then(function(){ 
//		basic.croppie('setZoom', 0)
//	});
//}
