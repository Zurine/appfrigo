$( document ).ready(function() {
	$('#btnLink').attr('href',getParameterByName('p1')+"&code="+getParameterByName('code')+"&enterprise="+getParameterByName('enterprise'));

	var lang = getParameterByName('lang');
	if(lang == 'es'){
		$('#title').text('Soy Frigo Vendedor');
		$('#text1').text('¿Has perdido tu contraseña?');
		$('#text2').text('Hemos recibido una petición para cambiar la contraseña. Si es así, haz click en el botón inferior, y entonces podrás cambiar la contraseña.');
		$('#text3').text('Si no ha sido así, ignora este email. ');
		$('#btnLink').text('Solicitar nueva contraseña');
		$('#text4').text('¡¡Comparte felicidad!! :)');
	}
	else{
		$('#title').text("I'm Wall's Vendor");
		$('#text1').text('Did you lost your password?');
		$('#text2').text('We have receive a request to change your password. If this is Okay, press the next button and you could change your password.');
		$('#text3').text('If you have not requested a new password, ignore this email.');
		$('#btnLink').text('Change the password');
		$('#text4').text('Share happiness!! :)');
	}
});

		
function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		results = regex.exec(location.search);
	return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

