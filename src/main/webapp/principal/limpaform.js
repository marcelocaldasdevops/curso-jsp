function limpaForm(){
	var elemento = document.getElementById("formUser").elements;
	
	for (p = 0; p < elemento.length; p++){
		elemento[p].value = '';
	}	
}