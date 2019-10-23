<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="pt-br">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" href="./css/style.css">
  <link rel="stylesheet" href="./css/styleE.css">
  <title>Chatbot on Storytelling</title>
</head>
<body>

  <%
  boolean sessao = false;
  if (session.getAttribute("usuario") != null) {
    sessao = true;
  }
  %>

  <!-- navbar -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-info">
    <a class="navbar-brand fonte" href="index.jsp">Chatbot on Storytelling</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a class="nav-link fonte1" href="index.jsp">Home<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link fonte1" href="storytelling.jsp">Storytelling</a>
        </li>
        <li class="nav-item">
          <a class="nav-link fonte1" href="faq.jsp">FAQ</a>
        </li>
        <li class="nav-item dropdown" id="cadLogin">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Cadastro/Login
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="cadastro.jsp">Cadastro</a>
            <a class="dropdown-item" data-toggle="modal" data-target="#login">Login</a>
          </div>
        </li>
            <li class="nav-item" id="chatbot">
              <a class="nav-link fonte1" href="chatbot.jsp">Chatbot</a>
            </li>
            <li class="nav-item" id="sugestao"><a class="nav-link fonte1" href="sugestao.jsp">Sugestão</a></li>
            <li class="nav-item" id="logout"><a class="nav-link fonte1" href="logout.jsp">Logout</a></li>

          </ul>
      </div>
      </nav>

      <!-- tratamento dos links dos usuários -->
      <script>
      if (<%=sessao%> == true) {
        document.getElementById("chatbot").classList.remove("esconde");
        document.getElementById("chatbot").classList.add("mostra");
        document.getElementById("sugestao").classList.remove("esconde");
        document.getElementById("sugestao").classList.add("mostra");
        document.getElementById("cadLogin").classList.add("esconde");
        document.getElementById("cadLogin").classList.remove("mostra");
        document.getElementById("logout").classList.remove("esconde");
        document.getElementById("logout").classList.add("mostra");
      } else if (<%=sessao%> == false) {
        document.getElementById("cadLogin").classList.remove("esconde");
        document.getElementById("cadLogin").classList.add("mostra");
        document.getElementById("chatbot").classList.add("esconde");
        document.getElementById("chatbot").classList.remove("mostra");
        document.getElementById("sugestao").classList.add("esconde");
        document.getElementById("sugestao").classList.remove("mostra");
        document.getElementById("logout").classList.remove("mostra");
        document.getElementById("logout").classList.add("esconde");
      }
      </script>
      <!-- fim do navbar -->

      <!-- modal -->
      <div class="modal" id="login" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Login</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <form action="Logar" method="post">
    						<input type="hidden" name="jspname" value="storytelling.jsp">
                <div class="form-group">
                  <label for="exampleInputEmail1">Endereço de E-mail</label>
                  <input type="email" class="form-control" id="exampleInputEmail1" name="email" aria-describedby="emailHelp" placeholder="Entre com seu email" required>
                  <small id="emailHelp" class="form-text text-muted">Não vamos compartilhar seu E-mail com ninguém.</small>
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Password</label>
                  <input type="password" class="form-control" id="exampleInputPassword1" name="senha" placeholder="Password" required>
                </div>
                <div class="form-group form-check">
                  <input type="checkbox" class="form-check-input" id="exampleCheck1">
                  <label class="form-check-label" for="exampleCheck1">Mantenha-me logado</label>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                  <button type="submit" class="btn btn-primary">Logar</button>
                </div>
              </form>
            </div>

          </div>
        </div>
      </div>

      <!-- inï¿½cio da main -->
      <main class="container-fluid">
        <div class="row" align="center">
          <div class="col-12 col-sm-12 col-md-12 col-lg-12">
            <div class="jumbotron jumbotron-fluid margem">
              <h1 class="display-4 fonte">Storytelling</h1>
              <p class="lead text-justify fonte1">Storytelling vem do inglês, story = história, telling = contar, ou seja: a arte de contar histórias; exatamente como nossos pais faziam conosco quando éramos crianças ou como autores de livros e filmes fazem até hoje.

                As histórias são sempre a reprodução de uma sequência de fatos, verdadeiros ou não, que têm personagens, conflitos e cenários. As histórias mais empolgantes rendem milhões em bilheterias no cinema e até saem de lá, passam a fazer parte do dia a dia das pessoas, como Star Wars, por exemplo.

                Os personagens centrais das tramas são pessoas, animais ou objetos com os quais nos identificamos fortemente. Sentimo-nos atraídos a ouvir sua história, acompanhar cada capítulo e viver a emoção de ver o começo, meio e fim do enredo. Todos nós já nos sentimos assim instigados a acompanhar uma boa história. O que impediria então as marcas de se colocarem como personagens centrais de uma história, fazendo com que a gente também queira acompanhá-la? Nada, absolutamente nada. Parece simples, mas não é, pois não é tão fácil ser um contador de histórias.</p>
              </div>
            </div>
          </div>
          <div class="row margem1" align="center">
            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
              <div class="card" style="width: 18rem;">
                <img src="./imagens/storytelling2.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                  <h5 class="card-title">Vídeo</h5>
                  <p class="card-text">Veja um vídeo sobre Storytelling!</p>
                  <a class="btn btn-primary" data-toggle="modal" data-target="#video1">VÍDEO</a>
                </div>
              </div>
            </div>
          </div>
        </main>

        <!-- fim da main -->

        <!-- modal -->

        <div class="modal" id="video1" tabindex="-1" role="dialog">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Storytelling: A arte de contar histórias memoráveis</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <iframe src="https://www.youtube.com/embed/360y-AwaLK4" width="450" height="315"></iframe>
              </div>
            </div>
          </div>
        </div>
        <!-- fim do modal -->

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
      </body>
      </html>
