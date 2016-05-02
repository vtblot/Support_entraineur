/**
 * 
 */



$( window ).load(function() {
	
	var filter = document.getElementById('filter');
	var box = document.getElementById('box');

	if(filter.offsetHeight) {
	filter.style.display = 'none';
	return false;
	}
	filter.style.display = 'block';
	box.style.marginLeft = '-'+ (box.offsetWidth / 2) +'px';
	box.style.marginTop = (box.offsetHeight / 2) * -1 +'px';
	
});




