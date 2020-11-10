/**
 * javascript function that changes color of all anchor tags in a page to light green
 */

anchorColor("#32a88d");

function anchorColor(theColor){
	var theAnchors = document.getElementsByTagName("a");
	for(var j = 0; j < theAnchors.length; j++){
		theAnchors[j].style.color = theColor;
	}	
}




