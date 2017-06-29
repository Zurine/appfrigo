var json = '';
$(document).ready(function() {
	 $('#productIds').multiselect({
	    	nonSelectedText : 'Select Products',
	    	enableClickableOptGroups: true,
	        maxHeight: 400,
	 });
	 $('[name="selectedConditions"]').multiselect({  maxHeight: 400,});
	 $('.select-component').multiselect({  maxHeight: 400,});
	
	 
	/* $('.multiselect-container .checkbox').click(function() {
		  var id = $( this ).closest('li').find('[type=checkbox]').val();
		  if(json!='')
		  {
			  var composed = 0;
			  for(var i=0;i<json.length;i++)
			  {
				 for(var j=0;j<json[i].products.length;j++)
				 {
					 if(json[i].products[j].id == id)
					 {
						 if(json[i].products[j].composition!=null)
						 {
							 composed = json[i].id+'@@'+ json[i].products[j].id;
						 }
						 
						 
						 break;
					 }
				 }
			  }
			  if(composed!=0)
			  {
				  $('#selectedProduct').val(composed);
				  $('#getComposed').click();
			  }
		  }
	   });*/
	 
	 json = JSON.parse($('#compound').val());
});

function loadImage(){
	if($('#type').find('option:selected').val() == 2)
	{
		$('.image img').attr('src','https://appfrigo.blob.core.windows.net/products/IamWalls.png')
		$('.form-url').hide();
		$('.form-image').show();
	}
	else 
	{
		$('.form-url').show();
		$('.form-image').hide();
	}
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
		url: $('#promotion\\.image\\.path').val(),
		points: [77,469,280,739],
		zoom:1
	});
}


function uploadImage(){
	$('.se-pre-con').show();
    var formData = new FormData(document.getElementById("promotion"));
	$.ajax({
	    'url':  path+'promotion/addFile',
	    'type': 'POST',
	    'contentType': false,
	    'cache': false,
	    'processData': false,
	    'data': formData,
	    'success': function(result){
			 $("#module-statistics" ).html( result );
			 $('#promotion\\.image\\.path').val($('#imageUrl').val());
			 cropImage();
			 $('.se-pre-con').fadeOut('slow');
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}

function fileDone(){
	
	updateFileData();
	$('.se-pre-con').show();
    var formData = new FormData(document.getElementById("promotion"));
	$.ajax({
	    'url':  path+'promotion/fileDone',
	    'type': 'POST',
	    'contentType': false,
	    'processData': false,
	    'cache': false,
	    'data': formData,
	    'success': function(result){
			 $('.image img').attr('src',result);
			 $('#promotion\\.image\\.path').val(result);
			 $('#url').val(result);
			 
			 $('#demo-basic').hide();
			 $('#productImage').show();
			 
			 $('.se-pre-con').fadeOut('slow');
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}

function updateFileData(){
	 $('#promotion\\.image\\.zoom').val(basic.croppie('get').zoom);
	 $('#promotion\\.image\\.x').val(basic.croppie('get').points[0]);
	 $('#promotion\\.image\\.y').val(basic.croppie('get').points[1]);
	 $('#promotion\\.image\\.w').val(basic.croppie('get').points[2]-basic.croppie('get').points[0]);
	 $('#promotion\\.image\\.h').val(basic.croppie('get').points[3]-basic.croppie('get').points[1]);
}

function validate(){
	if($('#crop').val() == 'true')
	{
		$('.error-row').append('<div class="error-wrapper col-md-6 col-md-offset-3"><div><p>You have to save the image before</p></div></div>');
		$('html, body').animate({ scrollTop: 0 }, 'slow');
	}
	else $('#submitForm').click();
}


function callComposed(id){
	if($('#select-'+id).find('option:selected').attr('data-compound') == 'true')
	{
		$('#productId').val($($('#select-'+id)).find('option:selected').attr('data-generic'));
		$('#row').val(id);
		$('#getComposed').click();   
	}
}
