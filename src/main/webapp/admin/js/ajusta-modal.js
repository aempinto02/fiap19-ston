function ajustaModal(email, admin) {

	let spanEmail = document.querySelector("#email-modal");
	spanEmail.innerHTML = email;
	
	let input_email = document.querySelector("#email-input-modal");
	let input_admin = document.querySelector("#admin-input-modal");
	
	input_email.value = email;
	
	if(admin == '1') {
		document.querySelector("#admin-check").checked = true;
		input_admin.value = '0';
	}
	else {
		document.querySelector("#admin-check").checked = false;
		input_admin.value = '1';
	}
	
	document.querySelector(".salvar").disabled = true;
}

function verificarAdmin() {

	let botaoSalvar = document.querySelector(".salvar");
	let input_admin = document.querySelector("#admin-input-modal");
	
	if(document.querySelector("#admin-check").checked == true && input_admin.value == 0) {
		botaoSalvar.disabled = true;
	}
	else if(document.querySelector("#admin-check").checked == false && input_admin.value == 0) {
		botaoSalvar.disabled = false;
	}
	else if(document.querySelector("#admin-check").checked == true && input_admin.value == 1) {
		botaoSalvar.disabled = false;
	}
	else if(document.querySelector("#admin-check").checked == false && input_admin.value == 1) {
		botaoSalvar.disabled = true;
	}
}