$(document).ready(function() {
	$('#businessList').multiselect({
		nonSelectedText : 'Select Equipments',
		enableClickableOptGroups: true,
	    maxHeight: 300,
    });
 
} );

function setEquipment(){
	if($('#business-multi').is(':visible')){
		$('#business-multi').addClass('hide-content');
		$('#business-multi').removeClass('show-content');
	}
	else{
		$('#business-multi').addClass('show-content');
		$('#business-multi').removeClass('hide-content');
	}
}
