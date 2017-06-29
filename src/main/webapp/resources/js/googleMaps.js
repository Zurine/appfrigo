

$( document ).ready(function() {
	initialize();
});

var autocomplete;

/*var placeSearch, autocomplete;
var componentForm = {
    street_number: 'short_name',
    route: 'long_name',
    locality: 'long_name',
    administrative_area_level_1: 'short_name',
    country: 'long_name',
    postal_code: 'short_name'
};*/

function initialize() {
	  var input = document.getElementById('direccion');
      autocomplete = new google.maps.places.Autocomplete(input);

    // When the user selects an address from the dropdown,
    // populate the address fields in the form.
    google.maps.event.addListener(autocomplete, 'place_changed', function () {
        // fillInAddress();
        getLatAndLng();
    });
}


function getLatAndLng() {

    if (autocomplete.getPlace() != null) {
        autocomplete.getPlace().geometry.location.lat()
    }
    $('#latitud').val(autocomplete.getPlace().geometry.location.lat());
    $('#longitud').val(autocomplete.getPlace().geometry.location.lng());
    $('#ciudad').val(autocomplete.getPlace().address_components[2].short_name);
}

// [END region_fillform]

// [START region_geolocation]
// Bias the autocomplete object to the user's geographical location,
// as supplied by the browser's 'navigator.geolocation' object.
function geolocate() {
    /*if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var geolocation = new google.maps.LatLng(
                position.coords.latitude, position.coords.longitude);
            var circle = new google.maps.Circle({
                center: geolocation,
                radius: position.coords.accuracy
            });
            autocomplete.setBounds(circle.getBounds());
        });
    }*/
}
// [END region_geolocation]