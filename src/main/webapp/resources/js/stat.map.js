var table = null;
var type = null;
var array = null;
var markers = [];


$(document).ready(function() {
	
   $('#startDate').daterangepicker({
        singleDatePicker: true,
        calender_style: "picker_1",
        format: 'DD/MM/YYYY',
    }, function (start, end, label) {
        console.log(start.toISOString(), end.toISOString(), label);
    });
    
    $('#endDate').daterangepicker({
        singleDatePicker: true,
        calender_style: "picker_1",
        format: 'DD/MM/YYYY',
    }, function (start, end, label) {
        console.log(start.toISOString(), end.toISOString(), label);
    });
	
    $('#equipment').multiselect({
		maxHeight: 300,
	    enableFiltering: true,
	    includeSelectAllOption: true,
	    nonSelectedText: 'Select Equipment',
    });
    $('#product').multiselect({
		maxHeight: 300,
	    enableFiltering: true,
	    includeSelectAllOption: true,
	    nonSelectedText: 'Select Product',
    });
    if(type == null){
    	$('#info-type1').iCheck('check');
    	type = 0;
    }
    
    $('#info-type1').on('ifChanged', function(event){
    	type = 0;
    	changeData()
	});	
	$('#info-type2').on('ifChanged', function(event){
		type = 1;
		changeData()
	});	
	initMap();
} );

function submitData(){
	$('.se-pre-con').show();
	$.ajax({
	    'url':  path+'statistic/salesMap',
	    'type': 'POST',
	    'data': $('form').serialize(),
	    'success': function(result){
			 $("#module-statistics" ).html( result );
			 initMap();
			 $('.se-pre-con').fadeOut('slow');
	    },
	    'error': function(result){
			 $('.se-pre-con').fadeOut('slow');
	    }
	});
}

function initMap() {
	
	if($('#salesMap').val()!=null && $('#salesMap').val()!=''){
		array = JSON.parse($('#salesMap').val());
		if(array!=null && array.length > 0){
			map = new google.maps.Map(document.getElementById('map'), {
				center: {lat: array[array.length-1].lat, lng: array[array.length-1].lon},
				zoom: 8
			});
		}
		else{
			map = new google.maps.Map(document.getElementById('map'), {
				center: {lat: 0, lng: 0},
				zoom: 2
			});
		}
		setMarkers();
	}

}

function setMarkers(){
	var label = '';
	for(var i=0;i<array.length;i++)
	{
		if(type==0)
			label = array[i].total+'';
		else label = array[i].amount+'';
		if(label.length > 3)
			label = label.substring(0,1)+'K';
		addMarker(array[i].lat,array[i].lon,map,label);
	}
}

//Adds a marker to the map.
function addMarker(lat, lng, map,label) {
  // Add the marker at the clicked location, and add the next-available label
  // from the array of alphabetical characters.
  var marker = new google.maps.Marker({
    position: {lat: lat, lng: lng},
    label:  {
        text: label,
        fontSize    : '10px',
    },
    map: map
  });
  markers.push(marker);
}

function changeData(){
	if(type!=null){
		setMapOnAll(null);
	    markers = [];
	    setMarkers();
	}
	
}

// Sets the map on all markers in the array.
function setMapOnAll(map) {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(map);
  }
}