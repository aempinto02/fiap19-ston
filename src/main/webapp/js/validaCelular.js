function validaTelCel() {
  let dadosForm = document.querySelector("#form");

  let telefone = dadosForm.exampleInputTel1.value;
  let celular = dadosForm.exampleInputCel1.value;

  if((telefone.length < 8 || telefone.length > 10) && telefone.length != 0) {
    alert("O telefone deve ter no mínimo 8 dígitos e máximo de 10! Por favor corrija!");
    document.getElementById('exampleInputTel1').focus();
    return false;
  }

  if((celular.length < 8 || celular.length > 11) && celular.length != 0) {
    alert("O celular deve ter no mínimo 8 dígitos e máximo de 11! Por favor corrija!");
    document.getElementById('exampleInputCel1').focus();
    return false;
  }
  return true;
}
