function validaNome() {
  let dadosForm = document.querySelector("#form");

  let nome = dadosForm.exampleInputName1.value;

  if(nome === null || nome === '') {
    alert("Nome vazio! Por favor corrija!");
    document.getElementById("exampleInputName1").focus();
    return false;
  }
  return true;
}
