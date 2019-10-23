function validaEmails() {
  let dadosForm = document.querySelector("#form");

  let email1 = dadosForm.inputUserEmail.value;
  let email2 = dadosForm.exampleInputEmail2.value;

  if(email1 === null || email1 === '') {
    alert("Primeiro email vazio! Por favor corrija!");
    document.getElementById("inputUserEmail").focus();
    return false;
  }

  if(email2 === null || email2 === '') {
    alert("Segundo email vazio! Por favor corrija!");
    document.getElementById("exampleInputEmail2").focus();
    return false;
  }

  if(email1 != email2) {
    alert("Emails diferentes! Por favor corrija!");
    document.getElementById("inputUserEmail").focus();
    return false;
  }
  return true;
}
