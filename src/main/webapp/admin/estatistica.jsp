<%@page import="java.text.DecimalFormat"%>
<%@page import="br.com.fiap.ston.dao.EstatisticaDAO"%>
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
  	EstatisticaDAO estatDao = new EstatisticaDAO();
  %>

  <main class="container-fluid my-2">
    <table class="table table-striped">
      <thead>
        <tr>
          <th scope="col">Intenção</th>
          <th scope="col">Perguntas</th>
          <th scope="col">Porcentagem</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th scope="row">#Aplicação</th>
          <td><%=estatDao.pesquisaPorIntencaoSomaTotal(1)%></td>
          <td><%=new DecimalFormat("0.00").format(100 * estatDao.pesquisaPorIntencaoSomaTotal(1) / (double) estatDao.pesquisaTotalPerguntas())%>%</td>
          </tr>
        <tr>
          <th scope="row">#Atos</th>
          <td><%=estatDao.pesquisaPorIntencaoSomaTotal(2)%></td>
          <td><%=new DecimalFormat("0.00").format(100 * estatDao.pesquisaPorIntencaoSomaTotal(2) / (double) estatDao.pesquisaTotalPerguntas())%>%</td>
          </tr>
        <tr>
          <th scope="row">#Contando</th>
          <td><%=estatDao.pesquisaPorIntencaoSomaTotal(3)%></td>
          <td><%=new DecimalFormat("0.00").format(100 * estatDao.pesquisaPorIntencaoSomaTotal(3) / (double) estatDao.pesquisaTotalPerguntas())%>%</td>
        </tr>
        <tr>
          <th scope="row">#Curso</th>
          <td><%=estatDao.pesquisaPorIntencaoSomaTotal(4)%></td>
          <td><%=new DecimalFormat("0.00").format(100 * estatDao.pesquisaPorIntencaoSomaTotal(4) / (double) estatDao.pesquisaTotalPerguntas())%>%</td>
        </tr>
        <tr>
          <th scope="row">#Erros</th>
          <td><%=estatDao.pesquisaPorIntencaoSomaTotal(5)%></td>
          <td><%=new DecimalFormat("0.00").format(100 * estatDao.pesquisaPorIntencaoSomaTotal(5) / (double) estatDao.pesquisaTotalPerguntas())%>%</td>
        </tr>
        <tr>
          <th scope="row">#Exemplos</th>
          <td><%=estatDao.pesquisaPorIntencaoSomaTotal(6)%></td>
          <td><%=new DecimalFormat("0.00").format(100 * estatDao.pesquisaPorIntencaoSomaTotal(6) / (double) estatDao.pesquisaTotalPerguntas())%>%</td>
        </tr>
        <tr>
          <th scope="row">#Game</th>
          <td><%=estatDao.pesquisaPorIntencaoSomaTotal(7)%></td>
          <td><%=new DecimalFormat("0.00").format(100 * estatDao.pesquisaPorIntencaoSomaTotal(7) / (double) estatDao.pesquisaTotalPerguntas())%>%</td>
        </tr>
        <tr>
          <th scope="row">#JornadaDoHeroi</th>
          <td><%=estatDao.pesquisaPorIntencaoSomaTotal(8)%></td>
          <td><%=new DecimalFormat("0.00").format(100 * estatDao.pesquisaPorIntencaoSomaTotal(8) / (double) estatDao.pesquisaTotalPerguntas())%>%</td>
        </tr>
        <tr>
          <th scope="row">#Personagens</th>
          <td><%=estatDao.pesquisaPorIntencaoSomaTotal(9)%></td>
          <td><%=new DecimalFormat("0.00").format(100 * estatDao.pesquisaPorIntencaoSomaTotal(9) / (double) estatDao.pesquisaTotalPerguntas())%>%</td>
        </tr>
        <tr>
          <th scope="row">#Profissionais</th>
          <td><%=estatDao.pesquisaPorIntencaoSomaTotal(10)%></td>
          <td><%=new DecimalFormat("0.00").format(100 * estatDao.pesquisaPorIntencaoSomaTotal(10) / (double) estatDao.pesquisaTotalPerguntas())%>%</td>
        </tr>
        <tr>
          <th scope="row">#Protagonista</th>
          <td><%=estatDao.pesquisaPorIntencaoSomaTotal(11)%></td>
          <td><%=new DecimalFormat("0.00").format(100 * estatDao.pesquisaPorIntencaoSomaTotal(11) / (double) estatDao.pesquisaTotalPerguntas())%>%</td>
        </tr>
        <tr>
          <th scope="row">#QuebrandoRegra</th>
          <td><%=estatDao.pesquisaPorIntencaoSomaTotal(12)%></td>
          <td><%=new DecimalFormat("0.00").format(100 * estatDao.pesquisaPorIntencaoSomaTotal(12) / (double) estatDao.pesquisaTotalPerguntas())%>%</td>
        </tr>
        <tr>
          <th scope="row">#Storytelling</th>
          <td><%=estatDao.pesquisaPorIntencaoSomaTotal(13)%></td>
          <td><%=new DecimalFormat("0.00").format(100 * estatDao.pesquisaPorIntencaoSomaTotal(13) / (double) estatDao.pesquisaTotalPerguntas())%>%</td>
        </tr>
        <tr>
          <th scope="row">#Vilao</th>
          <td><%=estatDao.pesquisaPorIntencaoSomaTotal(14)%></td>
          <td><%=new DecimalFormat("0.00").format(100 * estatDao.pesquisaPorIntencaoSomaTotal(14) / (double) estatDao.pesquisaTotalPerguntas())%>%</td>
        </tr>
        <tr>
          <th scope="row">Nenhuma intenção(anything_else)</th>
          <td><%=estatDao.pesquisaPorIntencaoSomaTotal(15)%></td>
          <td><%=new DecimalFormat("0.00").format(100 * estatDao.pesquisaPorIntencaoSomaTotal(15) / (double) estatDao.pesquisaTotalPerguntas())%>%</td>
        </tr>
        <tr>
          <th scope="row">Total de Perguntas</th>
          <td><%=estatDao.pesquisaTotalPerguntas()%></td>
          <td>100%</td>
          <%estatDao.fechar();%>
        </tr>
      </tbody>
    </table>
  </main>

  <!-- fim do main -->
  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>

<!--

    #Aplicação

  	#Atos

  	#Contando

  	#Curso

  	#Erros

  	#Exemplos

  	#Game

  	#JornadaDoHeroi

  	#Personagens

  	#Profissionais

  	#Protagonista

  	#QuebrandoRegra

  	#Storytelling

  	#Vilao

    -->
