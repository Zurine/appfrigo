var basic = null;

$(document).ready(function() {

	loading();
	if($("#parent\\.id").val() == 0 && $('#compound0\\.id').length > 0)
	{
		$("#radio-type").show();
		radioAction();
	}
	
	if($("#parent\\.id").val() != 0 )
	{
		$('#type2').iCheck('disable');
		$('#type1').iCheck('disable');
	}
});

$( window ).load(function() {
	
	$('#type1,#type2').on('ifChanged', function(event){
		radioAction();
	});	
});

function radioAction(){
	if($("#type1")[0].checked)
	{
		$('#product-panel').hide();
	}
	else
	{
		setCompound();
	}
}

function setCompound(){
	$('#product-panel').show();
	$("#type2").checked = true;
}

function clickBtn(){
	$('#addRow').click();
}

function clickChild(){
	if($('#parent\\.id').val()!=0)
	{
		$('#addChild').click();
	}
	else
	{
		$('#type2').iCheck('disable');
		$('#type1').iCheck('disable');
	}
}


function uploadImage(){
	$('#addFile').click()
}


function cropImage(){ 
	$('#demo-basic').show();
	$('#productImage').hide();
	basic = $('#demo-basic').croppie({
		viewport: {
			width: 250,
			height: 250
		}
	});
	
	basic.croppie('bind', {
		url: $('#product\\.image\\.path').val(),
		points: [77,469,280,739],
		zoom:1
	});
}

function loading(){
	if($('#crop').val() == 'true')
		cropImage();
	else $('#demo-basic').hide();
}


function updateFileData(){
	 $('#product\\.image\\.zoom').val(basic.croppie('get').zoom);
	 $('#product\\.image\\.x').val(basic.croppie('get').points[0]);
	 $('#product\\.image\\.y').val(basic.croppie('get').points[1]);
	 $('#product\\.image\\.w').val(basic.croppie('get').points[2]-basic.croppie('get').points[0]);
	 $('#product\\.image\\.h').val(basic.croppie('get').points[3]-basic.croppie('get').points[1]);
}

function validate(){
	if($('#crop').val() == 'true')
	{
		$('.error-row').append('<div class="error-wrapper col-md-6 col-md-offset-3"><div><p>You have to save the image before</p></div></div>');
		$('html, body').animate({ scrollTop: 0 }, 'slow');
	}
	else $('#submitForm').click();
}