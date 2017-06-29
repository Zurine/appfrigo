$(document).ready(function() {
	$('#product-select').multiselect({
		nonSelectedText : 'Select Products',
		enableClickableOptGroups: true,
	    maxHeight: 400,
    });
	$('.select-component').multiselect({
	  nonSelectedText : 'Select Products',
	  includeSelectAllOption: true
	});
	
	$('.multiselect-group-clickable').click(function(){
		 updatePriceCategory(this)
	});
	radioAction();
} );


$( window ).load(function() {
	$('#type1').on('ifChanged', function(event){
		showSimple();
	});	
	$('#type2').on('ifChanged', function(event){
		showCompound();
	});	
});

function radioAction(){
	if($("#type1")[0]!=null){
		if($("#type1")[0].checked)
			showSimple();
		else showCompound();
	}

}

function showSimple(){
	$('.compound-data').hide();
	$('.simple-data').show();
}

function showCompound(){
	$('.compound-data').show();
	$('.simple-data').hide();
}

function setPrice(id,type){
	
	var list = null;
	if(type == 0)
	{
		list = JSON.parse($('#listSimple').val());
		for(var i=0;i<list.length;i++)
		{
			for(var j=0;j<list[i].products.length;j++)
			{
				if(list[i].products[j].productId == id)
				{
					$('#price').val(list[i].products[j].price);
					break;
				}
			}
		}
	}
	else {
		list = JSON.parse($('#listCompound').val());
		for(var i=0;i<list.length;i++)
		{
			if(list[i].productId == id)
			{
				$('#price').val(list[i].price);
				break;
			}
		}
	}
}


function updatePrice(obj){
	setPrice(obj.value,0);
}

function updatePriceCategory(obj){
	var id = $(obj).next().find('input[type=checkbox]').val();
	setPrice(id);
}

function getComponents(obj){

	setPrice(obj.value,1);
	$('.se-pre-con').show();
	$.ajax({
	    'url':  path+'equipment/product/componets',
	    'type': 'POST',
	    'data': $('form').serialize(),
	    'success': function(result){
			 $("#module-compound" ).html( result );
			 $('.se-pre-con').fadeOut('slow');
			  $('.select-component').multiselect({
				  nonSelectedText : 'Select Products',
				  includeSelectAllOption: true
			   });
			  if($('#product-select').val()!=0)
					$('#no-products').show();
			 
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
	
}


function getComponentsType(obj){

	setPrice(obj.value,1);
	$('.se-pre-con').show();
	$.ajax({
	    'url':  path+'equipmentType/componets',
	    'type': 'POST',
	    'data': $('form').serialize(),
	    'success': function(result){
			 $("#module-compound" ).html( result );
			 $('.se-pre-con').fadeOut('slow');
			  $('.select-component').multiselect({
				  nonSelectedText : 'Select Products',
				  includeSelectAllOption: true
			   });
			  if($('#product-select').val()!=0)
					$('#no-products').show();
			 
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
	
}



