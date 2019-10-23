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
            <link rel="stylesheet" href="./css/main.css">
            <link rel="stylesheet" href="./css/style3.css">
            <link rel="stylesheet" href="./css/style4.css">
              <title>Chatbot on Storytelling</title>
            </head>
            <body>
            	<%
            		if (session.getAttribute("usuario") == null) {
            			response.sendRedirect("index.jsp");
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
                    <li class="nav-item">
                        <a class="nav-link fonte1" href="chatbot.jsp">Chatbot</a>
                      </li>
                      <li class="nav-item"><a class="nav-link fonte1" href="sugestao.jsp">Sugestão</a></li>
                      <li class="nav-item"><a class="nav-link fonte1" href="logout.jsp">Logout</a></li>
                    </ul>
                  </div>
                </nav>
                <!-- fim do navbar -->

                  <!-- inÃ­cio da main -->
                      <main class="container-fluid">
                        <div class="row" align="center">
                          <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                            <section class="container">
                          		<h2 class="fonte1">Chatbot on Storytelling - c-AImões</h2>
                          		<div class="chat-container"></div>
                          		<input type="text" id="question" name="question" class="field"
                          			placeholder="Escreva sua pergunta ou questão para enviar ou converter em Áudio" />
                          		<div class="controls">
                          			<button class="btn btn-info" id="sendQuestion">Enviar</button>
                          			<button class="btn btn-info" id="getVoiceButton">Transformar em Voz</button>
                          			<button class="btn btn-info" id="recordButton">Gravar</button>
                          			<button class="btn btn-info" id="pauseButton" disabled>Pausar</button>
                          			<button class="btn btn-info" id="stopButton" disabled>Parar</button>
                          		</div>
                          	</section>
                          </div>
                        </div>
                      </main>
                      <!-- fim da main -->
                      <!-- Optional JavaScript -->
                      <!-- jQuery first, then Popper.js, then Bootstrap JS -->
                      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
                      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
                      <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
                      <script src="https://cdn.rawgit.com/mattdiamond/Recorderjs/08e7abd9/dist/recorder.js"></script>
                      <script type="text/javascript" src="js/assistant.js"></script>
                    	<script type="text/javascript" src="js/tts.js"></script>
                    	<script type="text/javascript" src="js/stt.js"></script>
                    </body>
                  </html>
