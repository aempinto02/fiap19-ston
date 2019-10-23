let botao = document.querySelector('#btn-cadastro');

botao.addEventListener('click', function(event) {
  event.preventDefault();
  
  let dadosForm = document.querySelector('#form')

  if(!validaNome()) {
    return;
  }

  if(!validaEmails()) {
    return;
  }

  if(!validaTelCel()) {
    return;
  }

  if(!validaSenhas()) {
    return;
  }

  dadosForm.submit();
});
