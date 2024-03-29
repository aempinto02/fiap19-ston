<%@page import="br.com.fiap.ston.beans.Usuario"%>
<%@page import="br.com.fiap.ston.dao.UsuarioDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="pt-br">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/styleE.css">
<title>Chatbot on Storytelling</title>
</head>
<body>

	<%
		if(request.getAttribute("usuarioIgual") != null) {
	%>
	
	<!-- Email já existente, validação -->
	<script>
		if(<%=request.getAttribute("usuarioIgual")%> == 1) {
			alert("Email já cadastrado no sistema! Por favor escolha outro!");
			document.getElementById('exampleInputName1').focus();
		}
	</script>
	
	<%
			request.setAttribute("usuarioIgual", 0);
		}
	%>

	<!-- navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-info">
		<a class="navbar-brand fonte" href="index.jsp">Chatbot on
			Storytelling</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link fonte1"
					href="index.jsp">Home<span class="sr-only">(current)</span></a></li>
				<li class="nav-item"><a class="nav-link fonte1"
					href="storytelling.jsp">Storytelling</a></li>
				<li class="nav-item"><a class="nav-link fonte1" href="faq.jsp">FAQ</a>
				</li>
				<li class="nav-item dropdown" id="cadLogin"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Cadastro/Login </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="cadastro.jsp">Cadastro</a>
						<a class="dropdown-item" data-toggle="modal" data-target="#login">Login</a>
					</div>
				</li>
				</ul>
		</div>
	</nav>
	<!-- fim do navbar -->

	<!-- modal -->
	<div class="modal" id="login" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Login</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="Logar" method="post">
						<input type="hidden" name="jspname" value="cadastro.jsp">
						<div class="form-group">
							<label for="exampleInputEmail1">Endereço de E-mail</label> <input
								type="email" class="form-control" id="exampleInputEmail1"
								name="email" aria-describedby="emailHelp"
								placeholder="Entre com seu email" required> <small
								id="emailHelp" class="form-text text-muted">Não vamos
								compartilhar seu E-mail com ninguém.</small>
						</div>
						<div class="form-group">
							<label for="inputPassword1">Password</label> <input
								type="password" class="form-control" id="inputPassword1"
								name="senha" placeholder="Password" required>
						</div>
						<div class="form-group form-check">
							<input type="checkbox" class="form-check-input"
								id="exampleCheck1"> <label class="form-check-label"
								for="exampleCheck1">Mantenha-me logado</label>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Fechar</button>
							<button type="submit" class="btn btn-primary">Logar</button>
						</div>
					</form>

				</div>

			</div>
		</div>
	</div>

	<main class="container-fluid">
		<div class="row">
			<div class="col-12 col-sm-12 col-md-12 col-lg-12">
				<form action="Cadastrar" id="form" method="post">
					<div class="form-group">
						<label for="exampleInputName1">Nome</label> <input type="text"
							class="form-control" id="exampleInputName1" name="nome"
							placeholder="Digite seu nome" required>
					</div>
					<div class="form-group">
						<label for="inputUserEmail">Endereço de E-mail</label> <input
							type="email" class="form-control" id="inputUserEmail"
							aria-describedby="emailHelp" placeholder="Digite seu email"
							required> <small id="emailHelp"
							class="form-text text-muted">Nós nunca compartilharemos
							seu E-mail!</small>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">Confirme endereço de
							E-mail</label> <input type="email" class="form-control"
							id="exampleInputEmail2" name="email" aria-describedby="emailHelp"
							placeholder="Digite seu email" required>
					</div>
					<div class="form-group">
						<label for="exampleInputTel1">Telefone</label> <input type="tel"
							class="form-control" id="exampleInputTel1" name="tel"
							placeholder="Digite seu telefone">
					</div>
					<div class="form-group">
						<label for="exampleInputCel1">Celular</label> <input type="tel"
							class="form-control" id="exampleInputCel1" name="cel"
							placeholder="Digite seu celular">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Senha</label> <input
							type="password" class="form-control" id="exampleInputPassword1"
							placeholder="Password" required>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword2">Confirme sua senha</label> <input
							type="password" class="form-control" id="exampleInputPassword2"
							name="senha" placeholder="Password" required>
					</div>
					<button type="submit" class="btn btn-primary" id="btn-cadastro">Cadastrar</button>
				</form>
			</div>
		</div>
	</main>
	
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="js/validaEmails.js"></script>
	<script type="text/javascript" src="js/validaSenhas.js"></script>
	<script type="text/javascript" src="js/validaCelular.js"></script>
	<script type="text/javascript" src="js/validaNome.js"></script>
	<script type="text/javascript" src="js/validacoes.js"></script>
</body>
</html>
