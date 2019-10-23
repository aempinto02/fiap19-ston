<%@page import="java.io.PrintWriter"%>
<%@page import="br.com.fiap.ston.dao.EstatisticaDAO"%>
<%@page import="br.com.fiap.ston.dao.PerguntaNaoRespondidaDAO"%>
<%@page import="br.com.fiap.ston.dao.SugestaoDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="br.com.fiap.ston.dao.UsuarioDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="pt-br">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" href="../css/main.css">
  <link rel="stylesheet" href="../css/style.css">
  <link rel="stylesheet" href="../css/styleE.css">
  <title>Chatbot on Storytelling</title>
</head>
<body>

  <%
    if (session.getAttribute("usuario") == null) {
      response.sendRedirect("../index.jsp");
    }
    else if ((char) session.getAttribute("admin") != '1') {
      response.sendRedirect("../index.jsp");
    }
  %>
  
  <script>
  	if(<%=session.getAttribute("sucesso")%> != null) {
		if(<%=session.getAttribute("sucesso")%>) {
			alert("Usuário alterado com sucesso!");
		}
	}
  </script>
  
  <%
  	session.setAttribute("sucesso", false);
  %>

  <!-- navbar -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-info">
    <a class="navbar-brand fonte" href="admin.jsp">Chatbot on Storytelling</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a class="nav-link fonte1" href="admin.jsp">Admin<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link fonte1" href="estatistica.jsp">Estatísticas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link fonte1" href="sugestoes-admin.jsp">Sugestões</a>
        </li>
        <li class="nav-item">
          <a class="nav-link fonte1" href="perguntas.jsp">Perguntas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link fonte1" href="chatbot-admin.jsp">Chatbot</a>
        </li>
        <li class="nav-item"><a class="nav-link fonte1" href="../logout.jsp">Logout</a></li>
      </ul>
    </div>
  </nav>
  <!-- fim do navbar -->

  <!-- início do main -->

  <%
  	UsuarioDAO userDao = new UsuarioDAO();
  	SugestaoDAO sugestDao = new SugestaoDAO();
  	PerguntaNaoRespondidaDAO pergDao = new PerguntaNaoRespondidaDAO();
  	EstatisticaDAO estatDao = new EstatisticaDAO();
  	ResultSet resultado = userDao.pesquisarTodas();
  %>

  <main class="container-fluid my-2">
    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Edição do usuário</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="text-center">
              <h2><span class="font-weight-bold fonte1">Usuário: </span></h2>
            </div>
            <div class="text-center">
              <p class="fonte-maior"><span id="email-modal"></span></p>
            </div>
            <form action="../AlteraAdmin" method="post">
              <input type="hidden" id="email-input-modal" name="email-input-modal" value="">
              <input type="hidden" id="admin-input-modal" name="admin-input-modal" value="">
              <div class="text-center">
                <label for="admin-check" class="fonte1"><p>Admin?</p></label>
                <input type="checkbox" name="admin-check" id="admin-check" onChange="verificarAdmin()">
              </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
            <button type="submit" class="btn btn-primary salvar">Salvar alteração</button>
            </form>
          </div>
      </div>
    </div>
  </div>
  <!-- Fim modal -->
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Admin</th>
          <th scope="col">Nome</th>
          <th scope="col">E-mail</th>
          <th scope="col">Sugestões</th>
          <th scope="col">Perguntas não respondidas</th>
          <th scope="col">Total de Perguntas</th>
          <th scope="col">Editar</th>
        </tr>
      </thead>
      <tbody>

        <%
        	char admin = '0';
        	String email = "";
        	while(resultado.next()) {
         	   admin = resultado.getString("ST_ADMIN").charAt(0);
         	   email = resultado.getString("NM_EMAIL");
        %>

        <tr class="user">

        	<%
        		if (session.getAttribute("opcao") != null) {
        			if (admin == '1' && (session.getAttribute("opcao").toString().equals("A") || session.getAttribute("opcao").toString().equals("T"))) {
        	%>
        	
        	<td>Admin</td>
        	<td><%=resultado.getString("NM_USUARIO")%></td>
          	<td><%=email%></td>
          	<td><%=sugestDao.pesquisaQtdSugestoesUsuario(email)%></td>
          	<td><%=pergDao.pesquisaQtdPerguntasUsuario(email)%></td>
          	<td><%=estatDao.pesquisaTotalPerguntasUsuario(email)%></td>
          	<td><button type="button" class="btn btn-success" data-toggle="modal" data-target="#exampleModal" onClick="ajustaModal('<%=email%>', '<%=admin%>')">Editar usuário</button></td>

        	<%
        		}
        		else if (admin == '0' && (session.getAttribute("opcao").toString().equals("U") || session.getAttribute("opcao").toString().equals("T"))) {
        	%>
        	
        	<td>Usuário</td>
        	<td><%=resultado.getString("NM_USUARIO")%></td>
          	<td><%=email%></td>
          	<td><%=sugestDao.pesquisaQtdSugestoesUsuario(email)%></td>
          	<td><%=pergDao.pesquisaQtdPerguntasUsuario(email)%></td>
          	<td><%=estatDao.pesquisaTotalPerguntasUsuario(email)%></td>
          	<td><button type="button" class="btn btn-success" data-toggle="modal" data-target="#exampleModal" onClick="ajustaModal('<%=email%>', '<%=admin%>')">Editar usuário</button></td>

        	<%
        			}
        		}
        	%>

        </tr>
        <%
        	}
        	userDao.fechar();
        	sugestDao.fechar();
        	pergDao.fechar();
        	estatDao.fechar();
        %>
        </tbody>
    </table>

    <div class="row">
      <div class="col text-center">
      	<form action="ListaAdmin" id="form" method="post">
      		<input type="hidden" name="option" id="option" value="T">
      		<button type="button" name="button" class="btn btn-info center" onClick="admi()">Admins</button>
      		<button type="button" name="button" class="btn btn-info center" onClick="user()">Usuários</button>
      		<button type="button" name="button" class="btn btn-info center" onClick="todos()">Todos</button>
        </form>
      </div>
    </div>
  </main>

  <!-- fim do main -->
  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  <script src="js/mostra-users.js"></script>
  <script src="js/ajusta-modal.js"></script>
</body>
</html>
