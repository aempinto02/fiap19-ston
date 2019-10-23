/**
 * @author André Pinto
 * 
 * Esta classe garante que a sugestão enviada pelo usuário tenha no mínimo três(3) caracteres e no máximo 200
 */

package br.com.fiap.ston.bo;

public class SugestaoBO {
	public static boolean incluir(String sugestao) {
		if(sugestao.length() > 2 && sugestao.length() <= 200) {
			return true;
		}
		return false;
	}
}
