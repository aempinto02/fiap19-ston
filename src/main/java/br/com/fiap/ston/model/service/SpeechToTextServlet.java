/**
 * @author André Pinto
 * 
 * Servlet responsável por transformar o áudio em texto e enviar ao Watson Assistant
 * Também armazena a pergunta não respondida pelo chatbot
 */

package br.com.fiap.ston.model.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.ibm.cloud.sdk.core.http.HttpMediaType;
import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.assistant.v1.Assistant;
import com.ibm.watson.assistant.v1.model.Context;
import com.ibm.watson.assistant.v1.model.MessageInput;
import com.ibm.watson.assistant.v1.model.MessageOptions;
import com.ibm.watson.assistant.v1.model.MessageResponse;
import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;
import br.com.fiap.ston.beans.PerguntaNaoRespondida;
import br.com.fiap.ston.bo.PerguntaNaoRespondidaBO;
import br.com.fiap.ston.dao.EstatisticaDAO;
import br.com.fiap.ston.dao.PerguntaNaoRespondidaDAO;

@WebServlet(urlPatterns = "/stt")
public class SpeechToTextServlet extends HttpServlet {

  private Context context = null;
  private static final long serialVersionUID = 7476959821119009111L;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    byte[] buffer = new byte[1024 * 1024];

    InputStream is = req.getInputStream();

    File tempFile = File.createTempFile("speech-", ".wav");
    //File tempFile = new File("d:\\audio.wav");
    try (FileOutputStream os = new FileOutputStream(tempFile)) {
      int length;
      while((length = is.read(buffer)) != -1) {
        os.write(buffer, 0, length);
      }
    }

    IamOptions options = new IamOptions.Builder()
        //Colocar a sua APIKEY
        .apiKey("bL140DnTUZAM6xuym0ERZrzOnW9zC3BNMMLMXT0hixij")
        .build();

    SpeechToText service = new SpeechToText(options);

    RecognizeOptions recognizeOptions = new RecognizeOptions.Builder()
        .audio(tempFile)
        .contentType(HttpMediaType.AUDIO_WAV)
        .model("pt-BR_BroadbandModel")
        .acousticCustomizationId("5be7a6a4-6bc8-4331-9c39-2d81423957cc")   // customização acústica
        .customizationId("91f977d7-d099-4310-b12a-8cb9193f71a2")    // customização de linguagem
        .build();

    SpeechRecognitionResults transcript = service.recognize(recognizeOptions)
        .execute()
        .getResult();

    System.out.println(transcript);
    
    String pergunta = "";
    String s = "";

    if(!transcript.getResults().isEmpty()) {
      pergunta = transcript.getResults().get(0).getAlternatives().get(0).getTranscript();
      s = new Gson().toJson(transcript.getResults());
    }

    MessageResponse mr = assistantAPICall(s);

