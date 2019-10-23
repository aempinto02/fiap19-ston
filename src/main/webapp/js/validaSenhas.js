function validaSenhas() {
  let dadosForm = document.querySelector('#form');

  let senha1 = dadosForm.exampleInputPassword1.value;
  let senha2 = dadosForm.exampleInputPassword2.value;

  if(senha1 != senha2) {
    alert("Senhas diferentes! Por favor corrija!");
    document.getElementById("exampleInputPassword1").focus();
    return false;
  }

  if(senha1.length <= 7) {
    alert("Senha deve ter no mínimo 8 caracteres! Por favor corrija!");
    document.getElementById("exampleInputPassword1").focus();
    return false;
  }
  return true;
}
