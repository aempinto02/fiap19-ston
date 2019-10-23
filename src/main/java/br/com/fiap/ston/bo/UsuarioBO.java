/**
 * @author André Pinto
 * 
 * Esta classe garante que o nome do usuário tenha no mínimo três caracteres, a senha tenha no mínimo oito caracteres,
 * o email não seja vazio, o telefone e celular tenham 8 e 9 dígitos respectivamente
 */

package br.com.fiap.ston.bo;

import br.com.fiap.ston.beans.Usuario;
import br.com.fiap.ston.dao.UsuarioDAO;

public class UsuarioBO {
	public static boolean incluir(Usuario user) {
		UsuarioDAO dao = new UsuarioDAO();
		boolean retorno = true;
		if(user == null) {
			retorno = false;
		}
		if(user.getNome().length() <= 2) {
			retorno = false;
		}
		if(user.getSenha().length() <= 7) {
			retorno = false;
		}
		if(dao.pesquisarPorEmail(user.getEmail()) != null) {
			retorno = false;
		}
		if(user.getTelefone().length() < 8 && !user.getTelefone().isEmpty()) {
			retorno = false;
		}
		if(user.getCelular().length() < 9 && !user.getCelular().isEmpty()) {
			retorno = false;
		}
		return retorno;
	}
}
