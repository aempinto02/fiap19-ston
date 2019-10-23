/**
 * @author André Pinto
 * 
 * Esta classe garante que a pergunta que o chatbot não respondeu não foi vazia
 */

package br.com.fiap.ston.bo;

public class PerguntaNaoRespondidaBO {
	public boolean incluir(String pergunta) {
		if(pergunta.length() > 0) {
			return true;
		}
		return false;
	}
}
