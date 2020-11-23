$(document).ready(function( ) {
	
	//$('table.cAdminTable tr:even').addClass('even');	
			
	$('table.cAdminTable tbody tr').mouseover (function() {
		if ($(this).parent().parent().hasClass("noHigh")== false) {
			$(this).addClass('highlight');
		}		
	});
			
	$('table.cAdminTable tbody tr').mouseout (function() {		
		if ($(this).parent().parent().hasClass("noHigh") == false) {
			$(this).removeClass('highlight');	
		}
	});

});