    // TO-DO Aqui está a resposta em String do Watson para testar com as intenções e fazer Estatística e Perguntas não respondidass
    String str =  mr.getOutput().getText().get(0);
    if(str.startsWith("A contação de história tem")) {
      EstatisticaDAO estatDao = new EstatisticaDAO();
      estatDao.inserir(req.getSession(false).getAttribute("email").toString());
      estatDao.aumentarNrRespostas(1, req.getSession(false).getAttribute("email").toString());
      estatDao.fechar();
    }
    else if(str.startsWith("Os atos são as")) {
      EstatisticaDAO estatDao = new EstatisticaDAO();
      estatDao.inserir(req.getSession(false).getAttribute("email").toString());
      estatDao.aumentarNrRespostas(2, req.getSession(false).getAttribute("email").toString());
      estatDao.fechar();
    }
    else if(str.startsWith("Para se fazer uma")) {
      EstatisticaDAO estatDao = new EstatisticaDAO();
      estatDao.inserir(req.getSession(false).getAttribute("email").toString());
      estatDao.aumentarNrRespostas(3, req.getSession(false).getAttribute("email").toString());
      estatDao.fechar();
    }
    else if(str.startsWith("A contação de história pode")) {
      EstatisticaDAO estatDao = new EstatisticaDAO();
      estatDao.inserir(req.getSession(false).getAttribute("email").toString());
      estatDao.aumentarNrRespostas(4, req.getSession(false).getAttribute("email").toString());
      estatDao.fechar();
    }
    else if(str.startsWith("Alguns erros na contação")) {
      EstatisticaDAO estatDao = new EstatisticaDAO();
      estatDao.inserir(req.getSession(false).getAttribute("email").toString());
      estatDao.aumentarNrRespostas(5, req.getSession(false).getAttribute("email").toString());
      estatDao.fechar();
    }
    else if(str.startsWith("No campo da propaganda")) {
      EstatisticaDAO estatDao = new EstatisticaDAO();
      estatDao.inserir(req.getSession(false).getAttribute("email").toString());
      estatDao.aumentarNrRespostas(6, req.getSession(false).getAttribute("email").toString());
      estatDao.fechar();
    }
    else if(str.startsWith("Nem todo jogo tem uma")) {
      EstatisticaDAO estatDao = new EstatisticaDAO();
      estatDao.inserir(req.getSession(false).getAttribute("email").toString());
      estatDao.aumentarNrRespostas(7, req.getSession(false).getAttribute("email").toString());
      estatDao.fechar();
    }
    else if(str.startsWith("É comum a história ter")) {
      EstatisticaDAO estatDao = new EstatisticaDAO();
      estatDao.inserir(req.getSession(false).getAttribute("email").toString());
      estatDao.aumentarNrRespostas(8, req.getSession(false).getAttribute("email").toString());
      estatDao.fechar();
    }
    else if(str.startsWith("Os personagens principais numa")) {
      EstatisticaDAO estatDao = new EstatisticaDAO();
      estatDao.inserir(req.getSession(false).getAttribute("email").toString());
      estatDao.aumentarNrRespostas(9, req.getSession(false).getAttribute("email").toString());
      estatDao.fechar();
    }
    else if(str.startsWith("Os profissionais que se utilizam")) {
      EstatisticaDAO estatDao = new EstatisticaDAO();
      estatDao.inserir(req.getSession(false).getAttribute("email").toString());
      estatDao.aumentarNrRespostas(10, req.getSession(false).getAttribute("email").toString());
      estatDao.fechar();
    }
    else if(str.startsWith("O herói é aquele em que")) {
      EstatisticaDAO estatDao = new EstatisticaDAO();
      estatDao.inserir(req.getSession(false).getAttribute("email").toString());
      estatDao.aumentarNrRespostas(11, req.getSession(false).getAttribute("email").toString());
      estatDao.fechar();
    }
    else if(str.startsWith("A contação de história com a jornada")) {
      EstatisticaDAO estatDao = new EstatisticaDAO();
      estatDao.inserir(req.getSession(false).getAttribute("email").toString());
      estatDao.aumentarNrRespostas(12, req.getSession(false).getAttribute("email").toString());
      estatDao.fechar();
    }
    else if(str.startsWith("A contação de história é a arte de se")) {
      EstatisticaDAO estatDao = new EstatisticaDAO();
      estatDao.inserir(req.getSession(false).getAttribute("email").toString());
      estatDao.aumentarNrRespostas(13, req.getSession(false).getAttribute("email").toString());
      estatDao.fechar();
    }
    else if(str.startsWith("Um dos personagens centrais na")) {
      EstatisticaDAO estatDao = new EstatisticaDAO();
      estatDao.inserir(req.getSession(false).getAttribute("email").toString());
      estatDao.aumentarNrRespostas(14, req.getSession(false).getAttribute("email").toString());
      estatDao.fechar();
    }
    else if(str.startsWith("Pergunte melhor")) {
      PerguntaNaoRespondida perg = new PerguntaNaoRespondida();
      perg.setPergunta(pergunta);
      PerguntaNaoRespondidaDAO pergDao = new PerguntaNaoRespondidaDAO();
      PerguntaNaoRespondidaBO pergBo = new PerguntaNaoRespondidaBO();
      if(pergBo.incluir(pergunta)) {
        pergDao.inserir(perg, req.getSession(false).getAttribute("email").toString());
      }
      pergDao.fechar();
      EstatisticaDAO estatDao = new EstatisticaDAO();
      estatDao.aumentarNrRespostas(15, req.getSession(false).getAttribute("email").toString());
      estatDao.fechar();
    }

    resp.setCharacterEncoding("UTF-8");
    resp.setContentType("text/plain");
    resp.getWriter().write(str);
  }


  private MessageResponse assistantAPICall(String msg) {

    // Configuração de autenticação do serviço *********************************************
    IamOptions options = new IamOptions.Builder()
        //Colocar a sua APIKEY
        .apiKey("5xfAHVqiADUHxj9P2WBLw5iHlrhmdQaCC-pfp6tc55MR")
        .build();

    // Criando o objeto do serviço desejado ************************************************

    Assistant service = new Assistant("2018-02-16", options);
    //Colocar a sua WORKSPACEID
    String workspaceId = "5ecfbdff-76ea-41b2-9df0-042cad653af0";

    // Preparando a mensagem de envio *****************************************************
    MessageInput input = new MessageInput();
    input.setText(msg);

    // Configurando os parametros para o Watson *******************************************
    MessageOptions messageOptions = new MessageOptions.Builder()
        .workspaceId(workspaceId)
        .input(input)
        .context(this.context)
        .build();

    // Conectando com o Assistant e recebendo a resposta dele ******************************
    MessageResponse response  = service.message(messageOptions)
        .execute()
        .getResult();

    this.context = response.getContext();

    return response;
  }

}




