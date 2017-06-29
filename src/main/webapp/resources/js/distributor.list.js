function getContacts(id){
	
	if($('#plus'+id).hasClass('fa-address-book')){
		$('#plus'+id).removeClass('fa-address-book');
		$('#plus'+id).addClass('fa-address-book-o');
		$("#module-contact"+id ).show();
	}
	else{
		$('#plus'+id).removeClass('fa-address-book-o');
		$('#plus'+id).addClass('fa-address-book');
		 $("#module-contact"+id ).hide();
	}
}

