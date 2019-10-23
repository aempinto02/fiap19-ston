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
						<link rel="stylesheet" href="./css/style3.css">
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
														<li class="nav-item" id="chatbot"><a class="nav-link fonte1" href="chatbot.jsp">Chatbot</a></li>
														<li class="nav-item" id="sugestao"><a class="nav-link fonte1" href="sugestao.jsp">Sugestão</a></li>
														<li class="nav-item" id="logout"><a class="nav-link fonte1" href="logout.jsp">Logout</a></li>
														</ul>
													</div>
												</nav>

												<!-- tratamento dos links dos usuÃ¡rios -->
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
																<button type="button" class="close" data-dismiss="modal"
																	aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
															<div class="modal-body">
																<form action="Logar" method="post">
																	<input type="hidden" name="jspname" value="faq.jsp">
																	<div class="form-group">
																		<label for="exampleInputEmail1">Endereço de E-mail</label> <input
																		type="email" class="form-control" id="exampleInputEmail1" name="email"
																		aria-describedby="emailHelp" placeholder="Entre com seu email"
																		required> <small id="emailHelp"
																		class="form-text text-muted">Não vamos compartilhar seu
																		E-mail com ninguém.</small>
																	</div>
																	<div class="form-group">
																		<label for="exampleInputPassword1">Password</label> <input
																		type="password" class="form-control" id="exampleInputPassword1" name="senha"
																		placeholder="Password" required>
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

														<!-- início da main -->
														<main class="container-fluid">
															<!-- Início dos accordion -->
															<div id="accordion">
																<div class="card">
																	<div class="card-header" id="headingOne">
																		<h5 class="mb-0">
																			<button class="btn btn-link collapsed" data-toggle="collapse"
																				data-target="#collapseOne" aria-expanded="false"
																				aria-controls="collapseOne">
																				<h1 class="display-4 fonte2">O que faz um Chatbot?</h1>
																			</button>
																		</h5>
																	</div>

																	<div id="collapseOne" class="collapse" aria-labelledby="headingOne"
																		data-parent="#accordion">
																		<div class="card-body">
																			<p class="lead fonte1 fonte-maior">Resposta: Como diz a junção
																				das palavras chat(conversa em inglês) e bot (de robot que é robô
																				em inglês), é um robô construído para conversar com
																				humanos a respeito de um tema. O nosso se restringe por enquanto
																				ao tema de Storytelling.</p>
																			</div>
																		</div>
																	</div>
																	<div class="card">
																		<div class="card-header" id="headingTwo">
																			<h5 class="mb-0">
																				<button class="btn btn-link collapsed" data-toggle="collapse"
																					data-target="#collapseTwo" aria-expanded="false"
																					aria-controls="collapseTwo">
																					<h1 class="display-4 fonte2">Como se faz um Chatbot?</h1>
																				</button>
																			</h5>
																		</div>
																		<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
																			data-parent="#accordion">
																			<div class="card-body">
																				<p class="lead fonte1 fonte-maior">Resposta: Há diversas
																					plataformas que prestam o serviço de construir o robô que
																					conversa, a nossa solução foi criada com IBM Watson.</p>
																				</div>
																			</div>
																		</div>
																		<div class="card">
																			<div class="card-header" id="headingThree">
																				<h5 class="mb-0">
																					<button class="btn btn-link collapsed" data-toggle="collapse"
																						data-target="#collapseThree" aria-expanded="false"
																						aria-controls="collapseThree">
																						<h1 class="display-4 fonte2">O que é IBM Watson?</h1>
																					</button>
																				</h5>
																			</div>
																			<div id="collapseThree" class="collapse"
																				aria-labelledby="headingThree" data-parent="#accordion">
																				<div class="card-body">
																					<p class="lead fonte1 fonte-maior">
																						Resposta: Watson é a plataforma de serviços cognitivos da IBM
																						para negócios. A cognição consiste no processo que a mente humana
																						utiliza para adquirir conhecimento a partir de informações
																						recebidas. Com o avanço da tecnologia, essa capacidade passa a
																						ser integrada a sistemas que podem aprender em larga escala e
																						ajudar a sociedade em uma série de finalidades, desde o
																						atendimento a clientes até o combate a doenças graves, essa
																						solução também é chamada de inteligência artificial. (Fonte: <a
																						href="https://pt.wikipedia.org/wiki/Watson_(supercomputador)">Wikipedia</a>)
																					</p>
																				</div>
																			</div>
																		</div>
																		<div class="card">
																			<div class="card-header" id="headingFour">
																				<h5 class="mb-0">
																					<button class="btn btn-link collapsed" data-toggle="collapse"
																						data-target="#collapseFour" aria-expanded="false"
																						aria-controls="collapseFour">
																						<h1 class="display-4 fonte2">Por que Storytelling?</h1>
																					</button>
																				</h5>
																			</div>
																			<div id="collapseFour" class="collapse"
																				aria-labelledby="headingFour" data-parent="#accordion">
																				<div class="card-body">
																					<p class="lead fonte1 fonte-maior">Resposta: A iniciativa
																						partiu da percepção de que o ato de contar histórias pode ser um
																						grande diferencial no momento de se criar uma empresa
																						tradicional, uma startup, um negócio, ou mesmo para se
																						compartilhar novas ideias, e por isso o assunto pode ter tantas
																						aplicações quanto a imaginação permitir, além do fato de que com
																						uma boa história é possível atrair mais atenção dos ouvintes e
																						com isso difundir mais facilmente aquilo que se deseja.</p>
																					</div>
																				</div>
																			</div>
																			<div class="card">
																				<div class="card-header" id="headingFive">
																					<h5 class="mb-0">
																						<button class="btn btn-link collapsed" data-toggle="collapse"
																							data-target="#collapseFive" aria-expanded="false"
																							aria-controls="collapseFive">
																							<h1 class="display-4 fonte2">Por que um Chatbot de
																								Storytelling?</h1>
																							</button>
																						</h5>
																					</div>
																					<div id="collapseFive" class="collapse"
																						aria-labelledby="headingFive" data-parent="#accordion">
																						<div class="card-body">
																							<p class="lead fonte1 fonte-maior">Resposta: Com dúvidas sobre
																								o que é Storytelling ou como é construído? Com dúvidas sobre a
																								sua estrutura? Com dúvidas sobre quem são os personagens centrais
																								em um Storytelling padrï¿½o? Automatizamos um assistente para
																								solucionar as dúvidas mais frequentes acerca do tema. Assim um
																								tema com tanto diferencial agora tem um robô para o ajudar em
																								suas dúvidas.</p>
																							</div>
																						</div>
																					</div>
																				</div>
																				<!-- Fim dos accordions -->
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
																					</script>
																				</body>
																			</html>
