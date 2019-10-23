<%@page import="java.sql.ResultSet"%>
<%@page import="br.com.fiap.ston.dao.SugestaoDAO"%>
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
  <link rel="stylesheet" href="../css/style.css">
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
  	if(<%=session.getAttribute("sucesso-sugest")%> != null) {
		if(<%=session.getAttribute("sucesso-sugest")%>) {
			alert("Sugestão apagada com sucesso!");
		}
	}
  </script>
  
  <%
  	session.setAttribute("sucesso-sugest", false);
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
  <main class="container-fluid my-2">
  	<h1 class="fonte text-center">Sugestões dos Usuários</h1>
  	<ul>
  	<%
  		SugestaoDAO sugestDao = new SugestaoDAO();
  		ResultSet resultado = sugestDao.pesquisarTodas();
  		while(resultado.next()) {
  	%>
  	
  	<div class="sugest my-3 row">
  		<div class="col-10">
  			<p><%=resultado.getInt("NR_SUGESTAO")%> <%=resultado.getString("USUARIO_NM_EMAIL")%> <%=resultado.getDate("DT_SUGESTAO")%></p>
  			<h2><%=resultado.getString("DS_SUGESTAO")%></h2>
        </div>
        <div class="col-2 my-auto">
        	<form action="../ApagaSugestao" method="post">
        		<input type="hidden" name="numero-sugestao" value="<%=resultado.getInt("NR_SUGESTAO")%>">
        		<button type="submit" class="btn btn-success" name="button">Apagar sugestão</button>
        	</form>
        </div>
    </div>
    
  	<%
  		}
  		sugestDao.fechar();
  	%>
  	</ul>
  </main>

  <!-- fim do main -->
  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
