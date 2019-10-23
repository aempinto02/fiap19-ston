function validaSugestao() {

  let sugestao = document.getElementById('sugestao');

  if(sugestao.length < 4 || sugestao.length > 200) {
    alert("A sugestão deve ter no mínimo 3 e no máximo 200 caracteres! Corrija se deseja enviar!");
    sugestao.focus();
  }

  document.getElementById("form").submit();

}
