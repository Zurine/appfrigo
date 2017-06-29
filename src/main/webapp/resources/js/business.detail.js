var map;
var marker;
//var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
//var labelIndex = 0;


$( document ).ready(function() {
	initMap();
});


function initMap() {
	
	var lat = parseFloat($('#lat').val());
	var lng = parseFloat($('#lon').val());
	map = new google.maps.Map(document.getElementById('map'), {
		center: {lat:lat, lng:lng},
		zoom: 16
	});

	addMarker(lat,lng,map,"");
}


//Adds a marker to the map.
function addMarker(lat, lng, map,label) {
  // Add the marker at the clicked location, and add the next-available label
  // from the array of alphabetical characters.
  var marker = new google.maps.Marker({
    position: {lat: lat, lng: lng},
    label: label,
    map: map
  });
}
