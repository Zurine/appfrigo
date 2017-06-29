var map;
var marker;
//var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
//var labelIndex = 0;


$( document ).ready(function() {
	initMap();
});


function initMap() {
	
	var array = JSON.parse($('#locationData').val());
	
	map = new google.maps.Map(document.getElementById('map'), {
		center: {lat: array[0].latitud, lng: array[0].longitud},
		// center: {lat: 0, lng: -180},
		zoom: 16
		//mapTypeId: google.maps.MapTypeId.TERRAIN
	});
 
	/*var pos = '';
	for(var i=0;i<array.length;i++)
	{
		addMarker(array[i].latitud,array[i].longitud,map,array[i].label);
	}*/
	
	//addMarker(array[0].latitud,array[0].longitud,map,"A");
	//addMarker(array[array.length-1].latitud,array[array.length-1].longitud,map,"B");
	
	 var FlightPath1 = new Array();
	 
	
	
	var flightPlan ="[";
	for(var i=0;i<array.length;i++)
	{
		addMarker(array[i].latitud,array[i].longitud,map,array[i].label);
	//	flightPlan = flightPlan+ " {lat: "+array[i].latitud+", lng: "+array[i].longitud+"},"
		// FlightPath1.push({lat: "+array[i].latitud+", lng: "+array[i].longitud+"});
		FlightPath1[i] = new google.maps.LatLng(array[i].latitud, array[i].longitud);
	}
	//
//	flightPlan = flightPlan + "]";
//	flightPlan = flightPlan.replace(",]","]");
	
//	var obj = JSON.parse(flightPlan);
	
	
	var flightPlanCoordinates = FlightPath1;
	                            var flightPath = new google.maps.Polyline({
	                              path: flightPlanCoordinates,
	                              geodesic: true,
	                              strokeColor: '#FF0000',
	                              strokeOpacity: 1.0,
	                              strokeWeight: 2
	                            });

	                            flightPath.setMap(map);
	
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
